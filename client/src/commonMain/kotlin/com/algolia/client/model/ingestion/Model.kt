/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.ingestion

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * Model
 *
 * @param fullname
 * @param modelName
 * @param systemPrompt
 * @param id
 * @param provider
 */
@Serializable
public data class Model(

  @SerialName(value = "fullname") val fullname: String,

  @SerialName(value = "modelName") val modelName: String,

  @SerialName(value = "systemPrompt") val systemPrompt: String,

  @SerialName(value = "id") val id: String,

  @SerialName(value = "provider") val provider: String,
)