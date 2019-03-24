package suite

import clientAdmin1
import com.algolia.search.model.Attribute
import com.algolia.search.model.insights.EventName
import com.algolia.search.model.personalization.EventScoring
import com.algolia.search.model.personalization.EventType
import com.algolia.search.model.personalization.FacetScoring
import com.algolia.search.model.personalization.PersonalizationStrategy
import runBlocking
import kotlin.test.Test


internal class TestSuitePersonalization {

    @Test
    fun test() {
        runBlocking {
            clientAdmin1.getPersonalizationStrategy()
            clientAdmin1.setPersonalizationStrategy(
                PersonalizationStrategy(
                    eventsScoring = mapOf(
                        EventName("Add to cart") to EventScoring(EventType.Conversion, 50),
                        EventName("Purchase") to EventScoring(EventType.Conversion, 100)
                    ),
                    facetsScoring = mapOf(
                        Attribute("brand") to FacetScoring(100),
                        Attribute("brand") to FacetScoring(100)
                    )
                )
            )
        }
    }
}