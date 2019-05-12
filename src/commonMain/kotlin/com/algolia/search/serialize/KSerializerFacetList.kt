package com.algolia.search.serialize

import com.algolia.search.model.search.Facet
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.ArrayListClassDesc
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray


object KSerializerFacetList : KSerializer<List<Facet>> {

    override val descriptor = ArrayListClassDesc(Facet.serializer().descriptor)

    override fun serialize(encoder: Encoder, obj: List<Facet>) {
        val json = jsonArray {
            obj.map {
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