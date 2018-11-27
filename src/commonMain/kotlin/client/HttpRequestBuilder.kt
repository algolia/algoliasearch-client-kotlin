package client

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.parameter


fun HttpRequestBuilder.setApplicationId(applicationId: ApplicationId) {
    header("X-Algolia-Application-Id", applicationId.string)
}

fun HttpRequestBuilder.setApiKey(apiKey: ApiKey) {
    header("X-Algolia-API-Key", apiKey.string)
}

fun HttpRequestBuilder.setRequestOptions(requestOptions: RequestOptions?) {
    requestOptions?.headers?.forEach { header(it.key, it.value) }
    requestOptions?.urlParameters?.forEach { parameter(it.key, it.value) }
}