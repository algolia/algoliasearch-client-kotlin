package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import com.algolia.search.saas.serialize.KeyCursor
import com.algolia.search.saas.serialize.KeyFacetQuery
import com.algolia.search.saas.serialize.KeyMaxFacetHits
import com.algolia.search.saas.serialize.encodeNoNulls
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.json


class ClientSearch(
    val client: Client,
    override val indexName: IndexName
) : EndpointsSearch {

    override suspend fun search(requestOptions: RequestOptions?): Hits {
        return client.run {
            read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes()) { path ->
                httpClient.get<Hits>(path) {
                    setRequestOptions(requestOptions)
                }
            }
        }
    }

    override suspend fun search(query: Query?, requestOptions: RequestOptions?): Hits {
        return client.run {
            read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/query")) { path ->
                httpClient.post<Hits>(path) {
                    setRequestOptions(requestOptions)
                    setBody(query)
                }
            }
        }
    }

    override suspend fun browse(query: Query?, requestOptions: RequestOptions?): Hits {
        return client.run {
            read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/browse")) { path ->
                httpClient.post<Hits>(path) {
                    setRequestOptions(requestOptions)
                    setBody(query)
                }
            }
        }
    }

    override suspend fun browse(cursor: String, requestOptions: RequestOptions?): Hits {
        return client.run {
            read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/browse")) { path ->
                httpClient.get<Hits>(path) {
                    setRequestOptions(requestOptions)
                    parameter(KeyCursor, cursor)
                }
            }
        }
    }

    override suspend fun multipleQueries(
        queries: Collection<IndexQuery>,
        strategy: MultipleQueriesStrategy,
        requestOptions: RequestOptions?
    ): MultipleHits {
        return client.run {
            read.retry(requestOptions.computedReadTimeout, "/1/indexes/*/queries") { path ->
                httpClient.post<MultipleHits>(path) {
                    setRequestOptions(requestOptions)
                    setQueries(queries, strategy)
                }
            }
        }
    }

    override suspend fun searchForFacetValue(
        attribute: Attribute,
        query: Query?,
        facetQuery: String?,
        maxFacetHits: Int?,
        requestOptions: RequestOptions?
    ): FacetHits {
        return client.run {
            read.retry(
                requestOptions.computedReadTimeout,
                indexName.pathIndexes("/facets/$attribute/query")
            ) { path ->
                httpClient.post<FacetHits>(path) {
                    setRequestOptions(requestOptions)
                    val extraParams = json {
                        maxFacetHits?.let { KeyMaxFacetHits to it }
                        facetQuery?.let { KeyFacetQuery to it }
                    }

                    body = if (query != null) {
                        val serialize = query.encodeNoNulls()
                        val map = serialize.toMutableMap()

                        map.putAll(extraParams)
                        JsonObject(map)
                    } else {
                        extraParams
                    }.toString()
                }
            }
        }
    }
}