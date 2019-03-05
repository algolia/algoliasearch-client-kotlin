package com.algolia.search.model.request

import com.algolia.search.model.rule.Anchoring
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RequestSearchRules(
    @Optional @SerialName(KeyQuery) val query: String? = null,
    @Optional @SerialName(KeyAnchoring) val anchoring: Anchoring? = null,
    @Optional @SerialName(KeyContext) val context: String? = null,
    @Optional @SerialName(KeyPage) val page: Int? = null,
    @Optional @SerialName(KeyHitsPerPage) val hitsPerPage: Int? = null,
    @Optional @SerialName(KeyEnabled) val enabled: Boolean? = null
)