package com.algolia.search.model.insights

import com.algolia.search.endpoint.EndpointInsights
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.QueryID
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterConverter
import com.algolia.search.serialize.KeyClick
import com.algolia.search.serialize.KeyConversion
import com.algolia.search.serialize.KeyEventName
import com.algolia.search.serialize.KeyEventType
import com.algolia.search.serialize.KeyFilters
import com.algolia.search.serialize.KeyIndex
import com.algolia.search.serialize.KeyObjectIDs
import com.algolia.search.serialize.KeyPositions
import com.algolia.search.serialize.KeyQueryID
import com.algolia.search.serialize.KeyTimestamp
import com.algolia.search.serialize.KeyUserToken
import com.algolia.search.serialize.KeyView
import com.algolia.search.serialize.asJsonOutput
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray

/**
 * Event that can be send with [EndpointInsights].
 */
@Serializable(InsightsEvent.Companion::class)
public sealed class InsightsEvent {

    public abstract val eventName: EventName
    public abstract val indexName: IndexName
    public abstract val userToken: UserToken?
    public abstract val timestamp: Long?
    public abstract val queryID: QueryID?
    public abstract val resources: Resources?

    public data class View(
        override val eventName: EventName,
        override val indexName: IndexName,
        override val userToken: UserToken? = null,
        override val timestamp: Long? = null,
        override val queryID: QueryID? = null,
        override val resources: Resources? = null
    ) : InsightsEvent()

    public data class Click(
        override val eventName: EventName,
        override val indexName: IndexName,
        override val userToken: UserToken? = null,
        override val timestamp: Long? = null,
        override val queryID: QueryID? = null,
        override val resources: Resources? = null,
        val positions: List<Int>? = null
    ) : InsightsEvent() {

        init {
            if (queryID != null && positions == null)
                throw IllegalArgumentException("Positions are required for a Click event when a queryID is provided")
        }
    }

    public data class Conversion(
        override val eventName: EventName,
        override val indexName: IndexName,
        override val userToken: UserToken? = null,
        override val timestamp: Long? = null,
        override val queryID: QueryID? = null,
        override val resources: Resources? = null
    ) : InsightsEvent()

    public sealed class Resources {

        public data class ObjectIDs(val objectIDs: List<ObjectID>) : Resources() {

            init {
                if (objectIDs.size > 20)
                    throw IllegalArgumentException("You can't send more than 20 objectIDs for a single event at a time.")
            }
        }

        public data class Filters(val filters: List<Filter.Facet>) : Resources() {

            init {
                if (filters.size > 10)
                    throw IllegalArgumentException("You can't send more than 10 filters for a single event at at time.")
            }
        }
    }

    @Serializer(InsightsEvent::class)
    public companion object : KSerializer<InsightsEvent> {

        private infix fun JsonObjectBuilder.stringify(resources: Resources?) {
            when (resources) {
                is Resources.ObjectIDs -> KeyObjectIDs to jsonArray { resources.objectIDs.forEach { +it.raw } }
                is Resources.Filters -> KeyFilters to jsonArray {
                    resources.filters.forEach { filter ->
                        FilterConverter.Legacy(filter).forEach { +it }
                    }
                }
            }
        }

        private infix fun JsonObjectBuilder.eventType(event: InsightsEvent) {
            KeyEventType to when (event) {
                is Click -> KeyClick
                is View -> KeyView
                is Conversion -> KeyConversion
            }
        }

        override fun serialize(encoder: Encoder, value: InsightsEvent) {
            val json = json {
                this eventType value
                KeyEventName to value.eventName.raw
                value.timestamp?.let { KeyTimestamp to it }
                KeyIndex to value.indexName.raw
                value.userToken?.let { KeyUserToken to it.raw }
                value.queryID?.let { KeyQueryID to it.raw }
                this stringify value.resources
                if (value is Click) {
                    value.positions?.let { KeyPositions to jsonArray { it.forEach { +(it as Number) } } }
                }
            }
            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): InsightsEvent {
            throw UnsupportedOperationException("Insight event deserialization is not an expected operation")
        }
    }
}
