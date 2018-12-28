package client.data

import client.serialize.Deserializer
import client.serialize.KeyEqualOnly
import client.serialize.Serializer
import client.serialize.regexEqualOnly
import client.toAttribute
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive


data class NumericAttributeFilter(val attribute: Attribute, val equalOnly: Boolean = false) {

    val raw = if (equalOnly) "$KeyEqualOnly($attribute)" else attribute.raw

    override fun toString(): String {
        return raw
    }

    internal companion object : Serializer<NumericAttributeFilter>, Deserializer<NumericAttributeFilter> {

        override fun serialize(input: NumericAttributeFilter): JsonElement {
            return JsonPrimitive(input.raw)
        }

        override fun deserialize(element: JsonElement): NumericAttributeFilter? {
            return when (element) {
                is JsonPrimitive -> {
                    element.contentOrNull?.let {
                        val findEqualOnly = regexEqualOnly.find(it)

                        when {
                            findEqualOnly != null -> NumericAttributeFilter(
                                findEqualOnly.groupValues[1].toAttribute(),
                                true
                            )
                            else -> NumericAttributeFilter(it.toAttribute())
                        }
                    }
                }
                else -> null
            }
        }
    }
}