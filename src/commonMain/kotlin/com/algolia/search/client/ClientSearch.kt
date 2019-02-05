package com.algolia.search.client

import com.algolia.search.endpoint.EndpointSearch
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.search.Query
import com.algolia.search.query.clone
import com.algolia.search.response.ResponseSearch
import com.algolia.search.response.ResponseSearchFacetValue
import com.algolia.search.serialize.*
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlinx.serialization.json.json


internal class ClientSearch(
    val client: Client,
    override val indexName: IndexName
) : EndpointSearch,
    Client by client {

    private suspend fun search(requestOptions: RequestOptions?): ResponseSearch {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes()) { path ->
            httpClient.get<ResponseSearch>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun search(query: Query?, requestOptions: RequestOptions?): ResponseSearch {
        val copy = query?.clone()

        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/query")) { path ->
            httpClient.post<ResponseSearch>(path) {
                setBody(copy)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun browse(query: Query?, requestOptions: RequestOptions?): ResponseSearch {
        val copy = query?.clone()

        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/browse")) { path ->
            httpClient.post<ResponseSearch>(path) {
                setBody(copy)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun browse(cursor: Cursor, requestOptions: RequestOptions?): ResponseSearch {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/browse")) { path ->
            httpClient.get<ResponseSearch>(path) {
                parameter(KeyCursor, cursor)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun searchForFacetValue(
        attribute: Attribute,
        facetQuery: String?,
        query: Query?,
        maxFacetHits: Int?,
        requestOptions: RequestOptions?
    ): ResponseSearchFacetValue {
        val copy = query?.clone()
        val extraParams = json {
            maxFacetHits?.let { KeyMaxFacetHits to it }
            facetQuery?.let { KeyFacetQuery to it }
        }
        val bodyString = (copy?.toJsonNoDefaults()?.merge(extraParams) ?: extraParams).toString()

        return read.retry(
            requestOptions.computedReadTimeout,
            indexName.pathIndexes("/facets/$attribute/query")
        ) { path ->
            httpClient.post<ResponseSearchFacetValue>(path) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }
}