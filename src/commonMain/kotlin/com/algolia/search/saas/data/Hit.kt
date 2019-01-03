package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KSerializerHighlights
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Hit(
    @Optional @SerialName("_highlightResult") @Serializable(KSerializerHighlights::class)
    val highlightResult: Map<Attribute, HighlightResult>? = null
)