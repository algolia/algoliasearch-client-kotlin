package com.algolia.search.model.search

import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Personalization(
    /**
     * Personalization Score
     */
    @SerialName(Key.Score) val scoreOrNull: Int? = null,
    /**
     * Personalization Ranking Score
     */
    @SerialName(Key.RankingScore) val rankingScoreOrNull: Int? = null,
    /**
     * Personalization Filters Score
     */
    @SerialName(Key.FiltersScore) val filtersScoreOrNull: Int? = null,
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
