package host
import com.algolia.search.saas.Time
import com.algolia.search.saas.host.HostStatus
import com.algolia.search.saas.host.HostStatuses
import com.algolia.search.saas.host.areStatusExpired
import com.algolia.search.saas.host.selectNextHostIndex
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeFalse
import shouldBeTrue
import shouldEqual


@RunWith(JUnit4::class)
internal class TestHostStatus {

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

        allUnknowns.selectNextHostIndex() shouldEqual 0
        oneUp.selectNextHostIndex() shouldEqual 2
        twoUp.selectNextHostIndex() shouldEqual 1
        // Even though the Unknown status is more recent, we still prefer an host that is Up.
        oneUpOneUnknown.selectNextHostIndex() shouldEqual 1
        allDown.selectNextHostIndex() shouldEqual 0
        oneDownOtherUnknown.selectNextHostIndex() shouldEqual 1
    }
}