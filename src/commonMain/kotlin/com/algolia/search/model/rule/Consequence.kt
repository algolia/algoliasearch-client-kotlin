package com.algolia.search.model.rule

import com.algolia.search.model.ObjectID
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject


@Serializable
public data class Consequence(
    @Optional @SerialName(KeyParams) val params: Params? = null,
    @Optional @SerialName(KeyPromote) val promote: List<Promotion>? = null,
    @Optional @SerialName(KeyHide) @Serializable(KSerializerObjectIDs::class) val hide: List<ObjectID>? = null,
    @Optional @SerialName(KeyUserData) val userData: JsonObject? = null
)