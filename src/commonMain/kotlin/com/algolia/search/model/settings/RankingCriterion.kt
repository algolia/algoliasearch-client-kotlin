package com.algolia.search.model.settings

import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Raw
import com.algolia.search.serialize.*
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(RankingCriterion.Companion::class)
public sealed class RankingCriterion(override val raw: String) : Raw<String> {

    public object Typo : RankingCriterion(KeyTypo)

    public object Geo : RankingCriterion(KeyGeo)

    public object Words : RankingCriterion(KeyWords)

    public object Filters : RankingCriterion(KeyFilters)

    public object Proximity : RankingCriterion(KeyProximity)

    public object Attribute : RankingCriterion(KeyAttribute)

    public object Exact : RankingCriterion(KeyExact)

    public object Custom : RankingCriterion(KeyCustom)

    public data class Asc(val attribute: com.algolia.search.model.Attribute) : RankingCriterion("$KeyAsc($attribute)")

    public data class Desc(val attribute: com.algolia.search.model.Attribute) : RankingCriterion("$KeyDesc($attribute)")

    public data class Other(override val raw: String) : RankingCriterion(raw)

    override fun toString(): String {
        return raw
    }

    companion object : KSerializer<RankingCriterion> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: RankingCriterion) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): RankingCriterion {
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