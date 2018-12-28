package client.data

import client.serialize.*
import client.toAttribute
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull


sealed class Ranking(open val raw: String) {

    object Typo : Ranking(KeyTypo)

    object Geo : Ranking(KeyGeo)

    object Words : Ranking(KeyWords)

    object Filters : Ranking(KeyFilters)

    object Proximity : Ranking(KeyProximity)

    object Attribute : Ranking(KeyAttribute)

    object Exact : Ranking(KeyExact)

    object Custom : Ranking(KeyCustom)

    data class Asc(val attribute: client.data.Attribute) : Ranking("$KeyAsc($attribute)")

    data class Desc(val attribute: client.data.Attribute) : Ranking("$KeyDesc($attribute)")

    data class Unknown(override val raw: String) : Ranking(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : Serializer<Ranking>, Deserializer<Ranking> {

        override fun serialize(input: Ranking?): JsonElement {
            return input.unwrap { JsonPrimitive(raw) }
        }

        override fun deserialize(element: JsonElement): Ranking? {
            return when (val content = element.contentOrNull) {
                null -> null
                else -> {
                    val findAsc = regexAsc.find(content)
                    val findDesc = regexDesc.find(content)

                    when {
                        findAsc != null -> Asc(findAsc.groupValues[1].toAttribute())
                        findDesc != null -> Desc(findDesc.groupValues[1].toAttribute())
                        content == KeyTypo -> Typo
                        content == KeyGeo -> Geo
                        content == KeyWords -> Words
                        content == KeyFilters -> Filters
                        content == KeyProximity -> Proximity
                        content == KeyAttribute -> Attribute
                        content == KeyExact -> Exact
                        content == KeyCustom -> Custom
                        else -> Unknown(content)
                    }
                }
            }
        }
    }
}