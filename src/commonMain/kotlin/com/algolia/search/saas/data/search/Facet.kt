package com.algolia.search.saas.data.search

import com.algolia.search.saas.serialize.KeyCount
import com.algolia.search.saas.serialize.KeyName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Facet(
    @SerialName(KeyName) val name: String,
    @SerialName(KeyCount) val count: Int
)