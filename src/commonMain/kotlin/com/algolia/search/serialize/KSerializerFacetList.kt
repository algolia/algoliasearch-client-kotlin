package com.algolia.search.serialize

import com.algolia.search.model.search.Facet
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

public object KSerializerFacetList : KSerializer<List<Facet>> {

    override val descriptor: SerialDescriptor = Facet.serializer().descriptor

    override fun serialize(encoder: Encoder, value: List<Facet>) {
        val json = buildJsonArray {
            value.map {
                add(buildJsonObject {
                    put(KeyValue, it.value)
                    put(KeyCount, it.count)
                    it.highlightedOrNull?.let { put(KeyHighlighted, it) }
                })
            }
        }
        encoder.asJsonOutput().encodeJsonElement(json)
    }

    override fun deserialize(decoder: Decoder): List<Facet> {
        val json = decoder.asJsonInput().jsonArray

        return json.map {
            Facet(
                it.jsonObject.getValue(KeyValue).jsonPrimitive.content,
                it.jsonObject.getValue(KeyCount).jsonPrimitive.int,
                it.jsonObject[KeyHighlighted]?.jsonPrimitive?.content
            )
        }
    }
}
