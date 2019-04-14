package com.algolia.search.model.settings

import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Attribute
import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyAsc
import com.algolia.search.serialize.KeyDesc
import com.algolia.search.serialize.regexAsc
import com.algolia.search.serialize.regexDesc
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(CustomRankingCriterion.Companion::class)
public sealed class CustomRankingCriterion(override val raw: String) : Raw<String> {

    public data class Asc(val attribute: Attribute) : CustomRankingCriterion("$KeyAsc($attribute)")

    public data class Desc(val attribute: Attribute) : CustomRankingCriterion("$KeyDesc($attribute)")

    public data class Other(override val raw: String) : CustomRankingCriterion(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : KSerializer<CustomRankingCriterion> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: CustomRankingCriterion) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): CustomRankingCriterion {
            val string = serializer.deserialize(decoder)

            val findAsc = regexAsc.find(string)
            val findDesc = regexDesc.find(string)

            return when {
                findAsc != null -> Asc(findAsc.groupValues[1].toAttribute())
                findDesc != null -> Desc(findDesc.groupValues[1].toAttribute())
                else -> Other(string)
            }
        }
    }
}