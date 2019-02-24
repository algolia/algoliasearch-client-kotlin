package com.algolia.search.serialize

import com.algolia.search.model.Attribute
import com.algolia.search.model.search.MatchLevel
import com.algolia.search.model.search.SnippetResult
import com.algolia.search.toAttribute
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.HashMapClassDesc
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObjectSerializer
import kotlinx.serialization.json.json


object KSerializerSnippetResults : KSerializer<Map<Attribute, SnippetResult>> {

    override val descriptor = HashMapClassDesc(
        Attribute.descriptor,
        JsonObjectSerializer.descriptor
    )

    override fun serialize(encoder: Encoder, obj: Map<Attribute, SnippetResult>) {
        val json = json {
            obj.forEach {
                it.key.raw to json {
                    KeyValue to it.value.value
                    KeyMatchLevel to it.value.matchLevel.raw
                }
            }
        }
        encoder.asJsonOutput().encodeJson(json)
    }

    override fun deserialize(decoder: Decoder): Map<Attribute, SnippetResult> {
        val json = decoder.asJsonInput().jsonObject

        return json.map {
            it.key.toAttribute() to SnippetResult(
                it.value.jsonObject.getPrimitive(KeyValue).content,
                Json.parse(MatchLevel, it.value.jsonObject.getPrimitive(KeyMatchLevel).content)
            )
        }.toMap()
    }
}