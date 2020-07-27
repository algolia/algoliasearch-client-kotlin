import io.ktor.client.features.ClientRequestException
import kotlinx.coroutines.delay
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

/**
 * Create a [RetryableClientRequest] instance.
 */
@OptIn(ExperimentalTime::class)
internal fun retryApiCall(
    delay: Duration = 1.seconds,
    retries: Int = 10,
    block: suspend () -> Unit,
): RetryableClientRequest {
    require(delay > Duration.ZERO) { "Expected positive delay, but had $delay" }
    require(retries > 0) { "Expected positive amount of retries, but had $retries" }
    return RetryableClientRequest(delay, retries, block)
}

@OptIn(ExperimentalTime::class)
internal class RetryableClientRequest(
    private val delayDuration: Duration = 1.seconds,
    private val retries: Int = 10,
    private val block: suspend () -> Unit,
) {

    /**
     * Retries [block] calls if [retry] returns true until [retries] count is reached, otherwise throws an exception.
     */
    suspend fun whenever(retry: suspend (ClientRequestException) -> Boolean) {
        for (i in 0 until retries) {
            try {
                block()
            } catch (e: ClientRequestException) {
                if (!retry(e)) throw e
                delay(delayDuration.inMilliseconds.toLong())
            }
        }
        throw RuntimeException("reached the maximum number of retries")
    }
}
