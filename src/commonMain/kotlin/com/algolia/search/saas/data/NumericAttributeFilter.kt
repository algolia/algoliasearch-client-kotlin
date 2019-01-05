package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KeyEqualOnly
import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.serialize.asJsonOutput
import com.algolia.search.saas.serialize.regexEqualOnly
import com.algolia.search.saas.toAttribute
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable(NumericAttributeFilter.Companion::class)
data class NumericAttributeFilter(val attribute: Attribute, val equalOnly: Boolean = false) : Raw<String> {

    override val raw = if (equalOnly) "$KeyEqualOnly($attribute)" else attribute.raw

    override fun toString(): String {
        return raw
    }

    @Serializer(NumericAttributeFilter::class)
    internal companion object : KSerializer<NumericAttributeFilter> {

        override fun serialize(encoder: Encoder, obj: NumericAttributeFilter) {
            encoder.asJsonOutput().encodeJson(JsonPrimitive(obj.raw))
        }

        override fun deserialize(decoder: Decoder): NumericAttributeFilter {
            val element = decoder.asJsonInput() as JsonLiteral

            val findEqualOnly = regexEqualOnly.find(element.content)

            return when {
                findEqualOnly != null -> NumericAttributeFilter(
                    findEqualOnly.groupValues[1].toAttribute(),
                    true
                )
                else -> NumericAttributeFilter(element.content.toAttribute())
            }
        }
    }
}