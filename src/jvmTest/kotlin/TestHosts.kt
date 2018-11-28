import client.Hosts
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
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

        assertEquals("appId-1.algolianet.com", hosts.fallbackHosts[0])
        assertEquals("appId-2.algolianet.com", hosts.fallbackHosts[1])
        assertEquals("appId-3.algolianet.com", hosts.fallbackHosts[2])
    }

    @Test
    fun fallback() {
        val hosts = Hosts("appId")

        assertTrue { hosts.fallbackHosts.contains(hosts.fallback) }
    }

    @Test
    fun random() {
        val hosts = Hosts("appId")

        repeat(1000) {
            assertTrue { hosts.fallbackHosts.contains(hosts.getRandomFallbackHost()) }
        }
    }
}