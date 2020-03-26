package com.algolia.search.model.insights

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.helper.toEventName
import com.algolia.search.model.Raw
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer

/**
 * [EventName] of an [InsightsEvent]. Can't be a blank or empty string.
 */
@Serializable(EventName.Companion::class)
public data class EventName(override val raw: String) : Raw<String> {

    init {
        if (raw.isBlank()) throw EmptyStringException("EventName")
        if (raw.length > 64) throw IllegalArgumentException("EventName length can't be superior to 64 characters.")
    }

    companion object : KSerializer<EventName> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: EventName) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): EventName {
            return serializer.deserialize(decoder).toEventName()
        }
    }
}
