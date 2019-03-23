package suite

import clientAdmin1
import com.algolia.search.client.ClientSearch
import com.algolia.search.configuration.CallType
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.configuration.RetryableHost
import com.algolia.search.model.Time
import io.ktor.client.features.logging.LogLevel
import runBlocking
import kotlin.test.Test


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
            val timer = Time.getCurrentTimeMillis()

            client.apply {
                repeat(10) {
                    listIndices()
                }
            }
            println("Time elapsed in milliseconds: ${(Time.getCurrentTimeMillis() - timer)}")
        }
    }
}