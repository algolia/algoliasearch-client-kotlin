package com.algolia.search.model.search

import com.algolia.search.helper.and
import com.algolia.search.serialize.KeyDistance
import com.algolia.search.serialize.KeyLat
import com.algolia.search.serialize.KeyLng
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.float
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long
import kotlinx.serialization.json.put

@Serializable
public data class MatchedGeoLocation(
    /**
     * Latitude and Longitude of the matched location
     */
    val point: Point,
    /**
     * Only returned if [Query.aroundRadius] is used.
     */
    val distance: Long? = null
) {

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(MatchedGeoLocation::class)
    public companion object : KSerializer<MatchedGeoLocation> {

        override fun serialize(encoder: Encoder, value: MatchedGeoLocation) {
            val json = buildJsonObject {
                put(KeyDistance, value.distance)
                put(KeyLat, value.point.latitude)
                put(KeyLng, value.point.longitude)
            }

            encoder.asJsonOutput().encodeJsonElement(json)
        }

        override fun deserialize(decoder: Decoder): MatchedGeoLocation {
            val json = decoder.asJsonInput().jsonObject

            return MatchedGeoLocation(
                distance = json.getValue(KeyDistance).jsonPrimitive.long,
                point = json.getValue(KeyLat).jsonPrimitive.float and json.getValue(KeyLng).jsonPrimitive.float
            )
        }
    }
}
