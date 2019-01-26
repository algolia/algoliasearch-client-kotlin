package com.algolia.search.saas.model

import com.algolia.search.saas.model.search.Query
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class APIKeyCreate(
    @Optional @SerialName("acl") val rights: List<ACL>? = null,
    @Optional val indexes: List<IndexName>? = null,
    @Optional val description: String? = null,
    @Optional val maxHitsPerQuery: Int? = null,
    @Optional val maxQueriesPerIPPerHour: Int? = null,
    @Optional val validity: Long? = null,
    @Optional @SerialName("queryParameters") val query: Query? = null,
    @Optional val referers: List<String>? = null
)