package com.algolia.search.model.internal.request

import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RequestAPIKey(
    @SerialName(Key.Acl) val ACLs: List<ACL>? = null,
    @SerialName(Key.Indexes) val indices: List<IndexName>? = null,
    @SerialName(Key.Description) val description: String? = null,
    @SerialName(Key.MaxHitsPerQuery) val maxHitsPerQuery: Int? = null,
    @SerialName(Key.MaxQueriesPerIPPerHour) val maxQueriesPerIPPerHour: Int? = null,
    @SerialName(Key.Validity) val validity: Long? = null,
    @SerialName(Key.QueryParameters) val query: String? = null,
    @SerialName(Key.Referers) val referers: List<String>? = null
)
