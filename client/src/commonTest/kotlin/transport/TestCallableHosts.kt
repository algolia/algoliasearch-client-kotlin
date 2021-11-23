package transport

import clientAdmin1
import com.algolia.search.configuration.CallType
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.configuration.RetryableHost
import com.algolia.search.configuration.internal.extension.edit
import com.algolia.search.transport.internal.Transport
import runTest
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

            hosts shouldEqual listOf(
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

            hosts shouldEqual listOf(
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
            transport.hosts.first().edit { isUp = true }
            transport.hosts.drop(1).forEach { it.edit { isUp = false } }

            val hosts = transport.callableHosts(CallType.Read)

            hosts shouldEqual listOf(hostRead)
        }
    }

    @Test
    fun onlyLastIsUp() {
        runTest {
            transport.hosts.last().edit { isUp = true }
            transport.hosts.dropLast(1).forEach { it.edit { isUp = false } }

            val hosts = transport.callableHosts(CallType.Read)

            hosts shouldEqual listOf(hostfallback3)
        }
    }

    @Test
    fun noneAreUp() {
        runTest {
            transport.hosts.forEach { it.edit { isUp = false } }

            val hosts = transport.callableHosts(CallType.Write)

            hosts shouldEqual listOf(
                hostWrite,
                hostFallback1,
                hostFallback2,
                hostfallback3
            )
        }
    }
}
