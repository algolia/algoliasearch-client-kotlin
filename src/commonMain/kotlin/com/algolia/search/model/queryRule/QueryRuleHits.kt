package com.algolia.search.model.queryRule

import kotlinx.serialization.Serializable


@Serializable
data class QueryRuleHits(
    val hits: List<QueryRule>,
    val nbHits: Int
)