package com.algolia.search.serialize

import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Facet
import com.algolia.search.serialize.internal.JsonNonStrict
import com.algolia.search.serialize.internal.asJsonInput
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.mapSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public object KSerializerFacetMap : KSerializer<Map<Attribute, List<Facet>>> {

    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(Attribute.descriptor.serialName) {
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
        val json = JsonNonStrict.decodeFromJsonElement(serializer, decoder.asJsonInput())

        return json.map { (key, value) -> key.toAttribute() to value.map { Facet(it.key, it.value) } }.toMap()
    }
}
