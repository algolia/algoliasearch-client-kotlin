package com.algolia.search.model.response

import com.algolia.search.model.APIKey
import com.algolia.search.model.ClientDate
import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class ResponseAPIKey(
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

    public val maxQueriesPerIPPerHour: Int
        get() = maxQueriesPerIPPerHourOrNull!!

    public val maxHitsPerQuery: Int
        get() = maxHitsPerQueryOrNull!!

    public val createdAt: ClientDate
        get() = createdAtOrNull!!

    public val description: String
        get() = descriptionOrNull!!

    public val indices: List<IndexName>
        get() = indicesOrNull!!

    public val referers: List<String>
        get() = referersOrNull!!

    public val query: String
        get() = queryOrNull!!
}