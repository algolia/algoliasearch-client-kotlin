
import client.data.ApplicationId
import client.host.computeHosts
import client.host.randomize
import client.host.readHost
import client.host.writeHost
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class TestHosts {

    private val applicationId = ApplicationId("appId")
    private val host = "algolianet.com"

    @Test
    fun default() {
        "https://$applicationId-dsn.algolia.net" shouldEqual applicationId.readHost
        "https://$applicationId.algolia.net" shouldEqual applicationId.writeHost
    }

    @Test
    fun computeHosts() {
        val hosts = applicationId.computeHosts()

        hosts.contains("https://$applicationId-1.$host").shouldBeTrue()
        hosts.contains("https://$applicationId-2.$host").shouldBeTrue()
        hosts.contains("https://$applicationId-3.$host").shouldBeTrue()
        3 shouldEqual hosts.size
    }

    @Test
    fun random() {
        val result = (0 until 1000).map {
            val hosts = applicationId.computeHosts()
            val randomized = hosts.randomize()

            randomized[0] == "$applicationId-1.$host"
        }
        result.all { it }.shouldBeFalse()
    }
}