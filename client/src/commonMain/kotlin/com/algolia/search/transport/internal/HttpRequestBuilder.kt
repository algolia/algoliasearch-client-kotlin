package com.algolia.search.transport.internal

import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.serialize.internal.Key
import com.algolia.search.transport.RequestOptions
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody

internal fun HttpRequestBuilder.applicationId(applicationID: ApplicationID?) {
    header(Key.AlgoliaApplicationID, applicationID?.raw)
}

internal fun HttpRequestBuilder.apiKey(apiKey: APIKey?) {
    header(Key.AlgoliaAPIKey, apiKey?.raw)
}

internal fun HttpRequestBuilder.requestOptions(requestOptions: RequestOptions?) {
    requestOptions?.headers?.forEach { header(it.key, it.value) }
    requestOptions?.urlParameters?.forEach { parameter(it.key, it.value) }
    requestOptions?.body?.let { setBody(it) }
}
