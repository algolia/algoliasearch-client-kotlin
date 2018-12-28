package client.query

import client.data.Index


data class IndexQuery(
    val index: Index,
    val query: Query
)