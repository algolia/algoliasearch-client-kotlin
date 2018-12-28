package client

import client.data.ApiKey
import client.data.ApplicationId
import client.data.MultipleQueriesStrategy
import client.query.IndexQuery
import client.query.Query
import client.serialize.QuerySerializer
import client.serialize.urlEncode
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray


fun HttpRequestBuilder.setApplicationId(applicationId: ApplicationId) {
    header("X-Algolia-Application-Id", applicationId.name)
}

fun HttpRequestBuilder.setApiKey(apiKey: ApiKey) {
    header("X-Algolia-API-Key", apiKey.name)
}

fun HttpRequestBuilder.setRequestOptions(requestOptions: RequestOptions?) {
    requestOptions?.headers?.forEach { header(it.key, it.value) }
    requestOptions?.urlParameters?.forEach { parameter(it.key, it.value) }
}

fun HttpRequestBuilder.setQueries(queries: Collection<IndexQuery>, strategy: MultipleQueriesStrategy) {
    body = json {
        "requests" to jsonArray {
            queries.forEach {
                +json {
                    "indexName" to it.index.name
                    "params" to (QuerySerializer.serialize(it.query) as JsonObject).urlEncode()
                }
            }
        }
        "strategy" to strategy.raw
    }.toString()
}

fun HttpRequestBuilder.setBody(query: Query?) {
    body = if (query != null) QuerySerializer.serialize(query).toString() else json { }.toString()
}

fun HttpRequestBuilder.setBody(json: JsonElement) {
    body = json.toString()
}