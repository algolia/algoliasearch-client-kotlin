package com.algolia.search.saas.serialize

import com.algolia.search.saas.data.Attribute
import com.algolia.search.saas.data.Facet
import com.algolia.search.saas.toAttribute
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.HashMapClassDesc
import kotlinx.serialization.internal.IntSerializer
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.int


internal object KSerializerFacets : KSerializer<Map<Attribute, List<Facet>>> {

    override val descriptor = HashMapClassDesc(
        Attribute.descriptor,
        HashMapClassDesc(
            StringSerializer.descriptor,
            IntSerializer.descriptor
        )
    )

    override fun serialize(encoder: Encoder, obj: Map<Attribute, List<Facet>>) {
        val element = obj.map {
            it.key.raw to JsonObject(it.value.map {
                it.name to JsonLiteral(it.count)
            }.toMap())
        }.toMap()

        encoder.asJsonOutput().encodeJson(JsonObject(element.toMap()))
    }

    override fun deserialize(decoder: Decoder): Map<Attribute, List<Facet>> {
        val json = decoder.asJsonInput().jsonObject

        return json.map { (key, element) ->
            key.toAttribute() to element.jsonObject.map { Facet(it.key, it.value.int) }
        }.toMap()
    }
}