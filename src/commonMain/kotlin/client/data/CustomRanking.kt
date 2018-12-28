package client.data

import client.serialize.*
import client.toAttribute
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull


sealed class CustomRanking(open val raw: String) {

    data class Asc(val attribute: Attribute) : CustomRanking("$KeyAsc($attribute)")

    data class Desc(val attribute: Attribute) : CustomRanking("$KeyDesc($attribute)")

    data class Unknown(override val raw: String) : CustomRanking(raw)

    internal companion object : Serializer<CustomRanking> {

        override fun serialize(input: CustomRanking?): JsonElement {
            return input.unwrap { JsonPrimitive(raw) }
        }

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