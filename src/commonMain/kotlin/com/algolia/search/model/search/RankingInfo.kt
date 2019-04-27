package com.algolia.search.model.search

import com.algolia.search.model.settings.RankingCriterion
import com.algolia.search.model.settings.Settings
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class RankingInfo(
    /**
     * Present and set to true if a query rule promoted the hit.
     */
    @SerialName(KeyPromoted) val promoted: Boolean,
    /**
     * Number of typos encountered when matching the record.
     * Corresponds to the [RankingCriterion.Typo] in the ranking formula.
     */
    @SerialName(KeyNbTypos) val nbTypos: Int,
    /**
     * Position of the most important matched attribute in the attributes to index list.
     * Corresponds to the [RankingCriterion.Attribute] in the ranking formula.
     */
    @SerialName(KeyFirstMatchedWord) val firstMatchedWord: Int,
    /**
     * When the query contains more than one word, the sum of the distances between matched words (in meters).
     * Corresponds to the [RankingCriterion.Proximity] in the ranking formula.
     */
    @SerialName(KeyProximityDistance) val proximityDistance: Int,
    /**
     * Custom ranking for the object, expressed as a single integer value.
     * This field is internal to Algolia and shouldn’t be relied upon, as it will eventually be removed
     * from the public API.
     */
    @SerialName(KeyUserScore) val userScore: Int,
    /**
     * Distance between the geo location in the search query and the best matching geo location in the record,
     * divided by the geo precision (in meters).
     */
    @SerialName(KeyGeoDistance) val geoDistance: Int,
    /**
     * Precision used when computing the geo distance, in meters.
     * All distances will be floored to a multiple of this precision.
     */
    @SerialName(KeyGeoPrecision) val geoPrecision: Int,
    /**
     * Number of exactly matched words. If [Query.alternativesAsExact] or [Settings.alternativesAsExact] is set,
     * it may include plurals and/or synonyms.
     */
    @SerialName(KeyNbExactWords) val nbExactWords: Int,
    /**
     * Number of matched words, including prefixes and typos.
     */
    @SerialName(KeyWords) val words: Int,
    /**
     * This field is reserved for advanced usage. It will be zero in most cases.
     */
    @SerialName(KeyFilters) val filters: Int,
    /**
     * Geo location that matched the query. Only returned if [Query.aroundRadius] is used.
     */
    @SerialName(KeyMatchedGeoLocation) val matchedGeoLocation: MatchedGeoLocation? = null
)