package com.algolia.search.saas.data

import com.algolia.search.saas.toAttribute
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer


@Serializable(Attribute.Companion::class)
data class Attribute(override val raw: String) : Raw<String> {

    override fun toString(): String {
        return raw
    }

    internal companion object : KSerializer<Attribute> {

        override val descriptor = StringSerializer.descriptor

        override fun serialize(encoder: Encoder, obj: Attribute) {
            StringSerializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): Attribute {
            return StringSerializer.deserialize(decoder).toAttribute()
        }
    }
}