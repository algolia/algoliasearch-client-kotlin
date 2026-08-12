package com.algolia.client.exception.internal

import com.algolia.client.exception.AlgoliaApiException
import com.algolia.client.exception.AlgoliaClientException
import com.algolia.client.exception.AlgoliaRuntimeException
import com.algolia.client.transport.internal.HEADER_CORRELATION_ID
import io.ktor.client.plugins.*

/** Coerce a Throwable to a [AlgoliaClientException]. */
internal fun Throwable.asClientException(): AlgoliaClientException =
  AlgoliaClientException(message = message, cause = this)

/** Coerce a [ResponseException] to a [AlgoliaRuntimeException]. */
internal fun ResponseException.asApiException(): AlgoliaApiException {
  val correlationId = response.headers[HEADER_CORRELATION_ID]
  return AlgoliaApiException(
    message = correlationId?.let { "$message (Correlation-ID: $it)" } ?: message,
    cause = this,
    httpErrorCode = response.status.value,
    correlationId = correlationId,
  )
}
