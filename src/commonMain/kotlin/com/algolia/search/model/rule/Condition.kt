package com.algolia.search.model.rule

import com.algolia.search.serialize.KeyAnchoring
import com.algolia.search.serialize.KeyContext
import com.algolia.search.serialize.KeyPattern
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class Condition(
    @SerialName(KeyAnchoring) val anchoring: Anchoring,
    @SerialName(KeyPattern) val pattern: Pattern,
    @SerialName(KeyContext) val context: String? = null
)