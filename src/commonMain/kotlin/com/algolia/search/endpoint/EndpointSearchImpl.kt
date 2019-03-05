package com.algolia.search.endpoint

import com.algolia.search.client.*
import com.algolia.search.filter.build
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchForFacetValue
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.*
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json


internal class EndpointSearchImpl(
    val api: APIWrapper,
    override val indexName: IndexName
) : EndpointSearch,
    APIWrapper by api {

    private suspend fun search(requestOptions: RequestOptions?): ResponseSearch {
        return retryRead(requestOptions, indexName.toPath()) { url ->
            httpClient.get<ResponseSearch>(url) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun search(query: Query?, requestOptions: RequestOptions?): ResponseSearch {
        val copy = query?.build()

        return retryRead(requestOptions, indexName.toPath("/query")) { url ->
            httpClient.post<ResponseSearch>(url) {
                setBody(copy)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun browse(query: Query?, requestOptions: RequestOptions?): ResponseSearch {
        val copy = query?.build()
        val bodyString =
            copy?.let {
                json {
                    KeyParams to Json.noDefaults.toJson(Query.serializer(), it).jsonObject.urlEncode()
                }.toString()
            } ?: "{}"

        return retryRead(requestOptions, indexName.toPath("/browse")) { url ->
            httpClient.post<ResponseSearch>(url) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun browse(cursor: Cursor, requestOptions: RequestOptions?): ResponseSearch {
        return retryRead(requestOptions, indexName.toPath("/browse")) { url ->
            httpClient.get<ResponseSearch>(url) {
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
    ): ResponseSearchForFacetValue {
        val copy = query?.build()
        val extraParams = json {
            maxFacetHits?.let { KeyMaxFacetHits to it }
            facetQuery?.let { KeyFacetQuery to it }
        }
        val bodyString = (copy?.toJsonNoDefaults()?.merge(extraParams) ?: extraParams).toString()

        return retryRead(
            requestOptions,
            indexName.toPath("/facets/$attribute/query")
        ) { url ->
            httpClient.post<ResponseSearchForFacetValue>(url) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }
}