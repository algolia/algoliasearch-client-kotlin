package com.algolia.search.model.personalization

import com.algolia.search.serialize.KeyScore
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class FacetScoring(
    @SerialName(KeyScore) val score: Int
)