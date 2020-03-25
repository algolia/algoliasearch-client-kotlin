package com.algolia.search.model.response

import com.algolia.search.model.APIKey
import com.algolia.search.model.ClientDate
import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.serialize.KeyAcl
import com.algolia.search.serialize.KeyCreatedAt
import com.algolia.search.serialize.KeyDescription
import com.algolia.search.serialize.KeyIndexes
import com.algolia.search.serialize.KeyMaxHitsPerQuery
import com.algolia.search.serialize.KeyMaxQueriesPerIPPerHour
import com.algolia.search.serialize.KeyQueryParameters
import com.algolia.search.serialize.KeyReferers
import com.algolia.search.serialize.KeyValidity
import com.algolia.search.serialize.KeyValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseAPIKey(
    /**
     * The [APIKey] value.
     */
    @SerialName(KeyValue) val apiKey: APIKey,
    /**
     * The date at which the [APIKey] has been created.
     */
    @SerialName(KeyCreatedAt) val createdAtOrNull: ClientDate? = null,
    /**
     * List of permissions [ACL] the key contains.
     */
    @SerialName(KeyAcl) val ACLs: List<ACL>,
    /**
     * Timestamp of the date at which the [APIKey] expires. (0 means it will not expire automatically).
     */
    @SerialName(KeyValidity) val validity: Long,
    /**
     * The list of targeted indices, if any.
     */
    @SerialName(KeyIndexes) val indicesOrNull: List<IndexName>? = null,
    /**
     * Description of the key, if set.
     */
    @SerialName(KeyDescription) val descriptionOrNull: String? = null,
    @SerialName(KeyMaxQueriesPerIPPerHour) val maxQueriesPerIPPerHourOrNull: Int? = null,
    @SerialName(KeyMaxHitsPerQuery) val maxHitsPerQueryOrNull: Int? = null,
    @SerialName(KeyReferers) val referersOrNull: List<String>? = null,
    @SerialName(KeyQueryParameters) val queryOrNull: String? = null
) {

    val maxQueriesPerIPPerHour: Int
        get() = maxQueriesPerIPPerHourOrNull!!

    val maxHitsPerQuery: Int
        get() = maxHitsPerQueryOrNull!!

    val createdAt: ClientDate
        get() = createdAtOrNull!!

    val description: String
        get() = descriptionOrNull!!

    val indices: List<IndexName>
        get() = indicesOrNull!!

    val referers: List<String>
        get() = referersOrNull!!

    val query: String
        get() = queryOrNull!!
}
