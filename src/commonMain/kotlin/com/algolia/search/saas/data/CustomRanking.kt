package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KeyAsc
import com.algolia.search.saas.serialize.KeyDesc
import com.algolia.search.saas.serialize.regexAsc
import com.algolia.search.saas.serialize.regexDesc
import com.algolia.search.saas.toAttribute
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(CustomRanking.Companion::class)
sealed class CustomRanking(override val raw: String) : Raw<String> {

    data class Asc(val attribute: Attribute) : CustomRanking("$KeyAsc($attribute)")

    data class Desc(val attribute: Attribute) : CustomRanking("$KeyDesc($attribute)")

    data class Other(override val raw: String) : CustomRanking(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : KSerializer<CustomRanking> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: CustomRanking) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): CustomRanking {
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