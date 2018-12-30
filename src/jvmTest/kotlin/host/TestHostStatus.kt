package host
import client.Time
import client.host.HostStatuses
import client.host.HostStatus
import client.host.areStatusExpired
import client.host.selectNextHostIndex
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeFalse
import shouldBeTrue
import shouldEqual


@RunWith(JUnit4::class)
class TestHostStatus {

    @Test
    fun initialState() {
        val expected = listOf(HostStatus.Unknown to 0L, HostStatus.Unknown to 0L, HostStatus.Unknown to 0L)
        val actual = expected.areStatusExpired(5000L)

        actual.shouldBeTrue()
    }

    @Test
    fun invalid() {
        val initial = listOf<HostStatuses>()

        initial.areStatusExpired(5000).shouldBeTrue()
    }

    @Test
    fun lastRequestAfterExpirationDelay() {
        val sixSecondsAgo = Time.getCurrentTimeMillis() - 6000L
        val initial = listOf(HostStatus.Unknown to 0L, HostStatus.Unknown to 0L)
        val statuses = listOf(HostStatus.Up to sixSecondsAgo, HostStatus.Unknown to 0L)

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

        val statuses = listOf(HostStatus.Unknown to 0L, HostStatus.Up to fourSecondsAgo, HostStatus.Down to sixSecondsAgo)

        // The last request was made 4 seconds ago. Even though one request was made 6 seconds ago, which is greater
        // than our expiration delay, our statuses should not be invalidated.
        statuses.areStatusExpired(hostStatusExpirationDelay).shouldBeFalse()
    }

    @Test
    fun selectNextHostIndex() {
        val allUnknowns = listOf(HostStatus.Unknown to 0L, HostStatus.Unknown to 0L, HostStatus.Unknown to 0L)
        val oneUp = listOf(HostStatus.Unknown to 0L, HostStatus.Unknown to 0L, HostStatus.Up to 5000L)
        val twoUp = listOf(HostStatus.Unknown to 0L, HostStatus.Up to 5000L, HostStatus.Up to 4000L)
        val oneUpOneUnknown = listOf(HostStatus.Unknown to 0L, HostStatus.Up to 4000L, HostStatus.Unknown to 5000L)
        val allDown = listOf(HostStatus.Down to 0L, HostStatus.Down to 0L, HostStatus.Down to 0L)
        val oneDownOtherUnknown = listOf(HostStatus.Down to 0L, HostStatus.Unknown to 0L, HostStatus.Unknown to 0L)

        0 shouldEqual allUnknowns.selectNextHostIndex()
        2 shouldEqual oneUp.selectNextHostIndex()
        1 shouldEqual twoUp.selectNextHostIndex()
        // Even though the Unknown status is more recent, we still prefer an host that is Up.
        1 shouldEqual oneUpOneUnknown.selectNextHostIndex()
        0 shouldEqual allDown.selectNextHostIndex()
        1 shouldEqual oneDownOtherUnknown.selectNextHostIndex()
    }
}