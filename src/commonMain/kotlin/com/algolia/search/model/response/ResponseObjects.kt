package com.algolia.search.model.response

import com.algolia.search.serialize.KeyMessage
import com.algolia.search.serialize.KeyResults
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.JsonObject


@Serializable
public data class ResponseObjects(
    @SerialName(KeyResults) val results: List<JsonObject?>,
    @SerialName(KeyMessage) val messageOrNull: String? = null
) {

    @Transient
    public val message: String
        get() = messageOrNull!!
}