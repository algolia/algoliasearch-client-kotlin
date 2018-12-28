import client.data.ApplicationId
import client.host.computeHosts
import client.host.randomize
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
        assertEquals("https://${applicationId.string}-dsn.algolia.net", applicationId.readHost)
        assertEquals("https://${applicationId.string}.algolia.net", applicationId.writeHost)
    }

    @Test
    fun computeHosts() {
        val hosts = applicationId.computeHosts()

        assertTrue { hosts.contains("https://${applicationId.string}-1.$host") }
        assertTrue { hosts.contains("https://${applicationId.string}-2.$host") }
        assertTrue { hosts.contains("https://${applicationId.string}-3.$host") }
        assertEquals(3, hosts.size)
    }

    @Test
    fun random() {
        val result = (0 until 1000).map {
            val hosts = applicationId.computeHosts()
            val randomized = hosts.randomize()

            randomized[0] == "${applicationId.string}-1.$host"
        }
        assertFalse { result.all { it } }
    }
}