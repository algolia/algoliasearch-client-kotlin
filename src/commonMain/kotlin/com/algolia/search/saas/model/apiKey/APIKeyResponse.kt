package com.algolia.search.saas.model.apiKey

import com.algolia.search.saas.model.APIKey
import com.algolia.search.saas.model.IndexName
import com.algolia.search.saas.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class APIKeyResponse(
    @SerialName(KeyValue) val apiKey: APIKey,
    @SerialName(KeyValidity) val validity: Long,
    @SerialName(KeyAcl) val rights: List<ACL>,
    @Optional @SerialName(KeyCreatedAt) val createdAt: Long? = null,
    @Optional @SerialName(KeyDescription) val description: String? = null,
    @Optional @SerialName(KeyIndex) val index: IndexName? = null
)