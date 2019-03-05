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


@Serializable(CustomRankingCriteria.Companion::class)
public sealed class CustomRankingCriteria(override val raw: String) : Raw<String> {

    public data class Asc(val attribute: Attribute) : CustomRankingCriteria("$KeyAsc($attribute)")

    public data class Desc(val attribute: Attribute) : CustomRankingCriteria("$KeyDesc($attribute)")

    public data class Other(override val raw: String) : CustomRankingCriteria(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : KSerializer<CustomRankingCriteria> {

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