package com.algolia.search.serialize

import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.SerialClassDescImpl

typealias GeoDistance = Int

@Serializer(forClass = GeoDistance::class)
public object KSerializerGeoDistance : KSerializer<GeoDistance> {
    override val descriptor = SerialClassDescImpl("GeoDistance")

    override fun serialize(encoder: Encoder, obj: GeoDistance) {
        try {
            encoder.encodeInt(obj)
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
