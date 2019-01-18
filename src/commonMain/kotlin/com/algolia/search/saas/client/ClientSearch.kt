package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import com.algolia.search.saas.endpoint.EndpointSearch
import com.algolia.search.saas.query.clone
import com.algolia.search.saas.serialize.KeyCursor
import com.algolia.search.saas.serialize.KeyFacetQuery
import com.algolia.search.saas.serialize.KeyMaxFacetHits
import com.algolia.search.saas.serialize.encodeNoNulls
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.json


internal class ClientSearch(
    val client: Client,
    override val indexName: IndexName
) : EndpointSearch,
    Client by client {

    private suspend fun search(requestOptions: RequestOptions?): Hits {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes()) { path ->
            httpClient.get<Hits>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun search(query: Query?, requestOptions: RequestOptions?): Hits {
        val copy = query?.clone()

        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/query")) { path ->
            httpClient.post<Hits>(path) {
                setRequestOptions(requestOptions)
                setBody(copy)
            }
        }
    }

    override suspend fun browse(query: Query?, requestOptions: RequestOptions?): Hits {
        val copy = query?.clone()

        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/browse")) { path ->
            httpClient.post<Hits>(path) {
                setRequestOptions(requestOptions)
                setBody(copy)
            }
        }
    }

    override suspend fun browse(cursor: Cursor, requestOptions: RequestOptions?): Hits {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/browse")) { path ->
            httpClient.get<Hits>(path) {
                setRequestOptions(requestOptions)
                parameter(KeyCursor, cursor)
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
        val copy = query?.clone()

        return read.retry(
            requestOptions.computedReadTimeout,
            indexName.pathIndexes("/facets/$attribute/query")
        ) { path ->
            httpClient.post<FacetHits>(path) {
                setRequestOptions(requestOptions)
                val extraParams = json {
                    maxFacetHits?.let { KeyMaxFacetHits to it }
                    facetQuery?.let { KeyFacetQuery to it }
                }

                body = if (copy != null) {
                    val serialize = copy.encodeNoNulls()
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