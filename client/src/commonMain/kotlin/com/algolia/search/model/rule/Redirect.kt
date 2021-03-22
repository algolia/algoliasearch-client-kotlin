package com.algolia.search.model.rule

import com.algolia.search.serialize.KeyUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Redirect(
    @SerialName(KeyUrl) val url: String?,
)
