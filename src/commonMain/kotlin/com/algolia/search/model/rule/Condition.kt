package com.algolia.search.model.rule

import com.algolia.search.serialize.KeyAnchoring
import com.algolia.search.serialize.KeyContext
import com.algolia.search.serialize.KeyPattern
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Condition(
    @SerialName(KeyPattern) val pattern: Pattern,
    @SerialName(KeyAnchoring) val anchoring: Anchoring,
    @Optional @SerialName(KeyContext) val context: String? = null
)