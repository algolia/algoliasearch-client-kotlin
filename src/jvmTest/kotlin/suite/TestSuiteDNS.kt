package suite

import com.algolia.search.client.ClientSearch
import com.algolia.search.client.Configuration
import com.algolia.search.helper.toAPIKey
import com.algolia.search.helper.toApplicationID
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
            val applicationID = System.getenv("ALGOLIA_APPLICATION_ID_1").toApplicationID()

            val client = ClientSearch(
                Configuration(
                    applicationID,
                    System.getenv("ALGOLIA_ADMIN_KEY_1").toAPIKey(),
                    hosts = listOf(
                        "https://algolia.biz",
                        "https://$applicationID-1.algolianet.com",
                        "https://$applicationID-2.algolianet.com",
                        "https://$applicationID-3.algolianet.com"
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