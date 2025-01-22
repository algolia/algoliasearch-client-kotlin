/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.composition

import com.algolia.client.extensions.internal.*
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*

/**
 * CompositionsSearchResponse
 *
 * @param run
 */
@Serializable(CompositionsSearchResponseSerializer::class)
public data class CompositionsSearchResponse(

  val run: List<CompositionRunSearchResponse>,

  val additionalProperties: Map<String, JsonElement>? = null,
)

internal object CompositionsSearchResponseSerializer : KSerializer<CompositionsSearchResponse> {

  override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CompositionsSearchResponse") {
    element<List<CompositionRunSearchResponse>>("run")
  }

  override fun deserialize(decoder: Decoder): CompositionsSearchResponse {
    val input = decoder.asJsonDecoder()
    val tree = input.decodeJsonObject()
    return CompositionsSearchResponse(
      run = tree.getValue("run").let { input.json.decodeFromJsonElement(it) },
      additionalProperties = tree.filterKeys { it !in descriptor.elementNames },
    )
  }

  override fun serialize(encoder: Encoder, value: CompositionsSearchResponse) {
    val output = encoder.asJsonEncoder()
    val json = buildJsonObject {
      put("run", output.json.encodeToJsonElement(value.run))
      value.additionalProperties?.onEach { (key, element) -> put(key, element) }
    }
    (encoder as JsonEncoder).encodeJsonElement(json)
  }
}
