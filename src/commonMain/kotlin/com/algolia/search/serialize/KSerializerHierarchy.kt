package com.algolia.search.serialize

import com.algolia.search.model.search.Hierarchy
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.HashMapClassDesc
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.*
import kotlinx.serialization.list


internal object KSerializerHierarchy : KSerializer<Hierarchy> {

    override val descriptor = HashMapClassDesc(
        StringSerializer.descriptor,
        StringSerializer.list.descriptor
    )

    override fun serialize(encoder: Encoder, obj: Hierarchy) {
        val json = json {
            obj.forEach { (key, value) ->
                key to if (value.size == 1) {
                    JsonLiteral(value.first())
                } else {
                    jsonArray { value.forEach { +it } }
                }
            }
        }
        encoder.asJsonOutput().encodeJson(json)
    }

    override fun deserialize(decoder: Decoder): Hierarchy {
        val json = decoder.asJsonInput().jsonObject

        return json.map { (key, value) ->
            key to when (value) {
                is JsonLiteral -> listOf(value.content)
                is JsonArray -> value.jsonArray.map { it.content }
                else -> throw Exception("Malformed Hierarchy.")
            }
        }.toMap()
    }
}