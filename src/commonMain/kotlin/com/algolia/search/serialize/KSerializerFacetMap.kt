package com.algolia.search.serialize

import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Facet
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.descriptors.mapSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public object KSerializerFacetMap : KSerializer<Map<Attribute, List<Facet>>> {

    @OptIn(InternalSerializationApi::class)
    override val descriptor: SerialDescriptor =
        buildSerialDescriptor(Attribute.descriptor.serialName, StructureKind.MAP) {
            mapSerialDescriptor(String.serializer().descriptor, Int.serializer().descriptor)
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
        decoder.asJsonInput()
        val json = JsonNonStrict.decodeFromJsonElement(serializer, decoder.asJsonInput())

        return json.map { (key, value) -> key.toAttribute() to value.map { Facet(it.key, it.value) } }.toMap()
    }
}
