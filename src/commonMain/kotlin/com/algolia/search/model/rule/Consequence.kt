package com.algolia.search.model.rule

import com.algolia.search.model.ObjectID
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject


@Serializable
public data class Consequence(
    @SerialName(KeyParams) val params: Params? = null,
    @SerialName(KeyPromote) val promote: List<Promotion>? = null,
    @SerialName(KeyHide) @Serializable(KSerializerObjectIDs::class) val hide: List<ObjectID>? = null,
    @SerialName(KeyUserData) val userData: JsonObject? = null
)