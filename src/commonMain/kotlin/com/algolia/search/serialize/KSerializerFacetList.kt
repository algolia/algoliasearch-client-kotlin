package com.algolia.search.serialize

import com.algolia.search.model.search.Facet
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray

public object KSerializerFacetList : KSerializer<List<Facet>> {

    override val descriptor: SerialDescriptor = Facet.serializer().descriptor

    override fun serialize(encoder: Encoder, value: List<Facet>) {
        val json = jsonArray {
            value.map {
                +json {
                    KeyValue to it.value
                    KeyCount to it.count
                    it.highlightedOrNull?.let { KeyHighlighted to it }
                }
            }
        }
        encoder.asJsonOutput().encodeJson(json)
    }

    override fun deserialize(decoder: Decoder): List<Facet> {
        val json = decoder.asJsonInput().jsonArray

        return json.map {
            Facet(
                it.jsonObject.getPrimitive(KeyValue).content,
                it.jsonObject.getPrimitive(KeyCount).int,
                it.jsonObject.getPrimitiveOrNull(KeyHighlighted)?.content
            )
        }
    }
}
