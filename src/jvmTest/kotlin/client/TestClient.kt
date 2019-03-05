package client

import com.algolia.search.client.*
import com.algolia.search.helper.toAPIKey
import com.algolia.search.helper.toApplicationID
import io.ktor.client.engine.okhttp.OkHttp
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestClient {

    private val configuration = Configuration(
        apiKey = "apiKey".toAPIKey(),
        applicationID = "appID".toApplicationID(),
        hosts = listOf("host")
    )

    private val engine = OkHttp.create()

    @Test
    fun search() {
        ClientSearch(configuration, engine) shouldEqual configuration
    }

    @Test
    fun insights() {
        ClientInsights(configuration) shouldEqual configuration
        ClientInsights(configuration, engine) shouldEqual configuration
    }

    @Test
    fun analytics() {
        ClientAnalytics(configuration) shouldEqual configuration
        ClientAnalytics(configuration, engine) shouldEqual configuration
    }

    private infix fun ConfigurationInterface.shouldEqual(expected: Configuration) {
        expected.let {
            apiKey shouldEqual it.apiKey
            applicationID shouldEqual it.applicationID
            hosts shouldEqual it.hosts
            logLevel shouldEqual it.logLevel
            readTimeout shouldEqual it.readTimeout
            writeTimeout shouldEqual it.writeTimeout
        }
    }
}