package com.algolia.search.transport

import com.algolia.search.configuration.CallType
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.RetryableHost
import io.ktor.client.features.ResponseException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withTimeout
import kotlinx.io.IOException
import kotlin.math.floor


internal class Transport(
    configuration: Configuration
) : Configuration by configuration {

    private val hostStatusExpirationDelayMS: Long = 1000L * 60L * 5L
    private val mutex = Mutex()

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
        body: String?
    ): HttpRequestBuilder {
        return HttpRequestBuilder().apply {
            url.path(path)
            url.protocol = URLProtocol.HTTPS
            method = httpMethod
            body?.let { this.body = it }
            setApplicationId(applicationID)
            setApiKey(apiKey)
            setRequestOptions(requestOptions)
        }
    }

    suspend inline fun <reified T> request(
        httpMethod: HttpMethod,
        callType: CallType,
        path: String,
        requestOptions: RequestOptions?,
        body: String? = null
    ): T {
        val hosts = callableHosts(callType)
        val timeout = requestOptions.getTimeout(callType)
        val requestBuilder = httpRequestBuilder(httpMethod, path, requestOptions, body)

        for (host in hosts) {
            requestBuilder.url.host = host.url
            try {
                return withTimeout((host.retryCount + 1) * timeout) {
                    val response = httpClient.request<T>(requestBuilder)
                    mutex.withLock { host.reset() }
                    response
                }
            } catch (exception: TimeoutCancellationException) {
                mutex.withLock { host.hasTimedOut() }
            } catch (exception: IOException) {
                mutex.withLock { host.hasFailed() }
            } catch (exception: ResponseException) {
                val isRetryable = floor(exception.response.status.value / 100f) != 4f

                if (isRetryable) mutex.withLock { host.hasFailed() } else throw exception
            }
        }
        throw RuntimeException("Unreachable hosts.")
    }
}