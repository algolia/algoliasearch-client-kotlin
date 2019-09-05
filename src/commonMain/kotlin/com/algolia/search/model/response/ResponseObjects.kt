package com.algolia.search.model.response

import com.algolia.search.serialize.KeyMessage
import com.algolia.search.serialize.KeyResults
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject


@Serializable
public data class ResponseObjects(
    /**
     * List of requested records. If a record is not found, it will be marked as null in the list.
     */
    @SerialName(KeyResults) val results: List<JsonObject?>,
    /**
     * Optional error message in case of failure to retrieve a requested record.
     */
    @SerialName(KeyMessage) val messageOrNull: String? = null
) {

    public val message: String
        get() = messageOrNull!!
}