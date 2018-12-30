package client.data


data class IndexQuery(
    val indexName: IndexName,
    val query: Query
)