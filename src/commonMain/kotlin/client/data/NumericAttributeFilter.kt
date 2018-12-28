package client.data

import client.serialize.KeyEqualOnly
import client.serialize.Serializer
import client.serialize.regexEqualOnly
import client.serialize.unwrap
import client.toAttribute
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull


data class NumericAttributeFilter(val attribute: Attribute, val equalOnly: Boolean = false) {

    val raw = if (equalOnly) "$KeyEqualOnly($attribute)" else attribute.name

    override fun toString(): String {
        return raw
    }

    internal companion object : Serializer<NumericAttributeFilter> {

        override fun serialize(input: NumericAttributeFilter?): JsonElement {
            return input.unwrap { JsonPrimitive(raw) }
        }

        override fun deserialize(element: JsonElement): NumericAttributeFilter? {
            return when (val content = element.contentOrNull) {
                null -> null
                else -> {
                    val findEqualOnly = regexEqualOnly.find(content)

                    when {
                        findEqualOnly != null -> NumericAttributeFilter(
                            findEqualOnly.groupValues[1].toAttribute(),
                            true
                        )
                        else -> NumericAttributeFilter(content.toAttribute())
                    }
                }
            }
        }
    }
}