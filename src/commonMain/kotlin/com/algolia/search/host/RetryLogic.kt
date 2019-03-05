package com.algolia.search.host

import com.algolia.search.exception.RetryableException
import io.ktor.client.features.BadResponseStatusException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import kotlinx.io.IOException
import kotlin.math.floor


internal class RetryLogic(
    val hosts: List<String>,
    private val hostStatusExpirationDelay: Long = 1000L * 60L * 5L
) {

    internal val statuses = hosts.initialHostStatus()

    private val maxAttempts = 5

    private suspend fun <T> retry(
        timeout: Long,
        path: String,
        attempt: Int,
        request: suspend (String) -> T,
        exceptions: List<Exception>
    ): T {
        if (statuses.areStatusExpired(hostStatusExpirationDelay)) {
            for (index in statuses.indices) {
                statuses[index] = HostStatus.Unknown to 0L
            }
        }
        val index = statuses.selectNextHostIndex()
        val host = hosts[index]

        return try {
            withTimeout(timeout * attempt + 1) {
                val response = request("$host$path")
                statuses[index] = HostStatus.Up.getHostStatus()
                response
            }
        } catch (exception: Exception) {
            if (attempt >= maxAttempts) throw RetryableException(attempt, exceptions)
            when (exception) {
                is BadResponseStatusException -> {
                    val code = exception.response.status.value
                    val isSuccessful = floor(code / 100f) == 2f
                    val isRetryable = floor(code / 100f) != 4f && !isSuccessful

                    if (isRetryable) {
                        statuses[index] = HostStatus.Down.getHostStatus()
                    } else throw exception
                }
                is IOException, is TimeoutCancellationException -> {
                    statuses[index] = HostStatus.Down.getHostStatus()
                }
                else -> throw exception
            }
            retry(timeout, path, attempt + 1, request, exceptions.plus(exception))
        }
    }

    suspend fun <T> retry(
        timeout: Long,
        path: String,
        request: suspend (String) -> T
    ): T {
        return retry(timeout, path, 0, request, listOf())
    }
}