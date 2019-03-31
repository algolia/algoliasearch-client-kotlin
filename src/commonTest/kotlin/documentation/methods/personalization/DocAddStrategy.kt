package documentation.methods.personalization

import com.algolia.search.model.Attribute
import com.algolia.search.model.insights.EventName
import com.algolia.search.model.personalization.EventScoring
import com.algolia.search.model.personalization.EventType
import com.algolia.search.model.personalization.FacetScoring
import com.algolia.search.model.personalization.PersonalizationStrategy
import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocAddStrategy : TestDocumentation() {

//    suspend fun ClientSearch.setPersonalizationStrategy(
//        #{strategy}: __PersonalizationStrategy__,
//        requestOptions: __RequestOptions?__ = null
//    ): Revision

    @Test
    fun addStrategy() {
        runBlocking {
            val strategy = PersonalizationStrategy(
                facetsScoring = mapOf(
                    Attribute("brand") to FacetScoring(100),
                    Attribute("categories") to FacetScoring(10)
                ),
                eventsScoring = mapOf(
                    EventName("Add to cart") to EventScoring(EventType.Conversion, 50),
                    EventName("Purchase") to EventScoring(EventType.Conversion, 100)
                )
            )
            client.setPersonalizationStrategy(strategy)
        }
    }
}