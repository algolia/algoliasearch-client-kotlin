import client.Time
import client.host.HostStatus
import client.host.Status
import client.host.hostStatusExpiration
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestHostStatus {

    @Test
    fun hostExpiration_initialState() {
        val expected = listOf(Status.Unknown to 0L, Status.Unknown to 0L, Status.Unknown to 0L)
        val actual = expected.hostStatusExpiration(5000L)

        assertEquals(expected, actual)
    }

    @Test
    fun hostExpiration_invalid() {
        val initial = listOf<HostStatus>()

        assertEquals(initial, initial.hostStatusExpiration(5000))
    }

    @Test
    fun hostExpiration_lastRequestAfterExpirationDelay() {
        val sixSecondsAgo = Time.getCurrentTimeMillis() - 6000L
        val initial = listOf(Status.Unknown to 0L, Status.Unknown to 0L)
        val statuses = listOf(Status.Up to sixSecondsAgo, Status.Unknown to 0L)

        // The last request was made 6 seconds ago, expiration is 5 seconds. Host statuses have expired
        assertEquals(initial, statuses.hostStatusExpiration(5000L))
        // The last request was made 6 seconds ago, expiration is 7 seconds. Host status are still valid.
        assertEquals(statuses, statuses.hostStatusExpiration(7000L))
    }

    @Test
    fun hostExpiration_lasdtRequestBeforeExpirationDelay() {
        val now = Time.getCurrentTimeMillis()
        val fourSecondsAgo = now - 4000L
        val sixSecondsAgo = now - 6000L
        val hostStatusExpirationDelay = 5000L

        val initial = listOf(Status.Unknown to 0L, Status.Up to fourSecondsAgo, Status.Down to sixSecondsAgo)

        // The last request was made 4 seconds ago. Even though one request was made 6 seconds ago, which is greater
        // than our expiration delay, our statuses should not be invalidated.
        assertEquals(initial, initial.hostStatusExpiration(hostStatusExpirationDelay))
    }
}