package com.algolia.search.model.settings

import com.algolia.search.model.Attribute
import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyAsc
import com.algolia.search.serialize.KeyDesc
import com.algolia.search.serialize.regexAsc
import com.algolia.search.serialize.regexDesc
import com.algolia.search.helper.toAttribute
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(CustomRankingCriteria.Companion::class)
sealed class CustomRankingCriteria(override val raw: String) : Raw<String> {

    data class Asc(val attribute: Attribute) : CustomRankingCriteria("$KeyAsc($attribute)")

    data class Desc(val attribute: Attribute) : CustomRankingCriteria("$KeyDesc($attribute)")

    data class Other(override val raw: String) : CustomRankingCriteria(raw)

    override fun toString(): String {
        return raw
    }

    companion object : KSerializer<CustomRankingCriteria> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: CustomRankingCriteria) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): CustomRankingCriteria {
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