/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.ingestion

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * SourceShopifyBase
 *
 * @param shopURL URL of the Shopify store.
 */
@Serializable
public data class SourceShopifyBase(

  /** URL of the Shopify store. */
  @SerialName(value = "shopURL") val shopURL: String,
)