package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KeyEqualOnly
import com.algolia.search.saas.serialize.regexEqualOnly
import com.algolia.search.saas.toAttribute
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(NumericAttributeFilter.Companion::class)
data class NumericAttributeFilter(val attribute: Attribute, val equalOnly: Boolean = false) : Raw<String> {

    override val raw = if (equalOnly) "$KeyEqualOnly($attribute)" else attribute.raw

    override fun toString(): String {
        return raw
    }

    internal companion object : KSerializer<NumericAttributeFilter> {

        override val descriptor = StringSerializer.descriptor

        override fun serialize(encoder: Encoder, obj: NumericAttributeFilter) {
            StringSerializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): NumericAttributeFilter {
            val string = StringSerializer.deserialize(decoder)

            val findEqualOnly = regexEqualOnly.find(string)

            return when {
                findEqualOnly != null -> NumericAttributeFilter(
                    findEqualOnly.groupValues[1].toAttribute(),
                    true
                )
                else -> NumericAttributeFilter(string.toAttribute())
            }
        }
    }
}