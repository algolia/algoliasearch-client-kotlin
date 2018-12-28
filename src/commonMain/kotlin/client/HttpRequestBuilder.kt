package client

import client.data.ApiKey
import client.data.ApplicationId
import client.query.IndexQuery
import client.data.MultipleQueriesStrategy
import client.query.Query
import client.serialize.serialize
import client.serialize.toMap
import client.serialize.urlEncode
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.parameter
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
                    "params" to it.query.toMap().urlEncode()
                }
            }
        }
        "strategy" to strategy.raw
    }.toString()
}

fun HttpRequestBuilder.setQuery(query: Query?) {
    body = query?.toMap()?.serialize()?.toString() ?: "{}"
}

fun HttpRequestBuilder.setBody(map: Map<String, Any>) {
    body = map.serialize().toString()
}