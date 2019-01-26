package com.algolia.search.saas.model.query_rule

import kotlinx.serialization.Serializable


@Serializable
data class QueryRuleHits(
    val hits: List<QueryRule>,
    val nbHits: Int
)