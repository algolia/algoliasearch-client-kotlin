package suite

import clientAdmin1
import com.algolia.search.model.Attribute
import com.algolia.search.model.insights.EventName
import com.algolia.search.model.personalization.EventScoring
import com.algolia.search.model.personalization.EventType
import com.algolia.search.model.personalization.FacetScoring
import com.algolia.search.model.personalization.PersonalizationStrategy
import runBlocking
import shouldEqual
import kotlin.test.Test


internal class TestSuitePersonalization {

    @Test
    fun test() {
        runBlocking {
            val strategy = PersonalizationStrategy(
                eventsScoring = mapOf(
                    EventName("Add to cart") to EventScoring(EventType.Conversion, 50),
                    EventName("Purchase") to EventScoring(EventType.Conversion, 100)
                ),
                facetsScoring = mapOf(
                    Attribute("brand") to FacetScoring(100),
                    Attribute("categories") to FacetScoring(10)
                )
            )
            clientAdmin1.getPersonalizationStrategy().let {
                it.eventsScoring shouldEqual strategy.eventsScoring
                it.facetsScoring shouldEqual strategy.facetsScoring
            }
        }
    }
}