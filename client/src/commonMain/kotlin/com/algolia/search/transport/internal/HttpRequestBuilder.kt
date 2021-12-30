package com.algolia.search.transport.internal

import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.serialize.KeyAlgoliaAPIKey
import com.algolia.search.serialize.KeyAlgoliaApplicationID
import com.algolia.search.transport.RequestOptions
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody

internal fun HttpRequestBuilder.setApplicationId(applicationID: ApplicationID?) {
    header(KeyAlgoliaApplicationID, applicationID?.raw)
}

internal fun HttpRequestBuilder.setApiKey(apiKey: APIKey?) {
    header(KeyAlgoliaAPIKey, apiKey?.raw)
}

internal fun HttpRequestBuilder.setRequestOptions(requestOptions: RequestOptions?) {
    requestOptions?.headers?.forEach { header(it.key, it.value) }
    requestOptions?.urlParameters?.forEach { parameter(it.key, it.value) }
    requestOptions?.body?.let { setBody(it) }
}
