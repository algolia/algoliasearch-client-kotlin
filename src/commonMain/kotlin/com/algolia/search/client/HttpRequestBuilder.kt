package com.algolia.search.client

import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.model.request.RequestMultipleQueries
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.KeyAlgoliaAPIKey
import com.algolia.search.serialize.KeyAlgoliaApplicationID
import com.algolia.search.serialize.KeyForwardToReplicas
import com.algolia.search.serialize.noDefaults
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import kotlinx.serialization.json.Json


internal fun HttpRequestBuilder.setApplicationId(applicationID: ApplicationID) {
    header(KeyAlgoliaApplicationID, applicationID.raw)
}

internal fun HttpRequestBuilder.setApiKey(apiKey: APIKey) {
    header(KeyAlgoliaAPIKey, apiKey.raw)
}

internal fun HttpRequestBuilder.setRequestOptions(requestOptions: RequestOptions?) {
    requestOptions?.headers?.forEach { header(it.key, it.value) }
    requestOptions?.urlParameters?.forEach { parameter(it.key, it.value) }
    requestOptions?.body?.let { body = it }
}

internal fun HttpRequestBuilder.setForwardToReplicas(forwardToReplicas: Boolean?) {
    parameter(KeyForwardToReplicas, forwardToReplicas)
}

internal fun HttpRequestBuilder.setQueries(indexQueries: List<IndexQuery>, strategy: MultipleQueriesStrategy) {
    body = Json.noDefaults.stringify(RequestMultipleQueries, RequestMultipleQueries(indexQueries, strategy))
}

internal fun HttpRequestBuilder.setBody(query: Query?) {
    body = query?.let { Json.noDefaults.stringify(Query.serializer(), it) } ?: "{}"
}

internal suspend fun <T> APIWrapper.retryRead(
    requestOptions: RequestOptions?,
    path: String,
    request: suspend (String) -> T
): T {
    return read.retry(requestOptions.computedReadTimeout, path, request)
}

internal suspend fun <T> APIWrapper.retryWrite(
    requestOptions: RequestOptions?,
    path: String,
    request: suspend (String) -> T
): T {
    return write.retry(requestOptions.computedWriteTimeout, path, request)
}