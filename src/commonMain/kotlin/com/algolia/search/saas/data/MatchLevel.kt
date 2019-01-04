package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable(MatchLevel.Companion::class)
sealed class MatchLevel(override val raw: String) : Raw<String> {

    object None : MatchLevel(KeyNone)

    object Partial : MatchLevel(KeyPartial)

    object Full : MatchLevel(KeyFull)

    data class Unknown(override val raw: String) : MatchLevel(raw)

    @Serializer(MatchLevel::class)
    companion object : KSerializer<MatchLevel> {

        override fun serialize(output: Encoder, obj: MatchLevel) {
            output.asJsonOutput().writeTree(JsonPrimitive(obj.raw))
        }

        override fun deserialize(input: Decoder): MatchLevel {
            val element = input.asJsonInput() as JsonLiteral

            return when (val content = element.content) {
                KeyNone -> None
                KeyPartial -> Partial
                KeyFull -> Full
                else -> Unknown(content)
            }
        }
    }
}