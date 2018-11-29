package client.host

import client.ApplicationId
import client.Time
import io.ktor.client.features.BadResponseStatusException
import io.ktor.client.response.HttpResponse
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import kotlinx.io.IOException
import kotlin.math.floor


internal class Hosts(
    applicationId: ApplicationId,
    private val hostStatusExpirationDelay: Long = 1000L * 60L * 5L
) {

    private val hosts = applicationId.computeFallbackHosts()
    private val fallbackHosts = hosts.randomizeFallbackHosts()
    private val statuses = fallbackHosts.initialHostStatus()

    suspend fun retryLogic(
        timeout: Long,
        path: String,
        attempt: Int = 1,
        request: suspend (String) -> HttpResponse
    ): HttpResponse {
        val index = (attempt - 1) % fallbackHosts.size
        val host = fallbackHosts[index]

        return try {
            withTimeout(timeout * attempt) {
                request("https://$host$path")
            }
        } catch (exception: TimeoutCancellationException) {
            statuses[index] = Status.Down to Time.getCurrentTimeMillis()
            retryLogic(timeout, path, attempt + 1, request)
        } catch (exception: BadResponseStatusException) {
            val code = exception.response.status.value
            val isSuccessful = floor(code / 100f) == 2f
            val isRetryable = floor(code / 100f) != 4f && !isSuccessful
            val status = if (isRetryable) Status.Up else Status.Down

            statuses[index] = status to Time.getCurrentTimeMillis()
            if (isRetryable) retryLogic(timeout, path, attempt + 1, request) else throw exception
        } catch (exception: IOException) {
            statuses[index] = Status.Down to Time.getCurrentTimeMillis()
            retryLogic(timeout, path, attempt + 1, request)
        }
    }
}