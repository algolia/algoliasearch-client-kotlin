package com.algolia.search.model.search

import com.algolia.search.serialize.KeyCount
import com.algolia.search.serialize.KeyName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class Facet(
    @SerialName(KeyName) val name: String,
    @SerialName(KeyCount) val count: Int
)