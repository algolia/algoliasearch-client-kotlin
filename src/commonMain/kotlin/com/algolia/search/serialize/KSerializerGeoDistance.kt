package com.algolia.search.serialize

import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.Serializer

typealias GeoDistance = Int

@Serializer(forClass = GeoDistance::class)
public object KSerializerGeoDistance : KSerializer<GeoDistance> {
    override val descriptor = SerialDescriptor("GeoDistance")

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
