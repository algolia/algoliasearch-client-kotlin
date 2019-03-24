package com.algolia.search.model.rule

import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RuleQuery(
    @SerialName(KeyQuery) val query: String? = null,
    @SerialName(KeyAnchoring) val anchoring: Anchoring? = null,
    @SerialName(KeyContext) val context: String? = null,
    @SerialName(KeyPage) val page: Int? = null,
    @SerialName(KeyHitsPerPage) val hitsPerPage: Int? = null,
    @SerialName(KeyEnabled) val enabled: Boolean? = null
)