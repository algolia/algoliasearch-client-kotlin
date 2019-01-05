package com.algolia.search.saas.serialize

import com.algolia.search.saas.data.Attribute
import com.algolia.search.saas.data.HighlightResult
import com.algolia.search.saas.toAttribute
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.internal.HashMapClassDesc
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.json


internal object KSerializerHighlights : KSerializer<Map<Attribute, HighlightResult>> {

    override val descriptor: SerialDescriptor = HashMapClassDesc(
        Attribute.descriptor,
        HighlightResult.serializer().descriptor
    )

    override fun serialize(encoder: Encoder, obj: Map<Attribute, HighlightResult>) {
        val element = json {
            obj.forEach { (key, value) ->
                key.raw to Json.stringify(HighlightResult.serializer(), value)
            }
        }

        encoder.asJsonOutput().encodeJson(element)
    }

    override fun deserialize(decoder: Decoder): Map<Attribute, HighlightResult> {
        val json = decoder.asJsonInput().jsonObject

        return json.map { (key, value) ->
            key.toAttribute() to Json.parse(
                HighlightResult.serializer(),
                when (value) {
                    is JsonLiteral -> value.content
                    else -> value.toString()
                }
            )
        }.toMap()
    }
}