package com.algolia.search.model.apikey

import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query


public data class APIKeyParams(
    val ACLs: List<ACL>? = null,
    val indices: List<IndexName>? = null,
    val description: String? = null,
    val maxHitsPerQuery: Int? = null,
    val maxQueriesPerIPPerHour: Int? = null,
    val validity: Long? = null,
    val query: Query? = null,
    val referers: List<String>? = null
)