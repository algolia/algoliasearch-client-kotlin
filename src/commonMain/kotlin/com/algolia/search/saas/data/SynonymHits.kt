package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class SynonymHits(
    val hits: List<Synonym>,
    val nbHits: Int
)