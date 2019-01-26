package com.algolia.search.saas.data.search

import com.algolia.search.saas.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RankingInfo(
    @SerialName(KeyPromoted) val promoted: Boolean,
    @SerialName(KeyNbTypos) val nbTypos: Int,
    @SerialName(KeyFirstMatchedWord) val firstMatchedWord: Int,
    @SerialName(KeyProximityDistance) val proximityDistance: Int,
    @SerialName(KeyUserScore) val userScore: Int,
    @SerialName(KeyGeoDistance) val geoDistance: Int,
    @SerialName(KeyGeoPrecision) val geoPrecision: Int,
    @SerialName(KeyNbExactWords) val nbExactWords: Int,
    @SerialName(KeyWords) val words: Int,
    @SerialName(KeyFilters) val filters: Int,
    @Optional @SerialName(KeyMatchedGeoLocation) val matchedGeoLocation: Int? = null
) {

    data class MatchedGeoLocation(
        @SerialName(KeyLat) val latitude: Double,
        @SerialName(KeyLng) val longitude: Double,
        @SerialName(KeyDistance) val distance: Long
    )
}