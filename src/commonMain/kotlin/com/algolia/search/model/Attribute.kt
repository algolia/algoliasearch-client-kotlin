package com.algolia.search.model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.helper.toAttribute
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

/**
 * An attribute is a key in the json definition of a record.
 * Example:
 *
 * ```
 * {
 *  "color": "red"
 * }
 * ```
 *
 * This record has an attribute "color", and its value is "red".
 */
@Serializable(Attribute.Companion::class)
public data class Attribute(override val raw: String) : Raw<String> {

    init {
        if (raw.isBlank()) throw EmptyStringException("Attribute")
    }

    override fun toString(): String {
        return raw
    }

    public companion object : KSerializer<Attribute> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: Attribute) {
            String.serializer().serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): Attribute {
            return serializer.deserialize(decoder).toAttribute()
        }
    }
}
