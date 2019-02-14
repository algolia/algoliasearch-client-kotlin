package com.algolia.search.model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.toAttribute
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(Attribute.Companion::class)
data class Attribute(override val raw: String) : Raw<String> {

    init {
        if (raw.isEmpty()) throw EmptyStringException("Attribute")
    }

    override fun toString(): String {
        return raw
    }

    companion object : KSerializer<Attribute> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: Attribute) {
            StringSerializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): Attribute {
            return serializer.deserialize(decoder).toAttribute()
        }
    }
}