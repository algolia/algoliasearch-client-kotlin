package client.data

import client.serialize.*
import client.toAttribute
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive


data class NumericAttributeFilter(val attribute: Attribute, val equalOnly: Boolean = false) : Raw {

    override val raw = if (equalOnly) "$KeyEqualOnly($attribute)" else attribute.raw

    override fun toString(): String {
        return raw
    }

    internal companion object : RawSerializer<NumericAttributeFilter>, Deserializer<NumericAttributeFilter> {

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