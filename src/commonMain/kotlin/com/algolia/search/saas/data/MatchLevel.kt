package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KeyFull
import com.algolia.search.saas.serialize.KeyNone
import com.algolia.search.saas.serialize.KeyPartial
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(MatchLevel.Companion::class)
sealed class MatchLevel(override val raw: String) : Raw<String> {

    object None : MatchLevel(KeyNone)

    object Partial : MatchLevel(KeyPartial)

    object Full : MatchLevel(KeyFull)

    data class Unknown(override val raw: String) : MatchLevel(raw)

    companion object : KSerializer<MatchLevel> {

        override val descriptor = StringSerializer.descriptor

        override fun serialize(encoder: Encoder, obj: MatchLevel) {
            StringSerializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): MatchLevel {
            val string = StringSerializer.deserialize(decoder)

            return when (string) {
                KeyNone -> None
                KeyPartial -> Partial
                KeyFull -> Full
                else -> Unknown(string)
            }
        }
    }
}