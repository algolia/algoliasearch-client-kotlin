package com.algolia.search.model.personalization

import com.algolia.search.model.Raw
import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.serialize.KeyClick
import com.algolia.search.serialize.KeyConversion
import com.algolia.search.serialize.KeyView
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.StringSerializer

@Serializable(EventType.Companion::class)
sealed class EventType(override val raw: String) : Raw<String> {

    /**
     * Matches an event of type [InsightsEvent.View].
     */
    object View : EventType(KeyView)

    /**
     * Matches an event of type [InsightsEvent.Click].
     */
    object Click : EventType(KeyClick)

    /**
     * Matches an event of type [InsightsEvent.Conversion].
     */
    object Conversion : EventType(KeyConversion)

    data class Other(override val raw: String) : EventType(raw)

    @Serializer(EventType::class)
    companion object : KSerializer<EventType> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: EventType) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): EventType {
            return when (val string = serializer.deserialize(decoder)) {
                KeyView -> View
                KeyClick -> Click
                KeyConversion -> Conversion
                else -> Other(string)
            }
        }
    }
}
