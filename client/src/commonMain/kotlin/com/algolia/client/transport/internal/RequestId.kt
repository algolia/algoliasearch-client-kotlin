package com.algolia.client.transport.internal

import com.algolia.client.api.ApiClient
import com.algolia.client.transport.RequestOptions
import kotlin.random.Random

/** Name of the request header carrying the Request-ID. */
internal const val HEADER_REQUEST_ID: String = "request-id"

/** Name of the query parameter carrying the Request-ID. */
internal const val QUERY_PARAM_REQUEST_ID: String = "x-algolia-request-id"

/** Name of the response header carrying the Correlation-ID. */
internal const val HEADER_CORRELATION_ID: String = "Correlation-ID"

private const val BASE62_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
private const val REQUEST_ID_LENGTH = 11

/** Generates a Request-ID matching `^[0-9A-Za-z]{11}$`. */
internal fun generateRequestId(): String =
  buildString(REQUEST_ID_LENGTH) {
    repeat(REQUEST_ID_LENGTH) { append(BASE62_CHARS[Random.nextInt(BASE62_CHARS.length)]) }
  }

/** Whether this header map already carries a Request-ID, matched case-insensitively. */
internal fun Map<String, Any>.hasRequestIdHeader(): Boolean = keys.any {
  it.equals(HEADER_REQUEST_ID, ignoreCase = true)
}

/** Whether this query parameter map already carries a Request-ID, matched case-insensitively. */
internal fun Map<String, Any>.hasRequestIdQueryParameter(): Boolean = keys.any {
  it.equals(QUERY_PARAM_REQUEST_ID, ignoreCase = true)
}

/** Whether [requestOptions] already carries a Request-ID on either channel. */
internal fun hasRequestId(requestOptions: RequestOptions?): Boolean =
  requestOptions != null &&
    (requestOptions.headers.hasRequestIdHeader() ||
      requestOptions.urlParameters.hasRequestIdQueryParameter())

/**
 * Returns request options carrying a Request-ID, so that every request a multi-request helper
 * performs shares the same one. Returns [requestOptions] unchanged when the client does not send
 * Request-IDs or when one is already supplied.
 */
internal fun ApiClient.withRequestId(requestOptions: RequestOptions?): RequestOptions? {
  val sendsRequestId = (requester as? KtorRequester)?.sendsRequestId == true
  if (!sendsRequestId || hasRequestId(requestOptions)) return requestOptions
  return RequestOptions(headers = mapOf(HEADER_REQUEST_ID to generateRequestId())) + requestOptions
}
