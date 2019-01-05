package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.*
import com.algolia.search.saas.toAttribute
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable(Ranking.Companion::class)
sealed class Ranking(override val raw: String) : Raw<String> {

    object Typo : Ranking(KeyTypo)

    object Geo : Ranking(KeyGeo)

    object Words : Ranking(KeyWords)

    object Filters : Ranking(KeyFilters)

    object Proximity : Ranking(KeyProximity)

    object Attribute : Ranking(KeyAttribute)

    object Exact : Ranking(KeyExact)

    object Custom : Ranking(KeyCustom)

    data class Asc(val attribute: com.algolia.search.saas.data.Attribute) : Ranking("$KeyAsc($attribute)")

    data class Desc(val attribute: com.algolia.search.saas.data.Attribute) : Ranking("$KeyDesc($attribute)")

    data class Unknown(override val raw: String) : Ranking(raw)

    override fun toString(): String {
        return raw
    }

    @Serializer(Ranking::class)
    internal companion object : KSerializer<Ranking> {

        override fun serialize(encoder: Encoder, obj: Ranking) {
            encoder.asJsonOutput().encodeJson(JsonPrimitive(obj.raw))
        }

        override fun deserialize(decoder: Decoder): Ranking {
            val element = decoder.asJsonInput() as JsonLiteral

            val findAsc = regexAsc.find(element.content)
            val findDesc = regexDesc.find(element.content)

            return when {
                findAsc != null -> Asc(findAsc.groupValues[1].toAttribute())
                findDesc != null -> Desc(findDesc.groupValues[1].toAttribute())
                element.content == KeyTypo -> Typo
                element.content == KeyGeo -> Geo
                element.content == KeyWords -> Words
                element.content == KeyFilters -> Filters
                element.content == KeyProximity -> Proximity
                element.content == KeyAttribute -> Attribute
                element.content == KeyExact -> Exact
                element.content == KeyCustom -> Custom
                else -> Unknown(element.content)
            }
        }
    }
}