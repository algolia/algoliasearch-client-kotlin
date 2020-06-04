package model.insights

import attributeA
import com.algolia.search.helper.toEventName
import com.algolia.search.helper.toQueryID
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.insights.InsightsEvent
import indexA
import objectIDA
import shouldEqual
import shouldFailWith
import kotlin.test.Test

internal class TestInsightsEvent {

    private val eventName = "eventName".toEventName()
    private val filter = Filter.Facet(attributeA, "value")

    @Test
    fun positionsAreRequired() {
        shouldFailWith<IllegalArgumentException> {
            InsightsEvent.Click(
                eventName = eventName,
                indexName = indexA,
                queryID = "queryID".toQueryID()
            )
        }
    }

    @Test
    fun objectIDsSizeLimit() {
        val underTheSizeLimit = (0 until 19).map { objectIDA }
        val equalToTheSizeLimit = (0 until 20).map { objectIDA }
        val overTheSizeLimit = (0 until 21).map { objectIDA }

        InsightsEvent.Resources.ObjectIDs(underTheSizeLimit).objectIDs shouldEqual underTheSizeLimit
        InsightsEvent.Resources.ObjectIDs(equalToTheSizeLimit).objectIDs shouldEqual equalToTheSizeLimit
        shouldFailWith<IllegalArgumentException> { InsightsEvent.Resources.ObjectIDs(overTheSizeLimit) }
    }

    @Test
    fun filtersSizeLimit() {
        val underTheSizeLimit = (0 until 9).map { filter }
        val equalToTheSizeLimit = (0 until 10).map { filter }
        val overTheSizeLimit = (0 until 11).map { filter }

        InsightsEvent.Resources.Filters(underTheSizeLimit).filters shouldEqual underTheSizeLimit
        InsightsEvent.Resources.Filters(equalToTheSizeLimit).filters shouldEqual equalToTheSizeLimit
        shouldFailWith<IllegalArgumentException> { InsightsEvent.Resources.Filters(overTheSizeLimit) }
    }
}
