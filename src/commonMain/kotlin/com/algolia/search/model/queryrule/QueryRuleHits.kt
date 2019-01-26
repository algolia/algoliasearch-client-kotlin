package com.algolia.search.model.queryrule

import kotlinx.serialization.Serializable


@Serializable
data class QueryRuleHits(
    val hits: List<QueryRule>,
    val nbHits: Int
)