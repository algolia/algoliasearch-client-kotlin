package com.algolia.search.model.request

import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RequestAPIKey(
    @Optional @SerialName(KeyAcl) val rights: List<ACL>? = null,
    @Optional @SerialName(KeyIndexes) val indices: List<IndexName>? = null,
    @Optional @SerialName(KeyDescription) val description: String? = null,
    @Optional @SerialName(KeyMaxHitsPerQuery) val maxHitsPerQuery: Int? = null,
    @Optional @SerialName(KeyMaxQueriesPerIPPerHour) val maxQueriesPerIPPerHour: Int? = null,
    @Optional @SerialName(KeyValidity) val validity: Long? = null,
    @Optional @SerialName(KeyQueryParameters) val query: String? = null,
    @Optional @SerialName(KeyReferers) val referers: List<String>? = null,
    @Optional @SerialName(KeyRestrictSources) val restrictSources: String? = null
)