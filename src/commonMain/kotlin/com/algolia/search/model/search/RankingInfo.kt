package com.algolia.search.model.search

import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class RankingInfo(
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
    @SerialName(KeyMatchedGeoLocation) val matchedGeoLocation: MatchedGeoLocation? = null
)