package host

import com.algolia.search.Time
import com.algolia.search.host.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeFalse
import shouldBeNull
import shouldBeTrue
import shouldEqual


@RunWith(JUnit4::class)
internal class TestHostStatus {

    @Test
    fun initialState() {
        val expected = listOf(HostState.Unknown to 0L, HostState.Unknown to 0L, HostState.Unknown to 0L)
        val actual = expected.areStatusExpired(5000L)

        actual.shouldBeTrue()
    }

    @Test
    fun emptyHostStatusList() {
        val initial = listOf<HostStatus>()

        initial.areStatusExpired(5000).shouldBeTrue()
    }

    @Test
    fun lastRequestAfterExpirationDelay() {
        val sixSecondsAgo = Time.getCurrentTimeMillis() - 6000L
        val statuses = listOf(HostState.Up to sixSecondsAgo, HostState.Unknown to 0L)

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

        val statuses =
            listOf(HostState.Unknown to 0L, HostState.Up to fourSecondsAgo, HostState.Down to sixSecondsAgo)

        // The last request was made 4 seconds ago. Even though one request was made 6 seconds ago, which is greater
        // than our expiration delay, our statuses should not be invalidated.
        statuses.areStatusExpired(hostStatusExpirationDelay).shouldBeFalse()
    }

    @Test
    fun selectNextHostIndex() {
        val allUnknowns = listOf(HostState.Unknown to 0L, HostState.Unknown to 0L, HostState.Unknown to 0L)
        val oneUp = listOf(HostState.Unknown to 0L, HostState.Unknown to 0L, HostState.Up to 5000L)
        val twoUp = listOf(HostState.Unknown to 0L, HostState.Up to 5000L, HostState.Up to 4000L)
        val oneUpOneUnknown = listOf(HostState.Unknown to 0L, HostState.Up to 4000L, HostState.Unknown to 5000L)
        val allDown = listOf(HostState.Down to 0L, HostState.Down to 0L, HostState.Down to 0L)
        val oneDownOtherUnknown = listOf(HostState.Down to 0L, HostState.Unknown to 0L, HostState.Unknown to 0L)

        allUnknowns.selectNextHostIndex() shouldEqual 0
        oneUp.selectNextHostIndex() shouldEqual 2
        twoUp.selectNextHostIndex() shouldEqual 1
        // Even though the Unknown status is more recent, we still prefer an host that is Up.
        oneUpOneUnknown.selectNextHostIndex() shouldEqual 1
        allDown.selectNextHostIndex().shouldBeNull()
        oneDownOtherUnknown.selectNextHostIndex() shouldEqual 1
    }

    @Test
    fun selectNextIndex() {
        val allDown = listOf(HostState.Down to 0L, HostState.Down to 0L, HostState.Down to 0L)
        var index = -1

        index = allDown.nextIndex(index)
        index shouldEqual 0
        index = allDown.nextIndex(index)
        index shouldEqual 1
        index = allDown.nextIndex(index)
        index shouldEqual 2
        index = allDown.nextIndex(index)
        index shouldEqual 0
        index = allDown.nextIndex(index)
        index shouldEqual 1
    }
}