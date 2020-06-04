package com.algolia.search.model.search

import com.algolia.search.helper.and
import com.algolia.search.serialize.KeyDistance
import com.algolia.search.serialize.KeyLat
import com.algolia.search.serialize.KeyLng
import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.json

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

    @Serializer(MatchedGeoLocation::class)
    companion object : KSerializer<MatchedGeoLocation> {

        override fun serialize(encoder: Encoder, value: MatchedGeoLocation) {
            val json = json {
                KeyDistance to value.distance
                KeyLat to value.point.latitude
                KeyLng to value.point.longitude
            }

            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): MatchedGeoLocation {
            val json = decoder.asJsonInput().jsonObject

            return MatchedGeoLocation(
                distance = json.getPrimitive(KeyDistance).long,
                point = json.getPrimitive(KeyLat).float and json.getPrimitive(KeyLng).float
            )
        }
    }
}
