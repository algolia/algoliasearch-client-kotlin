package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.asJsonOutput
import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.toAttribute
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.HashMapClassDesc
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTreeParser


internal object KSerializerFacetStats : KSerializer<Map<Attribute, FacetStats>> {

    override val descriptor = HashMapClassDesc(Attribute.descriptor, FacetStats.serializer().descriptor)

    override fun serialize(output: Encoder, obj: Map<Attribute, FacetStats>) {
        val element = obj.map { it.key.raw to JsonTreeParser.parse(JSON.stringify(FacetStats.serializer(), it.value)) }

        output.asJsonOutput().writeTree(JsonObject(element.toMap()))
    }

    override fun deserialize(input: Decoder): Map<Attribute, FacetStats> {
        val json = input.asJsonInput().jsonObject

        return json.map { (key, element) ->
            key.toAttribute() to JSON.parse(FacetStats.serializer(), element.toString())
        }.toMap()
    }
}