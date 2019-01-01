package host

import client.data.ApplicationId
import client.host.computeHosts
import client.host.randomize
import client.host.readHost
import client.host.writeHost
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeFalse
import shouldBeTrue
import shouldEqual


@RunWith(JUnit4::class)
class TestHosts {

    private val applicationId = ApplicationId("appId")
    private val host = "algolianet.com"

    @Test
    fun default() {
        applicationId.readHost shouldEqual "https://$applicationId-dsn.algolia.net"
        applicationId.writeHost shouldEqual "https://$applicationId.algolia.net"
    }

    @Test
    fun computeHosts() {
        val hosts = applicationId.computeHosts()

        hosts.contains("https://$applicationId-1.$host").shouldBeTrue()
        hosts.contains("https://$applicationId-2.$host").shouldBeTrue()
        hosts.contains("https://$applicationId-3.$host").shouldBeTrue()
        hosts.size shouldEqual 3
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