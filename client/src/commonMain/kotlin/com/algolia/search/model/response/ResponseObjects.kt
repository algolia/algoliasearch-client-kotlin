package com.algolia.search.model.response

import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
public data class ResponseObjects(
    /**
     * List of requested records. If a record is not found, it will be marked as null in the list.
     */
    @SerialName(Key.Results) val results: List<JsonObject?>,
    /**
     * Optional error message in case of failure to retrieve a requested record.
     */
    @SerialName(Key.Message) val messageOrNull: String? = null
) {

    public val message: String
        get() = messageOrNull!!
}
