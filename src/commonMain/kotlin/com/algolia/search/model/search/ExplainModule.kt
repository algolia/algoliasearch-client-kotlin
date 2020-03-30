package com.algolia.search.model.search

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyMatchAlternatives
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable(ExplainModule.Companion::class)
public sealed class ExplainModule(override val raw: String) : Raw<String> {

    public object MatchAlternatives : ExplainModule(KeyMatchAlternatives)

    public data class Other(override val raw: String) : ExplainModule(raw)

    companion object : KSerializer<ExplainModule> {

        private val serializer = String.serializer()

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: ExplainModule) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): ExplainModule {
            return when (val string = serializer.deserialize(decoder)) {
                KeyMatchAlternatives -> MatchAlternatives
                else -> Other(string)
            }
        }
    }
}
