package model.insights

import ObjectData
import attributeA
import com.algolia.search.helper.toEventName
import com.algolia.search.helper.toQueryID
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.Key
import indexA
import objectIDA
import shouldEqual
import shouldFailWith
import kotlin.test.Test
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.put

internal class TestInsightsEvent {

    private val eventName = "eventName".toEventName()
    private val filter = Filter.Facet(attributeA, "value")
    private val conversionEventWithObjectData = InsightsEvent.Conversion(
        eventName = eventName,
        indexName = indexA,
        objectData = listOf(
            ObjectData(price = "10", queryID = "queryID".toQueryID(), quantity = 1),
            ObjectData(price = "20", queryID = "queryID2".toQueryID(), quantity = 2)
        )
    )

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

    @Test
    fun objectDataSerializer() {
        JsonNoDefaults.encodeToJsonElement(InsightsEvent.serializer(), conversionEventWithObjectData).jsonObject shouldEqual
            buildJsonObject {
                put(Key.EventType, Key.Conversion)
                put(Key.EventName, eventName.raw)
                put(Key.Index, indexA.raw)
                put(Key.ObjectData, buildJsonArray {
                    add(
                        buildJsonObject {
                            put("price", "10")
                            put("queryID", "queryID")
                            put("quantity", 1)
                        }
                    )
                    add(
                        buildJsonObject {
                            put("price", "20")
                            put("queryID", "queryID2")
                            put("quantity", 2)
                        }
                    )
                })
            }
    }
}
