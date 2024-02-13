package com.algolia.client.extensions

import com.algolia.client.model.search.SearchParamsObject
import kotlinx.datetime.Instant

/**
 * Create restrictions for an API key.
 *
 * @param query A mapping of search parameters that will be forced at query time.
 * @param restrictIndices List of index names that can be queried.
 * @param restrictSources IPv4 network allowed using the generated key. This is used for more protection against
 * the api key leaking and reuse.
 * @param validUntil A Unix timestamp used to define the expiration date of the API key.
 * @param userToken Specify a user identifier. This is often used with rate limits. By default, rate limits will only
 * use the IP. This can be an issue when several of your end users are using the same IP. To avoid that, you can set a
 * userToken query parameter when generating the key. When set, a unique user will be identified by their IP + user_token
 * instead of only by their IP. This allows you to restrict a single user from performing a maximum of N API calls per hour,
 * even if he shares his IP with another user.
 */
public data class SecuredAPIKeyRestriction(
  val query: SearchParamsObject? = null,
  val restrictIndices: List<String>? = null,
  val restrictSources: List<String>? = null,
  val validUntil: Instant? = null,
  val userToken: String? = null
)
