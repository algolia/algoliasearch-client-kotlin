package com.algolia.search.exception.internal

import com.algolia.search.exception.AlgoliaApiException
import com.algolia.search.exception.AlgoliaClientException
import com.algolia.search.exception.AlgoliaRuntimeException
import io.ktor.client.plugins.ResponseException

/**
 * Coerce a Throwable to a [AlgoliaClientException].
 */
internal fun Throwable.asClientException(): AlgoliaClientException {
    return AlgoliaClientException(message = message, cause = cause)
}

/**
 * Coerce a [ResponseException] to a [AlgoliaRuntimeException].
 */
internal fun ResponseException.asApiException(): AlgoliaApiException {
    return AlgoliaApiException(message = message, cause = cause, httpErrorCode = response.status.value)
}
