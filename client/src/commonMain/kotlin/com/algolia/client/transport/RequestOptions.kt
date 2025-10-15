package com.algolia.client.transport

import kotlin.time.Duration
import kotlinx.serialization.json.JsonObject

/**
 * Represents options for configuring a request to an endpoint.
 *
 * @property writeTimeout The write timeout for the request.
 * @property readTimeout The read timeout for the request.
 * @property connectTimeout The connect timeout for the request.
 * @property headers A mutable map of header names to their respective values to be sent with the
 *   request.
 * @property urlParameters A mutable map of URL parameter names to their respective values to be
 *   appended to the request URL.
 * @property body A JSON object representing the request body.
 */
public data class RequestOptions(
  public val writeTimeout: Duration? = null,
  public val readTimeout: Duration? = null,
  public val connectTimeout: Duration? = null,
  public val headers: Map<String, Any> = emptyMap(),
  public val urlParameters: Map<String, Any> = emptyMap(),
  public val body: JsonObject? = null,
) {
  public operator fun plus(other: RequestOptions?): RequestOptions {
    if (other == null) {
      return this
    }

    return RequestOptions(
      writeTimeout = other.writeTimeout ?: writeTimeout,
      readTimeout = other.readTimeout ?: readTimeout,
      connectTimeout = other.connectTimeout ?: connectTimeout,
      headers = headers + other.headers,
      urlParameters = urlParameters + other.urlParameters,
      body = other.body ?: body,
    )
  }
}
