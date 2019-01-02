package com.algolia.search.saas.serialize

import com.algolia.search.saas.data.Attribute
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


internal object KSerializerFacets : KSerializer<Map<Attribute, Map<String, Int>>> {

    override val descriptor = HashMapClassDesc(
        Attribute.descriptor,
        HashMapClassDesc(
            StringSerializer.descriptor,
            IntSerializer.descriptor
        )
    )

    override fun serialize(output: Encoder, obj: Map<Attribute, Map<String, Int>>) {
        val element = obj.map {
            it.key.raw to JsonObject(it.value.map {
                it.key to JsonLiteral(it.value)
            }.toMap())
        }.toMap()

        output.asJsonOutput().writeTree(JsonObject(element.toMap()))
    }

    override fun deserialize(input: Decoder): Map<Attribute, Map<String, Int>> {
        val json = input.readAsTree().jsonObject

        return json.map { (key, element) ->
            key.toAttribute() to element.jsonObject.toMap().map { it.key to it.value.int }.toMap()
        }.toMap()
    }
}