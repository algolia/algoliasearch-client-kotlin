package com.algolia.search.model.insights

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.helper.toEventName
import com.algolia.search.model.internal.Raw
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * [EventName] of an [InsightsEvent]. Can't be a blank or empty string.
 */
@Serializable(EventName.Companion::class)
public data class EventName(override val raw: String) : Raw<String> {

    init {
        if (raw.isBlank()) throw EmptyStringException("EventName")
        if (raw.length > 64) throw IllegalArgumentException("EventName length can't be superior to 64 characters.")
    }

    public companion object : KSerializer<EventName> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: EventName) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): EventName {
            return serializer.deserialize(decoder).toEventName()
        }
    }
}
