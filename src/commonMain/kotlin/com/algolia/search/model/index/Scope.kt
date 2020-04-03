package com.algolia.search.model.index

import com.algolia.search.endpoint.EndpointIndex
import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyRules
import com.algolia.search.serialize.KeySettings
import com.algolia.search.serialize.KeySynonyms
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

/**
 * Possible [Scope] to copy for a [EndpointIndex.copyIndex] operation.
 */
@Serializable(Scope.Companion::class)
public sealed class Scope(override val raw: String) : Raw<String> {

    /**
     * Scope for [com.algolia.search.model.settings.Settings]
     */
    public object Settings : Scope(KeySettings)

    /**
     * Scope for [com.algolia.search.model.synonym.Synonym]
     */
    public object Synonyms : Scope(KeySynonyms)

    /**
     * Scope for [com.algolia.search.model.rule.Rule]
     */
    public object Rules : Scope(KeyRules)

    public data class Other(override val raw: String) : Scope(raw)

    companion object : KSerializer<Scope> {

        private val serializer = String.serializer()

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: Scope) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): Scope {
            return when (val string = serializer.deserialize(decoder)) {
                KeySettings -> Settings
                KeySynonyms -> Synonyms
                KeyRules -> Rules
                else -> Other(string)
            }
        }
    }
}
