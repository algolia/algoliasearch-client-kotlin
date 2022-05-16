package transport

import clientAdmin1
import com.algolia.search.configuration.CallType
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.configuration.RetryableHost
import com.algolia.search.transport.internal.Transport
import kotlinx.coroutines.test.runTest
import shouldContainAll
import shouldEqual
import kotlin.test.Test

internal class TestCallableHosts {

    private val configuration = ConfigurationSearch(clientAdmin1.applicationID, clientAdmin1.apiKey)
    private val transport = Transport(configuration, null)
    private val hostRead = RetryableHost("${configuration.applicationID}-dsn.algolia.net", CallType.Read)
    private val hostWrite = RetryableHost("${configuration.applicationID}.algolia.net", CallType.Write)
    private val hostFallback1 = RetryableHost("${configuration.applicationID}-1.algolianet.com")
    private val hostFallback2 = RetryableHost("${configuration.applicationID}-2.algolianet.com")
    private val hostfallback3 = RetryableHost("${configuration.applicationID}-3.algolianet.com")

    @Test
    fun callTypeRead() {
        runTest {
            val hosts = transport.callableHosts(CallType.Read)

            hosts shouldContainAll listOf(
                hostRead,
                hostFallback1,
                hostFallback2,
                hostfallback3
            )
        }
    }

    @Test
    fun callTypeWrite() {
        runTest {
            val hosts = transport.callableHosts(CallType.Write)

            hosts shouldContainAll listOf(
                hostWrite,
                hostFallback1,
                hostFallback2,
                hostfallback3
            )
        }
    }

    @Test
    fun onlyFirstIsUp() {
        runTest {
            transport.hosts.forEach { it.isUp = false }
            transport.hosts.first().isUp = true

            val hosts = transport.callableHosts(CallType.Read)

            hosts shouldEqual listOf(hostRead)
        }
    }

    @Test
    fun onlyLastIsUp() {
        runTest {
            transport.hosts.forEach { it.isUp = false }
            transport.hosts.last().isUp = true

            val hosts = transport.callableHosts(CallType.Read)

            hosts shouldEqual listOf(transport.hosts.last())
        }
    }

    @Test
    fun noneAreUp() {
        runTest {
            transport.hosts.forEach { it.isUp = false }

            val hosts = transport.callableHosts(CallType.Write)

            hosts shouldContainAll listOf(
                hostWrite,
                hostFallback1,
                hostFallback2,
                hostfallback3
            )
        }
    }
}
