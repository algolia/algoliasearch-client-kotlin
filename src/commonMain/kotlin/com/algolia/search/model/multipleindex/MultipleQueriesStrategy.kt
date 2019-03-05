package com.algolia.search.model.multipleindex

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyNone
import com.algolia.search.serialize.KeyStopIfEnoughMatches
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(MultipleQueriesStrategy.Companion::class)
public sealed class MultipleQueriesStrategy(override val raw: String) : Raw<String> {

    public object None : MultipleQueriesStrategy(KeyNone)

    public object StopIfEnoughMatches : MultipleQueriesStrategy(KeyStopIfEnoughMatches)

    public data class Other(override val raw: String) : MultipleQueriesStrategy(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : KSerializer<MultipleQueriesStrategy> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: MultipleQueriesStrategy) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): MultipleQueriesStrategy {
            val string = serializer.deserialize(decoder)

            return when (string) {
                KeyNone -> None
                KeyStopIfEnoughMatches -> StopIfEnoughMatches
                else -> Other(string)
            }
        }
    }
}