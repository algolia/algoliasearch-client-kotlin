package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KeyRules
import com.algolia.search.saas.serialize.KeySettings
import com.algolia.search.saas.serialize.KeySynonyms
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(Scope.Companion::class)
sealed class Scope(override val raw: String) : Raw<String> {

    object Settings : Scope(KeySettings)

    object Synonyms : Scope(KeySynonyms)

    object Rules : Scope(KeyRules)

    data class Other(override val raw: String) : Scope(raw)

    companion object : KSerializer<Scope> {

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