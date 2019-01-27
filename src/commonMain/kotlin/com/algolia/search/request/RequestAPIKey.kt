package com.algolia.search.request

import com.algolia.search.model.ACL
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class RequestAPIKey(
    @Optional @SerialName(KeyAcl) val rights: List<ACL>? = null,
    @Optional @SerialName(KeyIndexes) val indexes: List<IndexName>? = null,
    @Optional @SerialName(KeyDescription) val description: String? = null,
    @Optional @SerialName(KeyMaxHitsPerQuery) val maxHitsPerQuery: Int? = null,
    @Optional @SerialName(KeyMaxQueriesPerIpPerHour) val maxQueriesPerIPPerHour: Int? = null,
    @Optional @SerialName(KeyValidity) val validity: Long? = null,
    @Optional @SerialName(KeyQueryParameters) val query: Query? = null,
    @Optional @SerialName(KeyReferers) val referers: List<String>? = null
)