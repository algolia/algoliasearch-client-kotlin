package com.algolia.search.saas.data.search

import kotlinx.serialization.Serializable


@Serializable
data class Facet(
    val name: String,
    val count: Int
)