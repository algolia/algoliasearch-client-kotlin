package com.algolia.search.serialize

import com.algolia.search.model.search.Facet
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
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
                add(
                    buildJsonObject {
                        put(Key.Value, it.value)
                        put(Key.Count, it.count)
                        it.highlightedOrNull?.let { put(Key.Highlighted, it) }
                    }
                )
            }
        }
        encoder.asJsonOutput().encodeJsonElement(json)
    }

    override fun deserialize(decoder: Decoder): List<Facet> {
        val json = decoder.asJsonInput().jsonArray

        return json.map {
            Facet(
                it.jsonObject.getValue(Key.Value).jsonPrimitive.content,
                it.jsonObject.getValue(Key.Count).jsonPrimitive.int,
                it.jsonObject[Key.Highlighted]?.jsonPrimitive?.content
            )
        }
    }
}
