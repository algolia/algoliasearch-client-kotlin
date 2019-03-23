package suite

import com.algolia.search.configuration.CallType
import com.algolia.search.client.ClientSearch
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.configuration.RetryableHost
import io.ktor.client.features.logging.LogLevel
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestSuiteDNS {

    @Test
    fun test() {
        runBlocking {
            val applicationID = clientAdmin1.applicationID

            val client = ClientSearch(
                ConfigurationSearch(
                    applicationID,
                    clientAdmin1.apiKey,
                    hosts = listOf(
                        RetryableHost("algolia.biz", CallType.Read),
                        RetryableHost("$applicationID-1.algolianet.com"),
                        RetryableHost("$applicationID-2.algolianet.com"),
                        RetryableHost("$applicationID-3.algolianet.com")
                    ),
                    logLevel = LogLevel.INFO
                )
            )
            val timer = System.currentTimeMillis()

            client.apply {
                repeat(10) {
                    listIndices()
                }
            }
            println("Time elapsed in milliseconds: ${(System.currentTimeMillis() - timer)}")
        }
    }
}