package client.data

import client.serialize.*
import client.toAttribute
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive


sealed class CustomRanking(override val raw: String) : RawString {

    data class Asc(val attribute: Attribute) : CustomRanking("$KeyAsc($attribute)")

    data class Desc(val attribute: Attribute) : CustomRanking("$KeyDesc($attribute)")

    data class Unknown(override val raw: String) : CustomRanking(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : RawStringSerializer<CustomRanking>, Deserializer<CustomRanking> {

        override fun deserialize(element: JsonElement): CustomRanking? {
            return when (element) {
                is JsonPrimitive -> {
                    element.contentOrNull?.let {
                        val findAsc = regexAsc.find(it)
                        val findDesc = regexDesc.find(it)

                        when {
                            findAsc != null -> Asc(findAsc.groupValues[1].toAttribute())
                            findDesc != null -> Desc(findDesc.groupValues[1].toAttribute())
                            else -> Unknown(it)
                        }
                    }
                }
                else -> null
            }
        }
    }
}