package com.algolia.search.model.search

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyFull
import com.algolia.search.serialize.KeyNone
import com.algolia.search.serialize.KeyPartial
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(MatchLevel.Companion::class)
public sealed class MatchLevel(override val raw: String) : Raw<String> {

    public object None : MatchLevel(KeyNone)

    public object Partial : MatchLevel(KeyPartial)

    public object Full : MatchLevel(KeyFull)

    public data class Other(override val raw: String) : MatchLevel(raw)

    companion object : KSerializer<MatchLevel> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: MatchLevel) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): MatchLevel {
            return when (val string = serializer.deserialize(decoder)) {
                KeyNone -> None
                KeyPartial -> Partial
                KeyFull -> Full
                else -> Other(string)
            }
        }
    }
}