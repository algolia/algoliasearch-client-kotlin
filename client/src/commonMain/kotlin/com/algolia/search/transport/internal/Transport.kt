package com.algolia.search.transport.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.configuration.Compression
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.Credentials
import com.algolia.search.configuration.RetryableHost
import com.algolia.search.exception.UnreachableHostsException
import com.algolia.search.exception.internal.asApiException
import com.algolia.search.exception.internal.asClientException
import com.algolia.search.transport.CustomRequester
import com.algolia.search.transport.RequestOptions
import io.ktor.client.call.HttpClientCall
import io.ktor.client.features.HttpRequestTimeoutException
import io.ktor.client.features.ResponseException
import io.ktor.client.features.timeout
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.network.sockets.ConnectTimeoutException
import io.ktor.network.sockets.SocketTimeoutException
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class Transport(
    configuration: Configuration,
    private val credentialsOrNull: Credentials?,
) : CustomRequester, Configuration by configuration {

    private val hostStatusExpirationDelayMS: Long = 1000L * 60L * 5L
    private val mutex: Mutex = Mutex()

    internal val credentials get() = credentialsOrNull!!

    /**
     * Runs an HTTP request (with retry strategy) and get a result as [T].
     *
     * @param httpMethod http method (verb)
     * @param callType indicate whether the HTTP call performed is of type Read or Write
     * @param path request path
     * @param requestOptions additional request configuration
     * @param body request body
     */
    internal suspend inline fun <reified T> request(
        httpMethod: HttpMethod,
        callType: CallType,
        path: String,
        requestOptions: RequestOptions?,
        body: String? = null,
    ): T {
        return execute(httpMethod, callType, path, requestOptions, body) {
            httpClient.request(it)
        }
    }

    /**
     * Execute HTTP request (with retry strategy) and get a result as [T].
     */
    private suspend inline fun <T> execute(
        httpMethod: HttpMethod,
        callType: CallType,
        path: String,
        requestOptions: RequestOptions?,
        body: String? = null,
        block: (HttpRequestBuilder) -> T
    ): T {
        val hosts = callableHosts(callType)
        val errors by lazy(LazyThreadSafetyMode.NONE) { mutableListOf<Throwable>() }
        val requestBuilder = httpRequestBuilder(httpMethod, path, requestOptions, body)

        for (host in hosts) {
            requestBuilder.url.host = host.url
            try {
                setTimeout(requestBuilder, requestOptions, callType, host)
                return block(requestBuilder).apply {
                    mutex.withLock { host.reset() }
                }
            } catch (exception: Throwable) {
                host.handle(exception)
                errors += exception.asClientException()
            }
        }
        throw UnreachableHostsException(errors)
    }

    /**
     * Get list of [RetryableHost] for a given [CallType].
     */
    suspend fun callableHosts(callType: CallType): List<RetryableHost> {
        return mutex.withLock {
            hosts.expireHostsOlderThan(hostStatusExpirationDelayMS)
            val hostsCallType = hosts.filterCallType(callType)
            val hostsCallTypeAreUp = hostsCallType.filter { it.isUp }
            hostsCallTypeAreUp.ifEmpty {
                hostsCallType.onEach { it.reset() }
            }
        }
    }

    /**
     * Get a [HttpRequestBuilder] with given parameters.
     */
    private fun httpRequestBuilder(
        httpMethod: HttpMethod,
        path: String,
        requestOptions: RequestOptions?,
        body: String?,
    ): HttpRequestBuilder {
        return HttpRequestBuilder().apply {
            url.path(path)
            url.protocol = URLProtocol.HTTPS
            method = httpMethod
            compress(body)
            credentialsOrNull?.let {
                setApplicationId(it.applicationID)
                setApiKey(it.apiKey)
            }
            setRequestOptions(requestOptions)
        }
    }

    private fun HttpRequestBuilder.compress(payload: String?) {
        if (payload != null) {
            body = when (compression) {
                Compression.Gzip -> Gzip(payload)
                Compression.None -> payload
            }
        }
    }

    /**
     * Handle API request exceptions.
     */
    private suspend fun RetryableHost.handle(exception: Throwable) {
        when (exception) {
            is HttpRequestTimeoutException, is SocketTimeoutException, is ConnectTimeoutException -> mutex.withLock { hasTimedOut() }
            is IOException -> mutex.withLock { hasFailed() }
            is ResponseException -> {
                when (exception.response.status.value) {
                    in (400 until 500) -> throw exception.asApiException()
                    else -> mutex.withLock { hasFailed() }
                }
            }
            else -> throw exception.asClientException()
        }
    }

    /**
     * Set socket read/write timeout.
     */
    private fun setTimeout(
        requestBuilder: HttpRequestBuilder,
        requestOptions: RequestOptions?,
        callType: CallType,
        host: RetryableHost,
    ) {
        requestBuilder.timeout {
            socketTimeoutMillis = requestOptions.getTimeout(callType) * (host.retryCount + 1)
        }
    }

    override suspend fun <T> customRequest(
        method: HttpMethod,
        callType: CallType,
        path: String,
        responseType: TypeInfo,
        body: String?,
        requestOptions: RequestOptions?
    ): T {
        val httpResponse = genericRequest(method, callType, path, requestOptions, body)
        return httpResponse.call.receiveAs(responseType)
    }

    /**
     * Execute HTTP request (with retry strategy) and get a result as [HttpResponse].
     */
    private suspend fun genericRequest(
        httpMethod: HttpMethod,
        callType: CallType,
        path: String,
        requestOptions: RequestOptions?,
        body: String? = null
    ): HttpResponse {
        return execute(httpMethod, callType, path, requestOptions, body) {
            httpClient.request(it)
        }
    }

    /**
     * Receive Http payload as [T].
     */
    @Suppress("UNCHECKED_CAST")
    private suspend fun <T> HttpClientCall.receiveAs(type: TypeInfo): T = receive(type) as T
}
