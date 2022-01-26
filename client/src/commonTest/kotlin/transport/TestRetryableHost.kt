package transport

import com.algolia.search.configuration.CallType
import com.algolia.search.configuration.RetryableHost
import com.algolia.search.helper.toApplicationID
import com.algolia.search.transport.internal.hasFailed
import com.algolia.search.transport.internal.hasTimedOut
import com.algolia.search.transport.internal.reset
import com.algolia.search.transport.internal.searchHosts
import shouldContainAll
import shouldEqual
import kotlin.test.Test

internal class TestRetryableHost {

    private val host = RetryableHost("url", CallType.Write)
    private val applicationID = "appID".toApplicationID()

    @Test
    fun searchHosts() {
        applicationID.searchHosts shouldContainAll listOf(
            RetryableHost("$applicationID-dsn.algolia.net", CallType.Read),
            RetryableHost("$applicationID.algolia.net", CallType.Write),
            RetryableHost("$applicationID-1.algolianet.com"),
            RetryableHost("$applicationID-2.algolianet.com"),
            RetryableHost("$applicationID-3.algolianet.com")
        )
    }

    @Test
    fun reset() {
        host.apply {
            isUp = false
            retryCount = 1
        }
        host.reset()
        host.let {
            it.isUp shouldEqual true
            it.retryCount shouldEqual 0
        }
    }

    @Test
    fun timedOut() {
        host.apply {
            isUp = false
            retryCount = 0
        }
        host.hasTimedOut()
        host.let {
            it.isUp shouldEqual true
            it.retryCount shouldEqual 1
        }
    }

    @Test
    fun failed() {
        host.apply {
            isUp = true
            retryCount = 1
        }
        host.hasFailed()
        host.let {
            it.isUp shouldEqual false
            it.retryCount shouldEqual 1
        }
    }
}
