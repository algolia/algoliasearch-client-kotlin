package com.algolia.search.model.request

import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.serialize.KeyAcl
import com.algolia.search.serialize.KeyDescription
import com.algolia.search.serialize.KeyIndexes
import com.algolia.search.serialize.KeyMaxHitsPerQuery
import com.algolia.search.serialize.KeyMaxQueriesPerIPPerHour
import com.algolia.search.serialize.KeyQueryParameters
import com.algolia.search.serialize.KeyReferers
import com.algolia.search.serialize.KeyValidity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RequestAPIKey(
    @SerialName(KeyAcl) val ACLs: List<ACL>? = null,
    @SerialName(KeyIndexes) val indices: List<IndexName>? = null,
    @SerialName(KeyDescription) val description: String? = null,
    @SerialName(KeyMaxHitsPerQuery) val maxHitsPerQuery: Int? = null,
    @SerialName(KeyMaxQueriesPerIPPerHour) val maxQueriesPerIPPerHour: Int? = null,
    @SerialName(KeyValidity) val validity: Long? = null,
    @SerialName(KeyQueryParameters) val query: String? = null,
    @SerialName(KeyReferers) val referers: List<String>? = null
)
