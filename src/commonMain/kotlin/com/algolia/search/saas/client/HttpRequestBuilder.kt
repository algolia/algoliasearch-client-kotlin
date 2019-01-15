package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import com.algolia.search.saas.serialize.*
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray


internal fun HttpRequestBuilder.setApplicationId(applicationID: ApplicationID) {
    header("X-Algolia-Application-Id", applicationID.raw)
}

internal fun HttpRequestBuilder.setApiKey(apiKey: APIKey) {
    header("X-Algolia-API-Key", apiKey.raw)
}

internal fun HttpRequestBuilder.setRequestOptions(requestOptions: RequestOptions?) {
    requestOptions?.headers?.forEach { header(it.key, it.value) }
    requestOptions?.urlParameters?.forEach { parameter(it.key, it.value) }
}

internal fun HttpRequestBuilder.setQueries(queries: Collection<IndexQuery>, strategy: MultipleQueriesStrategy) {
    body = json {
        KeyRequests to jsonArray {
            queries.forEach {
                +json {
                    KeyIndexName to it.indexName.raw
                    KeyParams to it.query.encodeNoNulls().urlEncode()
                }
            }
        }
        KeyStrategy to strategy.raw
    }.toString()
}

internal fun HttpRequestBuilder.setBody(query: Query?) {
    body = query?.encodeNoNulls()?.toString() ?: "{}"
}