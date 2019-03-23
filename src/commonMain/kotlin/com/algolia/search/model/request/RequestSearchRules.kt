package com.algolia.search.model.request

import com.algolia.search.model.rule.Anchoring
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class RequestSearchRules(
    @SerialName(KeyQuery) val query: String? = null,
    @SerialName(KeyAnchoring) val anchoring: Anchoring? = null,
    @SerialName(KeyContext) val context: String? = null,
    @SerialName(KeyPage) val page: Int? = null,
    @SerialName(KeyHitsPerPage) val hitsPerPage: Int? = null,
    @SerialName(KeyEnabled) val enabled: Boolean? = null
)