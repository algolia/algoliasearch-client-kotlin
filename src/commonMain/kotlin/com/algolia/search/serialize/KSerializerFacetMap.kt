package com.algolia.search.serialize

import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Facet
import kotlinx.serialization.*
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer

public object KSerializerFacetMap : KSerializer<Map<Attribute, List<Facet>>> {

    override val descriptor = SerialDescriptor(Attribute.descriptor.serialName, StructureKind.MAP) {
        mapDescriptor(String.serializer().descriptor, Int.serializer().descriptor)
    }

    private val serializer = MapSerializer(
        String.serializer(),
        MapSerializer(String.serializer(), Int.serializer())
    )

    override fun serialize(encoder: Encoder, value: Map<Attribute, List<Facet>>) {
        val element = value.map { (key, value) -> key.raw to value.map { it.value to it.count }.toMap() }.toMap()

        serializer.serialize(encoder, element)
    }

    override fun deserialize(decoder: Decoder): Map<Attribute, List<Facet>> {
        val json = JsonNonStrict.fromJson(serializer, decoder.asJsonInput())

        return json.map { (key, value) -> key.toAttribute() to value.map { Facet(it.key, it.value) } }.toMap()
    }
}
