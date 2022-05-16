package com.algolia.search.model.settings

import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Attribute
import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.regexEqualOnly
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(NumericAttributeFilter.Companion::class)
public data class NumericAttributeFilter(
    /**
     * Numeric attribute that can be used as numerical filters.
     */
    val attribute: Attribute,
    /**
     * If you only need to filter on a numeric value based on equality (i.e. with the operators = or !=)
     * you can use this modifier. All other operators will be disabled.
     */
    val equalOnly: Boolean = false
) : Raw<String> {

    override val raw: String = if (equalOnly) "${Key.EqualOnly}($attribute)" else attribute.raw

    override fun toString(): String {
        return raw
    }

    public companion object : KSerializer<NumericAttributeFilter> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: NumericAttributeFilter) {
            serializer.serialize(encoder, value.raw)
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
