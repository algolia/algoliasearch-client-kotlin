package com.algolia.search.transport.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.configuration.Compression
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.Credentials
import com.algolia.search.configuration.RetryableHost
import com.algolia.search.exception.UnreachableHostsException
import com.algolia.search.transport.RequestOptions
import io.ktor.client.features.HttpRequestTimeoutException
import io.ktor.client.features.ResponseException
import io.ktor.client.features.timeout
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.math.floor

internal class Transport(
    configuration: Configuration,
    private val credentialsOrNull: Credentials?,
) : Configuration by configuration {

    private val hostStatusExpirationDelayMS: Long = 1000L * 60L * 5L
    private val mutex = Mutex()

    val credentials get() = credentialsOrNull!!

    suspend fun callableHosts(callType: CallType): List<RetryableHost> {
        return mutex.withLock {
            hosts.expireHostsOlderThan(hostStatusExpirationDelayMS)
            val hostsCallType = hosts.filterCallType(callType)
            val hostsCallTypeAreUp = hostsCallType.filter { it.isUp }

            if (hostsCallTypeAreUp.isEmpty()) {
                hostsCallType.apply { forEach { it.reset() } }
            } else hostsCallTypeAreUp
        }
    }

    private fun httpRequestBuilder(
        httpMethod: HttpMethod,
        path: String,
        requestOptions: RequestOptions?,
        body: String?,
        callType: CallType,
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
            timeout {
                requestTimeoutMillis = requestOptions.getTimeout(callType)
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

    suspend inline fun <reified T> request(
        httpMethod: HttpMethod,
        callType: CallType,
        path: String,
        requestOptions: RequestOptions?,
        body: String? = null,
    ): T {
        val hosts = callableHosts(callType)
        val requestBuilder = httpRequestBuilder(httpMethod, path, requestOptions, body, callType)
        val errors by lazy(LazyThreadSafetyMode.NONE) { mutableListOf<Throwable>() }

        for (host in hosts) {
            requestBuilder.url.host = host.url
            try {
                return httpClient.request<T>(requestBuilder).apply {
                    mutex.withLock { host.reset() }
                }
            } catch (exception: HttpRequestTimeoutException) {
                mutex.withLock { host.hasTimedOut() }
                errors += exception
            } catch (exception: IOException) {
                mutex.withLock { host.hasFailed() }
                errors += exception
            } catch (exception: ResponseException) {
                val value = exception.response.status.value
                val isRetryable = floor(value / 100f) != 4f
                if (isRetryable) mutex.withLock { host.hasFailed() } else throw exception
                errors += exception
            }
        }

        throw UnreachableHostsException(errors)
    }
}
