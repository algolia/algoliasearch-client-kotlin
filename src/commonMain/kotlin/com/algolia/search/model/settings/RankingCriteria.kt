package com.algolia.search.model.settings

import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Raw
import com.algolia.search.serialize.*
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(RankingCriteria.Companion::class)
public sealed class RankingCriteria(override val raw: String) : Raw<String> {

    public object Typo : RankingCriteria(KeyTypo)

    public object Geo : RankingCriteria(KeyGeo)

    public object Words : RankingCriteria(KeyWords)

    public object Filters : RankingCriteria(KeyFilters)

    public object Proximity : RankingCriteria(KeyProximity)

    public object Attribute : RankingCriteria(KeyAttribute)

    public object Exact : RankingCriteria(KeyExact)

    public object Custom : RankingCriteria(KeyCustom)

    public data class Asc(val attribute: com.algolia.search.model.Attribute) : RankingCriteria("$KeyAsc($attribute)")

    public data class Desc(val attribute: com.algolia.search.model.Attribute) : RankingCriteria("$KeyDesc($attribute)")

    public data class Other(override val raw: String) : RankingCriteria(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : KSerializer<RankingCriteria> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: RankingCriteria) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): RankingCriteria {
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