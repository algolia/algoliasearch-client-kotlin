package com.algolia.search.saas.serialize

import com.algolia.search.saas.data.Attribute
import com.algolia.search.saas.data.Facet
import com.algolia.search.saas.toAttribute
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.HashMapClassDesc
import kotlinx.serialization.internal.HashMapSerializer
import kotlinx.serialization.internal.IntSerializer
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.Json


internal object KSerializerFacets : KSerializer<Map<Attribute, List<Facet>>> {

    override val descriptor = HashMapClassDesc(
        Attribute.descriptor,
        HashMapClassDesc(
            StringSerializer.descriptor,
            IntSerializer.descriptor
        )
    )

    private val serializer = HashMapSerializer(StringSerializer, HashMapSerializer(StringSerializer, IntSerializer))

    override fun serialize(encoder: Encoder, obj: Map<Attribute, List<Facet>>) {
        val element = obj.map { (key, value) -> key.raw to value.map { it.name to it.count }.toMap() }.toMap()

        serializer.serialize(encoder, element)
    }

    override fun deserialize(decoder: Decoder): Map<Attribute, List<Facet>> {
        val json = Json.nonstrict.fromJson(serializer, decoder.asJsonInput())

        return json.map { (key, value) -> key.toAttribute() to value.map { Facet(it.key, it.value) } }.toMap()
    }
}