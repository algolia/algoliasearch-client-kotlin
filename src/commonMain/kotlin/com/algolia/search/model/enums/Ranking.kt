package com.algolia.search.model.enums

import com.algolia.search.model.Raw
import com.algolia.search.serialize.*
import com.algolia.search.toAttribute
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


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

    data class Asc(val attribute: com.algolia.search.model.Attribute) : Ranking("$KeyAsc($attribute)")

    data class Desc(val attribute: com.algolia.search.model.Attribute) : Ranking("$KeyDesc($attribute)")

    data class Other(override val raw: String) : Ranking(raw)

    override fun toString(): String {
        return raw
    }

    companion object : KSerializer<Ranking> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: Ranking) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): Ranking {
            val string = serializer.deserialize(decoder)

            val findAsc = regexAsc.find(string)
            val findDesc = regexDesc.find(string)

            return when {
                findAsc != null -> Asc(findAsc.groupValues[1].toAttribute())
                findDesc != null -> Desc(findDesc.groupValues[1].toAttribute())
                string == KeyTypo -> Typo
                string == KeyGeo -> Geo
                string == KeyWords -> Words
                string == KeyFilters -> Filters
                string == KeyProximity -> Proximity
                string == KeyAttribute -> Attribute
                string == KeyExact -> Exact
                string == KeyCustom -> Custom
                else -> Other(string)
            }
        }
    }
}