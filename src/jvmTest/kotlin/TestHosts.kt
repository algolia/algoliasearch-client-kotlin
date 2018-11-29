
import client.ApplicationId
import client.host.computeFallbackHosts
import client.host.randomizeFallbackHosts
import client.host.readHost
import client.host.writeHost
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


@RunWith(JUnit4::class)
class TestHosts {

    private val applicationId = ApplicationId("appId")
    private val host = "algolianet.com"

    @Test
    fun default() {
        assertEquals("${applicationId.string}-dsn.algolia.net", applicationId.readHost)
        assertEquals("${applicationId.string}.algolia.net", applicationId.writeHost)
    }

    @Test
    fun fallbackHosts() {
        val hosts = applicationId.computeFallbackHosts()

        assertTrue { hosts.contains("${applicationId.string}-1.$host") }
        assertTrue { hosts.contains("${applicationId.string}-2.$host") }
        assertTrue { hosts.contains("${applicationId.string}-3.$host") }
        assertEquals(3, hosts.size)
    }

    @Test
    fun random() {
        val result = (0 until 1000).map {
            val hosts = applicationId.computeFallbackHosts()
            val randomized = hosts.randomizeFallbackHosts()

            randomized[0] == "${applicationId.string}-1.$host"
        }
        assertFalse { result.all { it } }
    }
}