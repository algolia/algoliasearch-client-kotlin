package com.algolia.search.saas.serialize

import com.algolia.search.saas.data.Attribute
import com.algolia.search.saas.toAttribute
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.HashMapClassDesc
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.content
import kotlinx.serialization.json.json


internal object KSerializerSnippets : KSerializer<Map<Attribute, String>> {

    override val descriptor = HashMapClassDesc(
        Attribute.descriptor,
        StringSerializer.descriptor
    )

    override fun serialize(encoder: Encoder, obj: Map<Attribute, String>) {
        val json = json {
            obj.forEach {
                it.key.raw to json {
                    KeyValue to it.value
                }
            }
        }
        encoder.asJsonOutput().encodeJson(json)
    }

    override fun deserialize(decoder: Decoder): Map<Attribute, String> {
        val json = decoder.asJsonInput().jsonObject

        return json.map { it.key.toAttribute() to it.value.jsonObject[KeyValue].content }.toMap()
    }
}