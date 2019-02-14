package suite

import com.algolia.search.client.ClientSearch
import com.algolia.search.client.Configuration
import com.algolia.search.toAPIKey
import com.algolia.search.toApplicationID
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
            val timer = System.currentTimeMillis()

            val client = ClientSearch(
                Configuration(
                    applicationID,
                    System.getenv("ALGOLIA_ADMIN_KEY_1").toAPIKey(),
                    hosts = listOf(
                        "https://algolia.biz",
                        "https://$applicationID-1.algolianet.com",
                        "https://$applicationID-2.algolianet.com",
                        "https://$applicationID-3.algolianet.com"
                    )
                )
            )

            client.apply {
                repeat(10) {
                    listIndexes()
                }
            }
            (System.currentTimeMillis() - timer).let {
                println("Time elapsed in milliseconds: $it")
            }
        }
    }
}