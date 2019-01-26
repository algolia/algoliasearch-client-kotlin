package com.algolia.search.saas.model.api_key

import com.algolia.search.saas.model.IndexName
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetAPIKey(
    @SerialName("value") val apiKey: APIKey,
    val validity: Long,
    @SerialName("acl") val rights: List<ACL>,
    @Optional val createdAt: Long? = null,
    @Optional val description: String? = null,
    @Optional val index: IndexName? = null
)