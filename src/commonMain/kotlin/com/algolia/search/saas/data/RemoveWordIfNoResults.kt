package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KeyAllOptional
import com.algolia.search.saas.serialize.KeyFirstWords
import com.algolia.search.saas.serialize.KeyLastWords
import com.algolia.search.saas.serialize.KeyNone
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(RemoveWordIfNoResults.Companion::class)
sealed class RemoveWordIfNoResults(override val raw: String) : Raw<String> {

    /**
     * No specific processing is done when a query does not return any results (default behavior).
     */
    object None : RemoveWordIfNoResults(KeyNone)

    /**
     * When a query does not return any results, treat the last word as optional.
     * The process is repeated with words N-1, N-2, etc. until there are results, or the beginning of the query
     * name has been reached.
     */
    object LastWords : RemoveWordIfNoResults(KeyLastWords)

    /**
     * When a query does not return any results, treat the first word as optional.
     * The process is repeated with words 2, 3, etc. until there are results, or the end of the query name has
     * been reached.
     */
    object FirstWords : RemoveWordIfNoResults(KeyFirstWords)

    /**
     * When a query does not return any results, make a second attempt treating all words as optional.
     * This is equivalent to transforming the implicit AND operator applied between query words to an OR.
     */
    object AllOptional : RemoveWordIfNoResults(KeyAllOptional)

    data class Other(override val raw: String) : RemoveWordIfNoResults(raw)

    override fun toString(): String {
        return raw
    }

    companion object : KSerializer<RemoveWordIfNoResults> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: RemoveWordIfNoResults) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): RemoveWordIfNoResults {
            val string = serializer.deserialize(decoder)

            return when (string) {
                KeyNone -> None
                KeyLastWords -> LastWords
                KeyFirstWords -> FirstWords
                KeyAllOptional -> AllOptional
                else -> Other(string)
            }
        }
    }
}