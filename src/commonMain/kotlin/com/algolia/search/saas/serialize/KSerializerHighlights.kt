package com.algolia.search.saas.serialize

import com.algolia.search.saas.data.Attribute
import com.algolia.search.saas.data.HighlightResult
import com.algolia.search.saas.toAttribute
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.internal.HashMapClassDesc
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.json


internal object KSerializerHighlights : KSerializer<Map<Attribute, HighlightResult>> {

    override val descriptor: SerialDescriptor = HashMapClassDesc(
        Attribute.descriptor,
        HighlightResult.serializer().descriptor
    )

    override fun serialize(output: Encoder, obj: Map<Attribute, HighlightResult>) {
        val element = json {
            obj.forEach { (key, value) ->
                key.raw to JSON.stringify(HighlightResult.serializer(), value)
            }
        }

        output.asJsonOutput().writeTree(element)
    }

    override fun deserialize(input: Decoder): Map<Attribute, HighlightResult> {
        val json = input.asJsonInput().jsonObject

        return json.map { (key, value) ->
            key.toAttribute() to JSON.parse(
                HighlightResult.serializer(),
                when (value) {
                    is JsonLiteral -> value.content
                    else -> value.toString()
                }
            )
        }.toMap()
    }
}