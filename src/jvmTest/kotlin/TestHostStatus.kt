
import client.Time
import client.host.HostStatus
import client.host.Status
import client.host.areStatusExpired
import client.host.selectNextHostIndex
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestHostStatus {

    @Test
    fun initialState() {
        val expected = listOf(Status.Unknown to 0L, Status.Unknown to 0L, Status.Unknown to 0L)
        val actual = expected.areStatusExpired(5000L)

        assertEquals(true, actual)
    }

    @Test
    fun invalid() {
        val initial = listOf<HostStatus>()

        assertEquals(true, initial.areStatusExpired(5000))
    }

    @Test
    fun lastRequestAfterExpirationDelay() {
        val sixSecondsAgo = Time.getCurrentTimeMillis() - 6000L
        val initial = listOf(Status.Unknown to 0L, Status.Unknown to 0L)
        val statuses = listOf(Status.Up to sixSecondsAgo, Status.Unknown to 0L)

        // The last request was made 6 seconds ago, expiration is 5 seconds. Host statuses have expired
        assertEquals(true, statuses.areStatusExpired(5000L))
        // The last request was made 6 seconds ago, expiration is 7 seconds. Host status are still valid.
        assertEquals(false, statuses.areStatusExpired(7000L))
    }

    @Test
    fun lastRequestBeforeExpirationDelay() {
        val now = Time.getCurrentTimeMillis()
        val fourSecondsAgo = now - 4000L
        val sixSecondsAgo = now - 6000L
        val hostStatusExpirationDelay = 5000L

        val statuses = listOf(Status.Unknown to 0L, Status.Up to fourSecondsAgo, Status.Down to sixSecondsAgo)

        // The last request was made 4 seconds ago. Even though one request was made 6 seconds ago, which is greater
        // than our expiration delay, our statuses should not be invalidated.
        assertEquals(false, statuses.areStatusExpired(hostStatusExpirationDelay))
    }

    @Test
    fun selectNextHostIndex() {
        val allUnknowns = listOf(Status.Unknown to 0L, Status.Unknown to 0L, Status.Unknown to 0L)
        val oneUp = listOf(Status.Unknown to 0L, Status.Unknown to 0L, Status.Up to 5000L)
        val twoUp = listOf(Status.Unknown to 0L, Status.Up to 5000L, Status.Up to 4000L)
        val oneUpOneUnknown = listOf(Status.Unknown to 0L, Status.Up to 4000L, Status.Unknown to 5000L)
        val allDown = listOf(Status.Down to 0L, Status.Down to 0L, Status.Down to 0L)
        val oneDownOtherUnknown = listOf(Status.Down to 0L, Status.Unknown to 0L, Status.Unknown to 0L)

        assertEquals(0, allUnknowns.selectNextHostIndex())
        assertEquals(2, oneUp.selectNextHostIndex())
        assertEquals(1, twoUp.selectNextHostIndex())
        // Even though the Unknown status is more recent, we still prefer an host that is Up.
        assertEquals(1, oneUpOneUnknown.selectNextHostIndex())
        assertEquals(0, allDown.selectNextHostIndex())
        assertEquals(1, oneDownOtherUnknown.selectNextHostIndex())
    }
}