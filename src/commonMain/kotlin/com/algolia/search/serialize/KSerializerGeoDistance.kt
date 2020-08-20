package com.algolia.search.serialize

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public typealias GeoDistance = Int

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = GeoDistance::class)
public object KSerializerGeoDistance : KSerializer<GeoDistance> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("GeoDistance")

    override fun serialize(encoder: Encoder, value: GeoDistance) {
        try {
            encoder.encodeInt(value)
        } catch (e: Exception) {
            encoder.encodeInt(-1)
        }
    }

    override fun deserialize(decoder: Decoder): GeoDistance {
        return try {
            decoder.decodeInt()
        } catch (e: Exception) {
            -1
        }
    }
}
