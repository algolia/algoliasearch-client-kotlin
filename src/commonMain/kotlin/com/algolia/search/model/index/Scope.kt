package com.algolia.search.model.index

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyRules
import com.algolia.search.serialize.KeySettings
import com.algolia.search.serialize.KeySynonyms
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(Scope.Companion::class)
public sealed class Scope(override val raw: String) : Raw<String> {

    public object Settings : Scope(KeySettings)

    public object Synonyms : Scope(KeySynonyms)

    public object Rules : Scope(KeyRules)

    public data class Other(override val raw: String) : Scope(raw)

    internal companion object : KSerializer<Scope> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: Scope) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): Scope {
            val string = serializer.deserialize(decoder)

            return when (string) {
                KeySettings -> Settings
                KeySynonyms -> Synonyms
                KeyRules -> Rules
                else -> Other(string)
            }
        }
    }
}