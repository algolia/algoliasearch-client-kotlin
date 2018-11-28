import client.Hosts
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


@RunWith(JUnit4::class)
class TestHosts {

    @Test
    fun default() {
        val hosts = Hosts("appId")

        assertEquals("appId-dsn.algolia.net", hosts.default)
    }

    @Test
    fun fallbackHosts() {
        val hosts = Hosts("appId")

        assertTrue { hosts.fallbackHosts.contains("appId-1.algolianet.com") }
        assertTrue { hosts.fallbackHosts.contains("appId-2.algolianet.com") }
        assertTrue { hosts.fallbackHosts.contains("appId-3.algolianet.com") }
        assertEquals(3, hosts.fallbackHosts.size)
    }

    @Test
    fun random() {

        val result = (0 until 1000).map {
            val hosts = Hosts("appId")
            hosts.fallbackHosts[0] == "appId-1.algolianet.com"
        }
        assertFalse { result.all { it } }
    }
}