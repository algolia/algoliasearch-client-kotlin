package transport

import com.algolia.search.configuration.CallType
import com.algolia.search.configuration.RetryableHost
import com.algolia.search.helper.toApplicationID
import com.algolia.search.transport.hasFailed
import com.algolia.search.transport.hasTimedOut
import com.algolia.search.transport.hosts
import com.algolia.search.transport.reset
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestRetryableHost {

    private val host = RetryableHost("url", CallType.Write)
    private val applicationID = "appID".toApplicationID()

    @Test
    fun searchHosts() {
        applicationID.hosts shouldEqual listOf(
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