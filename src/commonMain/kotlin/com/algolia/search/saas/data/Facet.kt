package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class Facet(
    val name: String,
    val count: Int
)