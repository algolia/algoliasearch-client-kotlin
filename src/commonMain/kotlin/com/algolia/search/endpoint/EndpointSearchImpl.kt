package com.algolia.search.endpoint

import com.algolia.search.client.APIWrapper
import com.algolia.search.client.RequestOptions
import com.algolia.search.client.setBody
import com.algolia.search.client.setRequestOptions
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchFacetValue
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.search.Query
import com.algolia.search.query.clone
import com.algolia.search.serialize.*
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlinx.serialization.json.json


internal class EndpointSearchImpl(
    val api: APIWrapper,
    override val indexName: IndexName
) : EndpointSearch,
    APIWrapper by api {

    private suspend fun search(requestOptions: RequestOptions?): ResponseSearch {
        return read.retry(requestOptions.computedReadTimeout, indexName.toPath()) { path ->
            httpClient.get<ResponseSearch>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun search(query: Query?, requestOptions: RequestOptions?): ResponseSearch {
        val copy = query?.clone()

        return read.retry(requestOptions.computedReadTimeout, indexName.toPath("/query")) { path ->
            httpClient.post<ResponseSearch>(path) {
                setBody(copy)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun browse(query: Query?, requestOptions: RequestOptions?): ResponseSearch {
        val copy = query?.clone()
        val bodyString =
            copy?.let {
                json {
                    KeyParams to JsonNoNulls.toJson(Query.serializer(), it).jsonObject.urlEncode()
                }.toString()
            } ?: "{}"

        return read.retry(requestOptions.computedReadTimeout, indexName.toPath("/browse")) { path ->
            httpClient.post<ResponseSearch>(path) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun browse(cursor: Cursor, requestOptions: RequestOptions?): ResponseSearch {
        return read.retry(requestOptions.computedReadTimeout, indexName.toPath("/browse")) { path ->
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
            indexName.toPath("/facets/$attribute/query")
        ) { path ->
            httpClient.post<ResponseSearchFacetValue>(path) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }
}