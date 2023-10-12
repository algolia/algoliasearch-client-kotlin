/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.recommend

import com.algolia.client.exception.AlgoliaClientException
import com.algolia.client.extensions.internal.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*

/**
 * Promote
 *
 * Implementations:
 * - [PromoteObjectID]
 * - [PromoteObjectIDs]
 */
@Serializable(PromoteSerializer::class)
public sealed interface Promote {

  public companion object {
  }
}

internal class PromoteSerializer : KSerializer<Promote> {

  override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Promote")

  override fun serialize(encoder: Encoder, value: Promote) {
    when (value) {
      is PromoteObjectID -> PromoteObjectID.serializer().serialize(encoder, value)
      is PromoteObjectIDs -> PromoteObjectIDs.serializer().serialize(encoder, value)
    }
  }

  override fun deserialize(decoder: Decoder): Promote {
    val codec = decoder.asJsonDecoder()
    val tree = codec.decodeJsonElement()

    // deserialize PromoteObjectID
    if (tree is JsonObject) {
      try {
        return codec.json.decodeFromJsonElement(PromoteObjectID.serializer(), tree)
      } catch (e: Exception) {
        // deserialization failed, continue
        println("Failed to deserialize PromoteObjectID (error: ${e.message})")
      }
    }

    // deserialize PromoteObjectIDs
    if (tree is JsonObject) {
      try {
        return codec.json.decodeFromJsonElement(PromoteObjectIDs.serializer(), tree)
      } catch (e: Exception) {
        // deserialization failed, continue
        println("Failed to deserialize PromoteObjectIDs (error: ${e.message})")
      }
    }

    throw AlgoliaClientException("Failed to deserialize json element: $tree")
  }
}
