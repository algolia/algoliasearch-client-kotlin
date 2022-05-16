package com.algolia.search.model.search

import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonPrimitive

/**
 * Define the maximum radius for a geo search (in meters).
 * This setting only works within the context of a radial (circular) geo search, enabled by aroundLatLngViaIP or
 * aroundLatLng.
 */
@Serializable(AroundRadius.Companion::class)
public sealed class AroundRadius(override val raw: String) : Raw<String> {

    /**
     *  Disables the radius logic, allowing all results to be returned, regardless of distance. =
     *  Ranking is still based on proximity to the central axis point. This option is faster than specifying a high integer value.
     */
    public object All : AroundRadius(Key.All)

    /**
     * Integer value (in meters) representing the radius around the coordinates specified during the query.
     */
    public data class InMeters(val radius: Int) : AroundRadius(radius.toString())

    public data class Other(override val raw: String) : AroundRadius(raw)

    override fun toString(): String {
        return raw
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(AroundRadius::class)
    public companion object : KSerializer<AroundRadius> {

        override fun serialize(encoder: Encoder, value: AroundRadius) {
            val element = when (value) {
                is InMeters -> JsonPrimitive(value.radius)
                else -> JsonPrimitive(value.raw)
            }

            encoder.asJsonOutput().encodeJsonElement(element)
        }

        override fun deserialize(decoder: Decoder): AroundRadius {
            val element = decoder.asJsonInput()

            return when {
                element.jsonPrimitive.intOrNull != null -> InMeters(element.jsonPrimitive.int)
                else -> when (element.jsonPrimitive.content) {
                    Key.All -> All
                    else -> Other(element.jsonPrimitive.content)
                }
            }
        }
    }
}
