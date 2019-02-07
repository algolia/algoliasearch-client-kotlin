package com.algolia.search.model.response

import com.algolia.search.serialize.KeyMessage
import com.algolia.search.serialize.KeyResults
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject


@Serializable
data class ResponseObjects(
    @SerialName(KeyResults) val results: List<JsonObject?>,
    @Optional @SerialName(KeyMessage) val message: String? = null
)