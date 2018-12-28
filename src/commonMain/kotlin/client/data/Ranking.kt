package client.data

import client.serialize.*
import client.toAttribute
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive


sealed class Ranking(override val raw: String) : Raw {

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

    internal companion object : RawSerializer<Ranking>, Deserializer<Ranking> {

        override fun deserialize(element: JsonElement): Ranking? {
            return when (element) {
                is JsonPrimitive -> {
                    element.contentOrNull?.let {
                        val findAsc = regexAsc.find(it)
                        val findDesc = regexDesc.find(it)

                        when {
                            findAsc != null -> Asc(findAsc.groupValues[1].toAttribute())
                            findDesc != null -> Desc(findDesc.groupValues[1].toAttribute())
                            it == KeyTypo -> Typo
                            it == KeyGeo -> Geo
                            it == KeyWords -> Words
                            it == KeyFilters -> Filters
                            it == KeyProximity -> Proximity
                            it == KeyAttribute -> Attribute
                            it == KeyExact -> Exact
                            it == KeyCustom -> Custom
                            else -> Unknown(it)
                        }
                    }
                }
                else -> null
            }
        }
    }
}