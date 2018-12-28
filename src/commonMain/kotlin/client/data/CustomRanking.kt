package client.data

import client.serialize.*
import client.toAttribute
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.contentOrNull


sealed class CustomRanking(override val raw: String) : Raw {

    data class Asc(val attribute: Attribute) : CustomRanking("$KeyAsc($attribute)")

    data class Desc(val attribute: Attribute) : CustomRanking("$KeyDesc($attribute)")

    data class Unknown(override val raw: String) : CustomRanking(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : RawSerializer<CustomRanking>, Deserializer<CustomRanking> {

        override fun deserialize(element: JsonElement): CustomRanking? {
            return when (val content = element.contentOrNull) {
                null -> null
                else -> {
                    val findAsc = regexAsc.find(content)
                    val findDesc = regexDesc.find(content)

                    when {
                        findAsc != null -> Asc(findAsc.groupValues[1].toAttribute())
                        findDesc != null -> Desc(findDesc.groupValues[1].toAttribute())
                        else -> Unknown(content)
                    }
                }
            }
        }
    }
}