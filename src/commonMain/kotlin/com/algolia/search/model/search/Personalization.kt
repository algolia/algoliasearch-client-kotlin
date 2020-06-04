package com.algolia.search.model.search

import com.algolia.search.serialize.KeyFiltersScore
import com.algolia.search.serialize.KeyRankingScore
import com.algolia.search.serialize.KeyScore
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Personalization(
    /**
     * Personalization Score
     */
    @SerialName(KeyScore) val score: Int,
    /**
     * Personalization Ranking Score
     */
    @SerialName(KeyRankingScore) val rankingScore: Int,
    /**
     * Personalization Filters Score
     */
    @SerialName(KeyFiltersScore) val filtersScore: Int
)
