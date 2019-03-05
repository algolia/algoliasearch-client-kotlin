package com.algolia.search.model.settings

import com.algolia.search.model.Attribute
import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyEqualOnly
import com.algolia.search.serialize.regexEqualOnly
import com.algolia.search.toAttribute
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(NumericAttributeFilter.Companion::class)
data class NumericAttributeFilter(val attribute: Attribute, val equalOnly: Boolean = false) :
    Raw<String> {

    override val raw = if (equalOnly) "$KeyEqualOnly($attribute)" else attribute.raw

    override fun toString(): String {
        return raw
    }

    companion object : KSerializer<NumericAttributeFilter> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: NumericAttributeFilter) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): NumericAttributeFilter {
            val string = serializer.deserialize(decoder)

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