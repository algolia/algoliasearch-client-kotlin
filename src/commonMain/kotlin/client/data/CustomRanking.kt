package client.data

import client.serialize.KeyAsc
import client.serialize.KeyDesc
import client.serialize.Serializer
import client.serialize.unwrap
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
                    val asc = Regex("$KeyAsc\\((.*)\\)")
                    val desc = Regex("$KeyDesc\\((.*)\\)")
                    val findAsc = asc.find(content)
                    val findDesc = desc.find(content)

                    when {
                        findAsc != null -> client.data.CustomRanking.Asc(findAsc.groupValues[1].toAttribute())
                        findDesc != null -> client.data.CustomRanking.Desc(findDesc.groupValues[1].toAttribute())
                        else -> client.data.CustomRanking.Unknown(content)
                    }
                }
            }
        }
    }
}