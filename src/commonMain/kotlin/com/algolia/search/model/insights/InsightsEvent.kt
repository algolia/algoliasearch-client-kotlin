package com.algolia.search.model.insights

import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.QueryID
import com.algolia.search.filter.FilterFacet
import com.algolia.search.serialize.*
import kotlinx.serialization.Encoder
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray


@Serializable(InsightsEvent.Companion::class)
public sealed class InsightsEvent {

    abstract val eventName: EventName
    abstract val indexName: IndexName
    abstract val userToken: UserToken?
    abstract val timestamp: Long?
    abstract val queryID: QueryID?
    abstract val resources: Resources?

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

        public data class Filters(val filters: List<FilterFacet>) : Resources() {

            init {
                if (filters.size > 10)
                    throw IllegalArgumentException("You can't send more than 10 filters for a single event at at time.")
            }
        }
    }

    @Serializer(InsightsEvent::class)
    internal companion object : SerializationStrategy<InsightsEvent> {

        private infix fun JsonObjectBuilder.stringify(resources: Resources?) {
            when (resources) {
                is Resources.ObjectIDs -> KeyObjectIDs to jsonArray { resources.objectIDs.forEach { +it.raw } }
                is Resources.Filters -> KeyFilters to jsonArray { resources.filters.forEach { +it.expression } }
            }
        }

        private infix fun JsonObjectBuilder.eventType(event: InsightsEvent) {
            KeyEventType to when (event) {
                is InsightsEvent.Click -> KeyClick
                is InsightsEvent.View -> KeyView
                is InsightsEvent.Conversion -> KeyConversion
            }
        }

        override fun serialize(encoder: Encoder, obj: InsightsEvent) {
            val json = json {
                this eventType obj
                KeyEventName to obj.eventName.raw
                obj.timestamp?.let { KeyTimestamp to it }
                KeyIndex to obj.indexName.raw
                obj.userToken?.let { KeyUserToken to it.raw }
                obj.queryID?.let { KeyQueryID to it.raw }
                this stringify obj.resources
                when (obj) {
                    is Click -> obj.positions?.let { KeyPositions to jsonArray { it.forEach { +(it as Number) } } }
                }
            }
            encoder.asJsonOutput().encodeJson(json)
        }
    }
}