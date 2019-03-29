package com.algolia.search.model.settings

import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Raw
import com.algolia.search.serialize.*
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(RankingCriterium.Companion::class)
public sealed class RankingCriterium(override val raw: String) : Raw<String> {

    public object Typo : RankingCriterium(KeyTypo)

    public object Geo : RankingCriterium(KeyGeo)

    public object Words : RankingCriterium(KeyWords)

    public object Filters : RankingCriterium(KeyFilters)

    public object Proximity : RankingCriterium(KeyProximity)

    public object Attribute : RankingCriterium(KeyAttribute)

    public object Exact : RankingCriterium(KeyExact)

    public object Custom : RankingCriterium(KeyCustom)

    public data class Asc(val attribute: com.algolia.search.model.Attribute) : RankingCriterium("$KeyAsc($attribute)")

    public data class Desc(val attribute: com.algolia.search.model.Attribute) : RankingCriterium("$KeyDesc($attribute)")

    public data class Other(override val raw: String) : RankingCriterium(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : KSerializer<RankingCriterium> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: RankingCriterium) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): RankingCriterium {
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