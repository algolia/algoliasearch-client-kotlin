package com.algolia.search.saas.data


data class IndexQuery(
    val indexName: IndexName,
    val query: Query
)