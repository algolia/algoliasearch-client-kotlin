package com.algolia.search.model.search

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyAllOptional
import com.algolia.search.serialize.KeyFirstWords
import com.algolia.search.serialize.KeyLastWords
import com.algolia.search.serialize.KeyNone
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable(RemoveWordIfNoResults.Companion::class)
public sealed class RemoveWordIfNoResults(override val raw: String) : Raw<String> {

    /**
     * No specific processing is done when a query does not return any results (default behavior).
     */
    public object None : RemoveWordIfNoResults(KeyNone)

    /**
     * When a query does not return any results, treat the last word as optional.
     * The process is repeated with words N-1, N-2, etc. until there are results, or the beginning of the query
     * name has been reached.
     */
    public object LastWords : RemoveWordIfNoResults(KeyLastWords)

    /**
     * When a query does not return any results, treat the first word as optional.
     * The process is repeated with words 2, 3, etc. until there are results, or the end of the query name has
     * been reached.
     */
    public object FirstWords : RemoveWordIfNoResults(KeyFirstWords)

    /**
     * When a query does not return any results, make a second attempt treating all words as optional.
     * This is equivalent to transforming the implicit AND operator applied between query words to an OR.
     */
    public object AllOptional : RemoveWordIfNoResults(KeyAllOptional)

    public data class Other(override val raw: String) : RemoveWordIfNoResults(raw)

    override fun toString(): String {
        return raw
    }

    public companion object : KSerializer<RemoveWordIfNoResults> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: RemoveWordIfNoResults) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): RemoveWordIfNoResults {
            return when (val string = serializer.deserialize(decoder)) {
                KeyNone -> None
                KeyLastWords -> LastWords
                KeyFirstWords -> FirstWords
                KeyAllOptional -> AllOptional
                else -> Other(string)
            }
        }
    }
}
