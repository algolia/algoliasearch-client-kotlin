package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class QueryRuleHits(
    val hits: List<QueryRule>,
    val nbHits: Int
)