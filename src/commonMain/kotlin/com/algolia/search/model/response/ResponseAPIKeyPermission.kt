package com.algolia.search.model.response

import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseAPIKeyPermission(
    @SerialName(KeyValue) val apiKey: APIKey,
    @SerialName(KeyValidity) val validity: Long,
    @SerialName(KeyAcl) val rights: List<ACL>,
    @Optional @SerialName(KeyCreatedAt) val createdAt: Long? = null,
    @Optional @SerialName(KeyDescription) val description: String? = null,
    @Optional @SerialName(KeyIndex) val index: IndexName? = null,
    @Optional @SerialName(KeyIndexes) val indexes: List<IndexName>? = null
)