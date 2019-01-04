package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.*
import com.algolia.search.saas.toAttribute
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable(CustomRanking.Companion::class)
sealed class CustomRanking(override val raw: String) : Raw<String> {

    data class Asc(val attribute: Attribute) : CustomRanking("$KeyAsc($attribute)")

    data class Desc(val attribute: Attribute) : CustomRanking("$KeyDesc($attribute)")

    data class Unknown(override val raw: String) : CustomRanking(raw)

    override fun toString(): String {
        return raw
    }

    @Serializer(CustomRanking::class)
    internal companion object : KSerializer<CustomRanking> {

        override fun serialize(output: Encoder, obj: CustomRanking) {
            output.asJsonOutput().writeTree(JsonPrimitive(obj.raw))
        }

        override fun deserialize(input: Decoder): CustomRanking {
            val element = input.asJsonInput() as JsonLiteral

            val findAsc = regexAsc.find(element.content)
            val findDesc = regexDesc.find(element.content)

            return when {
                findAsc != null -> Asc(findAsc.groupValues[1].toAttribute())
                findDesc != null -> Desc(findDesc.groupValues[1].toAttribute())
                else -> Unknown(element.content)
            }
        }
    }
}