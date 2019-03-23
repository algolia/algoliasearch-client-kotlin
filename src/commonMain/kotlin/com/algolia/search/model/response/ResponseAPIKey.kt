package com.algolia.search.model.response

import com.algolia.search.model.APIKey
import com.algolia.search.model.ClientDate
import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
public data class ResponseAPIKey(
    @SerialName(KeyValue) val apiKey: APIKey,
    @SerialName(KeyValidity) val validity: Long,
    @SerialName(KeyAcl) val rights: List<ACL>,
    @SerialName(KeyMaxQueriesPerIPPerHour) val maxQueriesPerIPPerHourOrNull: Int? = null,
    @SerialName(KeyMaxHitsPerQuery) val maxHitsPerQueryOrNull: Int? = null,
    @SerialName(KeyCreatedAt) val createdAtOrNull: ClientDate? = null,
    @SerialName(KeyDescription) val descriptionOrNull: String? = null,
    @SerialName(KeyIndexes) val indicesOrNull: List<IndexName>? = null,
    @SerialName(KeyReferers) val referersOrNull: List<String>? = null,
    @SerialName(KeyQueryParameters) val queryOrNull: String? = null
) {

    @Transient
    public val maxQueriesPerIPPerHour: Int
        get() = maxQueriesPerIPPerHourOrNull!!

    @Transient
    public val maxHitsPerQuery: Int
        get() = maxHitsPerQueryOrNull!!

    @Transient
    public val createdAt: ClientDate
        get() = createdAtOrNull!!

    @Transient
    public val description: String
        get() = descriptionOrNull!!

    @Transient
    public val indices: List<IndexName>
        get() = indicesOrNull!!

    @Transient
    public val referers: List<String>
        get() = referersOrNull!!

    @Transient
    public val query: String
        get() = queryOrNull!!
}