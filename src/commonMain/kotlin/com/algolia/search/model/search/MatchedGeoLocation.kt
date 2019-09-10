package com.algolia.search.model.search

import com.algolia.search.helper.and
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.json
import kotlin.jvm.JvmOverloads


@Serializable
public data class MatchedGeoLocation @JvmOverloads constructor(
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