package com.algolia.search.model.response

import com.algolia.search.model.APIKey
import com.algolia.search.model.ClientDate
import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseAPIKey(
    /**
     * The [APIKey] value.
     */
    @SerialName(Key.Value) val apiKey: APIKey,
    /**
     * The date at which the [APIKey] has been created.
     */
    @SerialName(Key.CreatedAt) val createdAtOrNull: ClientDate? = null,
    /**
     * List of permissions [ACL] the key contains.
     */
    @SerialName(Key.Acl) val ACLs: List<ACL>,
    /**
     * Timestamp of the date at which the [APIKey] expires. (0 means it will not expire automatically).
     */
    @SerialName(Key.Validity) val validity: Long,
    /**
     * The list of targeted indices, if any.
     */
    @SerialName(Key.Indexes) val indicesOrNull: List<IndexName>? = null,
    /**
     * Description of the key, if set.
     */
    @SerialName(Key.Description) val descriptionOrNull: String? = null,
    @SerialName(Key.MaxQueriesPerIPPerHour) val maxQueriesPerIPPerHourOrNull: Int? = null,
    @SerialName(Key.MaxHitsPerQuery) val maxHitsPerQueryOrNull: Int? = null,
    @SerialName(Key.Referers) val referersOrNull: List<String>? = null,
    @SerialName(Key.QueryParameters) val queryOrNull: String? = null
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
