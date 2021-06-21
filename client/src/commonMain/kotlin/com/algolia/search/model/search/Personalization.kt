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
    @SerialName(KeyScore) val scoreOrNull: Int? = null,
    /**
     * Personalization Ranking Score
     */
    @SerialName(KeyRankingScore) val rankingScoreOrNull: Int? = null,
    /**
     * Personalization Filters Score
     */
    @SerialName(KeyFiltersScore) val filtersScoreOrNull: Int? = null,
) {

    /**
     * Personalization Score
     */
    val score: Int
        get() = requireNotNull(scoreOrNull)

    /**
     * Personalization Ranking Score
     */
    val rankingScore: Int
        get() = requireNotNull(rankingScoreOrNull)

    /**
     * Personalization Filters Score
     */
    val filtersScore: Int
        get() = requireNotNull(filtersScoreOrNull)
}
