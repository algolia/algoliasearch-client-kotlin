package com.algolia.client.transport.internal

import com.algolia.client.configuration.CallType
import com.algolia.client.configuration.Host
import com.algolia.client.configuration.internal.HEADER_APIKEY
import com.algolia.client.exception.AlgoliaRetryException
import com.algolia.client.exception.internal.asApiException
import com.algolia.client.exception.internal.asClientException
import com.algolia.client.transport.AlgoliaHttpResponse
import com.algolia.client.transport.RequestConfig
import com.algolia.client.transport.RequestMethod
import com.algolia.client.transport.RequestOptions
import com.algolia.client.transport.Requester
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.network.sockets.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.utils.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.util.reflect.*
import io.ktor.utils.io.errors.*
import kotlin.time.Duration
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.JsonObject

/** Default implementation of [Requester] using Ktor's [HttpClient]. */
public class KtorRequester(
  public val httpClient: HttpClient,
  private val connectTimeout: Duration,
  private val readTimeout: Duration,
  private val writeTimeout: Duration,
  hosts: List<Host>,
  internal val sendsRequestId: Boolean = false,
) : Requester, kotlin.AutoCloseable {

  private val hostStatusExpirationDelayMS: Long = 1000L * 60L * 5L
  private val mutex: Mutex = Mutex()
  private val retryableHosts = hosts.map { RetryableHost(it) }

  public override fun setClientApiKey(apiKey: String) {
    headers {
      if (contains(HEADER_APIKEY)) {
        remove(HEADER_APIKEY)
      }
      append(HEADER_APIKEY, apiKey)
    }
  }

  override fun close() {
    httpClient.close()
  }

  override suspend fun <T> execute(
    requestConfig: RequestConfig,
    requestOptions: RequestOptions?,
    returnType: TypeInfo,
  ): T =
    executeWithRetry(requestConfig, requestOptions) { response ->
      @Suppress("UNCHECKED_CAST")
      val body: T = if (response.hasEmptyBody()) null as T else response.body<T>(returnType)
      body
    }

  override suspend fun <T> executeWithHttpInfo(
    requestConfig: RequestConfig,
    requestOptions: RequestOptions?,
    returnType: TypeInfo,
  ): AlgoliaHttpResponse<T> =
    executeWithRetry(requestConfig, requestOptions) { response ->
      val isEmpty = response.hasEmptyBody()
      AlgoliaHttpResponse(
        statusCode = response.status.value,
        headers = response.headers.toMap(),
        body = if (isEmpty) null else response.bodyAsText(),
        data = if (isEmpty) null else response.body<T>(returnType),
      )
    }

  private suspend fun <R> executeWithRetry(
    requestConfig: RequestConfig,
    requestOptions: RequestOptions?,
    handleResponse: suspend (HttpResponse) -> R,
  ): R {
    val callType = callTypeOf(requestConfig)
    val hosts = callableHosts(callType)
    val errors by lazy(LazyThreadSafetyMode.NONE) { mutableListOf<Throwable>() }
    val requestBuilder = httpRequestBuilderOf(requestConfig, requestOptions)
    var lastCorrelationId: String? = null

    for (host in hosts) {
      requestBuilder.url.protocol = URLProtocol.createOrDefault(host.protocol)
      requestBuilder.url.host = host.url
      if (host.port != null) {
        requestBuilder.url.port = host.port!!
      }
      requestBuilder.setTimeout(requestOptions, callType, host)
      try {
        val response = httpClient.request(requestBuilder)
        val result = handleResponse(response)
        mutex.withLock { host.reset() }
        return result
      } catch (exception: Throwable) {
        if (exception is ResponseException) {
          lastCorrelationId = exception.response.headers[HEADER_CORRELATION_ID] ?: lastCorrelationId
        }
        host.onError(exception)
        errors += exception.asClientException()
      }
    }
    throw AlgoliaRetryException(errors, lastCorrelationId)
  }

  private fun HttpResponse.hasEmptyBody(): Boolean = status.value == 204 || contentLength() == 0L

  private fun callTypeOf(requestConfig: RequestConfig): CallType =
    if (requestConfig.isRead || requestConfig.method == RequestMethod.GET) {
      CallType.Read
    } else {
      CallType.Write
    }

  /** Get list of [RetryableHost] for a given [CallType]. */
  private suspend fun callableHosts(callType: CallType): List<RetryableHost> = mutex.withLock {
    retryableHosts.expireHostsOlderThan(hostStatusExpirationDelayMS)
    val hostsCallType = retryableHosts.filterCallType(callType)
    val hostsCallTypeAreUp = hostsCallType.filter { it.isUp }
    hostsCallTypeAreUp.ifEmpty { hostsCallType.onEach { it.reset() } }
  }

  /** Handle API request exceptions. */
  private suspend fun RetryableHost.onError(throwable: Throwable) {
    when (throwable) {
      is CancellationException -> throw throwable // propagate coroutine cancellation
      is ClientRequestException -> throw throwable.asApiException()
      is HttpRequestTimeoutException,
      is SocketTimeoutException,
      is ConnectTimeoutException -> mutex.withLock { hasTimedOut() }
      is IOException,
      is ResponseException -> mutex.withLock { hasFailed() }
      else -> throw throwable.asClientException()
    }
  }

  /** Set socket read/write timeout. */
  private fun HttpRequestBuilder.setTimeout(
    requestOptions: RequestOptions?,
    callType: CallType,
    host: RetryableHost,
  ) {
    timeout {
      val timeout =
        when (callType) {
          CallType.Read -> requestOptions?.readTimeout ?: readTimeout
          CallType.Write -> requestOptions?.writeTimeout ?: writeTimeout
        }
      val connectTimeoutDuration = requestOptions?.connectTimeout ?: connectTimeout
      connectTimeoutMillis = connectTimeoutDuration.inWholeMilliseconds
      socketTimeoutMillis = timeout.inWholeMilliseconds * (host.retryCount + 1)
    }
  }

  private fun List<RetryableHost>.expireHostsOlderThan(hostStatusExpirationDelayMS: Long) {
    forEach {
      val timeDelayExpired = currentTimeMillis() - it.lastUpdated
      if (timeDelayExpired > hostStatusExpirationDelayMS) {
        it.reset()
      }
    }
  }

  private fun List<RetryableHost>.filterCallType(callType: CallType): List<RetryableHost> = filter {
    it.callType == callType || it.callType == null
  }

  private fun httpRequestBuilderOf(
    requestConfig: RequestConfig,
    requestOptions: RequestOptions? = null,
  ): HttpRequestBuilder =
    HttpRequestBuilder().apply {
      url { pathSegments = requestConfig.path }
      method = requestConfig.method.ktorHttpMethod
      contentType(ContentType.Application.Json)

      requestConfig.run {
        requestHeaders(headers)
        queryParameter(query)
        when {
          body != null -> setBody(body.body, body.bodyType)
          requiresBody(requestConfig) -> setBody(EmptyObject)
          else -> setBody(EmptyContent)
        }
      }

      requestOptions?.run {
        requestHeaders(headers)
        queryParameter(urlParameters)
        body?.let { setBody(it) }
      }

      if (sendsRequestId && !carriesRequestId()) {
        headers.append(HEADER_REQUEST_ID, generateRequestId())
      }
    }

  private fun HttpRequestBuilder.carriesRequestId(): Boolean =
    headers.contains(HEADER_REQUEST_ID) ||
      url.encodedParameters.names().any { it.equals(QUERY_PARAM_REQUEST_ID, ignoreCase = true) }

  private fun requiresBody(requestConfig: RequestConfig) =
    requestConfig.method == RequestMethod.POST || requestConfig.method == RequestMethod.PUT

  private fun HttpRequestBuilder.requestHeaders(headerOptions: Map<String, Any>) {
    headers.replaceAll(headerOptions)
  }

  private fun HttpRequestBuilder.queryParameter(parameters: Map<String, Any>) {
    url.encodedParameters.replaceAllEncoded(parameters)
  }

  private fun StringValuesBuilder.replaceAllEncoded(input: Map<String, Any>) {
    input.onEach { (key, value) ->
      if (contains(key)) remove(key)
      when (value) {
        is Iterable<*> ->
          append(
            key.encodeURLParameter(),
            value.joinToString(",") { it.toString() }.encodeURLParameter(),
          )
        else -> append(key.encodeURLParameter(), value.toString().encodeURLParameter())
      }
    }
  }

  private fun StringValuesBuilder.replaceAll(input: Map<String, Any>) {
    input.onEach { (key, value) ->
      if (contains(key)) remove(key)
      when (value) {
        is Iterable<*> -> appendAll(key, value.map { it.toString() })
        else -> append(key, value.toString())
      }
    }
  }

  private val RequestMethod.ktorHttpMethod: HttpMethod
    get() =
      when (this) {
        RequestMethod.DELETE -> HttpMethod.Delete
        RequestMethod.GET -> HttpMethod.Get
        RequestMethod.HEAD -> HttpMethod.Head
        RequestMethod.PATCH -> HttpMethod.Patch
        RequestMethod.PUT -> HttpMethod.Put
        RequestMethod.POST -> HttpMethod.Post
        RequestMethod.OPTIONS -> HttpMethod.Options
      }

  public companion object {
    /** Represents an empty Json object */
    private val EmptyObject = JsonObject(emptyMap())
  }
}
