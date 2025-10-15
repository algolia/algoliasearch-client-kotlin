package com.algolia.client.exception.internal

import com.algolia.client.exception.AlgoliaApiException
import com.algolia.client.exception.AlgoliaClientException
import com.algolia.client.exception.AlgoliaRuntimeException
import io.ktor.client.plugins.*

/** Coerce a Throwable to a [AlgoliaClientException]. */
internal fun Throwable.asClientException(): AlgoliaClientException =
  AlgoliaClientException(message = message, cause = this)

/** Coerce a [ResponseException] to a [AlgoliaRuntimeException]. */
internal fun ResponseException.asApiException(): AlgoliaApiException =
  AlgoliaApiException(message = message, cause = this, httpErrorCode = response.status.value)
