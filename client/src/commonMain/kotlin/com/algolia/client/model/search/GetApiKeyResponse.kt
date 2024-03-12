/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.search

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * GetApiKeyResponse
 *
 * @param createdAt Timestamp of creation in milliseconds in [Unix epoch time](https://wikipedia.org/wiki/Unix_time).
 * @param acl Permissions that determine the type of API requests this key can make. The required ACL is listed in each endpoint's reference. For more information, see [access control list](https://www.algolia.com/doc/guides/security/api-keys/#access-control-list-acl).
 * @param `value` API key.
 * @param description Description of an API key to help you identify this API key.
 * @param indexes Index names or patterns that this API key can access. By default, an API key can access all indices in the same application.  You can use leading and trailing wildcard characters (`*`):  - `dev_*` matches all indices starting with \"dev_\". - `*_dev` matches all indices ending with \"_dev\". - `*_products_*` matches all indices containing \"_products_\".
 * @param maxHitsPerQuery Maximum number of results this API key can retrieve in one query. By default, there's no limit.
 * @param maxQueriesPerIPPerHour Maximum number of API requests allowed per IP address or [user token](https://www.algolia.com/doc/guides/sending-events/concepts/usertoken/) per hour.  If this limit is reached, the API returns an error with status code `429`. By default, there's no limit.
 * @param queryParameters Query parameters to add when making API requests with this API key.  To restrict this API key to specific IP addresses, add the `restrictSources` parameter. You can only add a single source, but you can provide a range of IP addresses.  Creating an API key fails if the request is made from an IP address that's outside the restricted range.
 * @param referers Allowed HTTP referrers for this API key.  By default, all referrers are allowed. You can use leading and trailing wildcard characters (`*`):  - `https://algolia.com/_*` allows all referrers starting with \"https://algolia.com/\" - `*.algolia.com` allows all referrers ending with \".algolia.com\" - `*algolia.com*` allows all referrers in the domain \"algolia.com\".  Like all HTTP headers, referrers can be spoofed. Don't rely on them to secure your data. For more information, see [HTTP referrer restrictions](https://www.algolia.com/doc/guides/security/security-best-practices/#http-referrers-restrictions).
 * @param validity Duration (in seconds) after which the API key expires. By default, API keys don't expire.
 */
@Serializable
public data class GetApiKeyResponse(

  /** Timestamp of creation in milliseconds in [Unix epoch time](https://wikipedia.org/wiki/Unix_time). */
  @SerialName(value = "createdAt") val createdAt: Long,

  /** Permissions that determine the type of API requests this key can make. The required ACL is listed in each endpoint's reference. For more information, see [access control list](https://www.algolia.com/doc/guides/security/api-keys/#access-control-list-acl).  */
  @SerialName(value = "acl") val acl: List<Acl>,

  /** API key. */
  @SerialName(value = "value") val `value`: String? = null,

  /** Description of an API key to help you identify this API key. */
  @SerialName(value = "description") val description: String? = null,

  /** Index names or patterns that this API key can access. By default, an API key can access all indices in the same application.  You can use leading and trailing wildcard characters (`*`):  - `dev_*` matches all indices starting with \"dev_\". - `*_dev` matches all indices ending with \"_dev\". - `*_products_*` matches all indices containing \"_products_\".  */
  @SerialName(value = "indexes") val indexes: List<String>? = null,

  /** Maximum number of results this API key can retrieve in one query. By default, there's no limit.  */
  @SerialName(value = "maxHitsPerQuery") val maxHitsPerQuery: Int? = null,

  /** Maximum number of API requests allowed per IP address or [user token](https://www.algolia.com/doc/guides/sending-events/concepts/usertoken/) per hour.  If this limit is reached, the API returns an error with status code `429`. By default, there's no limit.  */
  @SerialName(value = "maxQueriesPerIPPerHour") val maxQueriesPerIPPerHour: Int? = null,

  /** Query parameters to add when making API requests with this API key.  To restrict this API key to specific IP addresses, add the `restrictSources` parameter. You can only add a single source, but you can provide a range of IP addresses.  Creating an API key fails if the request is made from an IP address that's outside the restricted range.  */
  @SerialName(value = "queryParameters") val queryParameters: String? = null,

  /** Allowed HTTP referrers for this API key.  By default, all referrers are allowed. You can use leading and trailing wildcard characters (`*`):  - `https://algolia.com/_*` allows all referrers starting with \"https://algolia.com/\" - `*.algolia.com` allows all referrers ending with \".algolia.com\" - `*algolia.com*` allows all referrers in the domain \"algolia.com\".  Like all HTTP headers, referrers can be spoofed. Don't rely on them to secure your data. For more information, see [HTTP referrer restrictions](https://www.algolia.com/doc/guides/security/security-best-practices/#http-referrers-restrictions).  */
  @SerialName(value = "referers") val referers: List<String>? = null,

  /** Duration (in seconds) after which the API key expires. By default, API keys don't expire.  */
  @SerialName(value = "validity") val validity: Int? = null,
)
