package com.algolia.search.model.search

import com.algolia.search.helper.and
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.json


@Serializable
public data class MatchedGeoLocation(
    val point: Point,
    val distance: Long
) {

    @Serializer(MatchedGeoLocation::class)
    internal companion object : KSerializer<MatchedGeoLocation> {

        override fun serialize(encoder: Encoder, obj: MatchedGeoLocation) {
            val json = json {
                KeyDistance to obj.distance
                KeyLat to obj.point.latitude
                KeyLng to obj.point.longitude
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