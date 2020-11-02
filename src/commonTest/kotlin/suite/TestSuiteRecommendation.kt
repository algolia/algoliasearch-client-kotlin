package suite

import clientRecommendation
import com.algolia.search.model.recommendation.EventScoring
import com.algolia.search.model.recommendation.FacetScoring
import com.algolia.search.model.recommendation.PersonalizationStrategy
import com.algolia.search.model.recommendation.SetPersonalizationStrategyResponse
import io.ktor.client.features.ClientRequestException
import io.ktor.http.HttpStatusCode
import runBlocking
import shouldEqual
import kotlin.test.Test

internal class TestSuiteRecommendation {

    @Test
    fun testRecommendationClient() {
        runBlocking {
            clientRecommendation.getPersonalizationStrategy()
        }
    }

    @Test
    fun testSetStrategyPayload() {
        runBlocking {
            val strategy = PersonalizationStrategy(
                eventsScoring = listOf(
                    EventScoring("Add to cart", "conversion", 50),
                    EventScoring("Purchase", "conversion", 100)
                ),
                facetsScoring = listOf(
                    FacetScoring("brand", 100),
                    FacetScoring("categories", 10)
                ),
                personalizationImpact = 0
            )

            val response = SetPersonalizationStrategyResponse(200, "Strategy was successfully updated")

            try {
                clientRecommendation.setPersonalizationStrategy(strategy) shouldEqual response
            } catch (e: ClientRequestException) {
                // The personalization API is now limiting the number of setPersonalizationStrategy()` successful calls
                // to 15 per day. If the 429 error is returned, the response is considered a "success".
                if (e.response.status != HttpStatusCode.TooManyRequests) throw e
            }
            clientRecommendation.getPersonalizationStrategy() shouldEqual strategy
        }
    }
}
