package com.algolia.search.saas.serialize

import com.algolia.search.saas.data.Attribute
import com.algolia.search.saas.data.FacetStats
import com.algolia.search.saas.toAttribute
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.HashMapClassDesc
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject


internal object KSerializerFacetStats : KSerializer<Map<Attribute, FacetStats>> {

    override val descriptor = HashMapClassDesc(Attribute.descriptor, FacetStats.serializer().descriptor)

    override fun serialize(encoder: Encoder, obj: Map<Attribute, FacetStats>) {
        val element = obj.map { it.key.raw to Json.nonstrict.toJson(it.value, FacetStats.serializer()) }

        encoder.asJsonOutput().encodeJson(JsonObject(element.toMap()))
    }

    override fun deserialize(decoder: Decoder): Map<Attribute, FacetStats> {
        val json = decoder.asJsonInput().jsonObject

        return json.map { (key, element) ->
            key.toAttribute() to Json.parse(FacetStats.serializer(), element.toString())
        }.toMap()
    }
}