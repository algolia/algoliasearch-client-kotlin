package suite

import clientRecommendation
import com.algolia.search.model.recommendation.EventsScoring
import com.algolia.search.model.recommendation.FacetsScoring
import com.algolia.search.model.recommendation.PersonalizationStrategy
import com.algolia.search.model.recommendation.SetPersonalizationStrategyResponse
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
                    EventsScoring("Add to cart", "conversion", 50),
                    EventsScoring("Purchase", "conversion", 100)
                ),
                facetsScoring = listOf(
                    FacetsScoring("brand", 100),
                    FacetsScoring("categories", 10)
                ),
                personalizationImpact = 0
            )

            val response = SetPersonalizationStrategyResponse(200, "Strategy was successfully updated")

            clientRecommendation.setPersonalizationStrategy(strategy).let {
                it.shouldEqual(response)
            }
            clientRecommendation.getPersonalizationStrategy().let {
                it.shouldEqual(strategy)
            }
        }
    }
}