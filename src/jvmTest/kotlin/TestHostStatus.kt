
import client.Time
import client.host.HostStatus
import client.host.Status
import client.host.areStatusExpired
import client.host.selectNextHostIndex
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class TestHostStatus {

    @Test
    fun initialState() {
        val expected = listOf(Status.Unknown to 0L, Status.Unknown to 0L, Status.Unknown to 0L)
        val actual = expected.areStatusExpired(5000L)

        actual.shouldBeTrue()
    }

    @Test
    fun invalid() {
        val initial = listOf<HostStatus>()

        initial.areStatusExpired(5000).shouldBeTrue()
    }

    @Test
    fun lastRequestAfterExpirationDelay() {
        val sixSecondsAgo = Time.getCurrentTimeMillis() - 6000L
        val initial = listOf(Status.Unknown to 0L, Status.Unknown to 0L)
        val statuses = listOf(Status.Up to sixSecondsAgo, Status.Unknown to 0L)

        // The last request was made 6 seconds ago, expiration is 5 seconds. Host statuses have expired
        statuses.areStatusExpired(5000L).shouldBeTrue()
        // The last request was made 6 seconds ago, expiration is 7 seconds. Host status are still valid.
        statuses.areStatusExpired(7000L).shouldBeFalse()
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
        statuses.areStatusExpired(hostStatusExpirationDelay).shouldBeFalse()
    }

    @Test
    fun selectNextHostIndex() {
        val allUnknowns = listOf(Status.Unknown to 0L, Status.Unknown to 0L, Status.Unknown to 0L)
        val oneUp = listOf(Status.Unknown to 0L, Status.Unknown to 0L, Status.Up to 5000L)
        val twoUp = listOf(Status.Unknown to 0L, Status.Up to 5000L, Status.Up to 4000L)
        val oneUpOneUnknown = listOf(Status.Unknown to 0L, Status.Up to 4000L, Status.Unknown to 5000L)
        val allDown = listOf(Status.Down to 0L, Status.Down to 0L, Status.Down to 0L)
        val oneDownOtherUnknown = listOf(Status.Down to 0L, Status.Unknown to 0L, Status.Unknown to 0L)

        0 shouldEqual allUnknowns.selectNextHostIndex()
        2 shouldEqual oneUp.selectNextHostIndex()
        1 shouldEqual twoUp.selectNextHostIndex()
        // Even though the Unknown status is more recent, we still prefer an host that is Up.
        1 shouldEqual oneUpOneUnknown.selectNextHostIndex()
        0 shouldEqual allDown.selectNextHostIndex()
        1 shouldEqual oneDownOtherUnknown.selectNextHostIndex()
    }
}