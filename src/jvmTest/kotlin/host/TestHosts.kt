package host

import com.algolia.search.host.buildFallbackHosts
import com.algolia.search.host.readHost
import com.algolia.search.host.writeHost
import com.algolia.search.model.ApplicationID
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldEqual


@RunWith(JUnit4::class)
internal class TestHosts {

    private val applicationId = ApplicationID("appId")
    private val host = "algolianet.com"

    @Test
    fun default() {
        applicationId.readHost shouldEqual "https://$applicationId-dsn.algolia.net"
        applicationId.writeHost shouldEqual "https://$applicationId.algolia.net"
    }

    @Test
    fun computeHosts() {
        val hosts = applicationId.buildFallbackHosts()

        hosts.contains("https://$applicationId-1.$host").shouldBeTrue()
        hosts.contains("https://$applicationId-2.$host").shouldBeTrue()
        hosts.contains("https://$applicationId-3.$host").shouldBeTrue()
        hosts.size shouldEqual 3
    }
}