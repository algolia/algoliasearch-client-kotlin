package client.host

import client.ApplicationId
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

    val hosts = applicationId.computeFallbackHosts()
    val fallbackHosts = hosts.randomizeFallbackHosts()
    val statuses = fallbackHosts.initialHostStatus()

    suspend fun retryLogic(
        timeout: Long,
        path: String,
        attempt: Int = 1,
        request: suspend (String) -> HttpResponse
    ): HttpResponse {
        if (statuses.areStatusExpired(hostStatusExpirationDelay)) {
            for (index in statuses.indices)    {
                statuses[index] = Status.Unknown to 0L
            }
        }
        val index = statuses.selectNextHostIndex()
        val host = fallbackHosts[index]

        return try {
            withTimeout(timeout * attempt) {
                val response = request("$host$path")
                statuses[index] = Status.Up.getHostStatus()
                response
            }
        } catch (exception: TimeoutCancellationException) {
            statuses[index] = Status.Down.getHostStatus()
            retryLogic(timeout, path, attempt + 1, request)
        } catch (exception: BadResponseStatusException) {
            val code = exception.response.status.value
            val isSuccessful = floor(code / 100f) == 2f
            val isRetryable = floor(code / 100f) != 4f && !isSuccessful

            statuses[index] = Status.Down.getHostStatus()
            if (isRetryable) retryLogic(timeout, path, attempt + 1, request) else throw exception
        } catch (exception: IOException) {
            statuses[index] = Status.Down.getHostStatus()
            retryLogic(timeout, path, attempt + 1, request)
        }
    }
}