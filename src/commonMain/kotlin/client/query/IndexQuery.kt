package client.query

import client.Index


data class IndexQuery(
    val index: Index,
    val query: Query
)