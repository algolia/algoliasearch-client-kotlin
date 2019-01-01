package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable(RemoveWordIfNoResults.Companion::class)
sealed class RemoveWordIfNoResults(override val raw: String) : RawString {

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

    data class Unknown(override val raw: String) : RemoveWordIfNoResults(raw)

    override fun toString(): String {
        return raw
    }

    @Serializer(RemoveWordIfNoResults::class)
    internal companion object : KSerializer<RemoveWordIfNoResults> {

        override fun serialize(output: Encoder, obj: RemoveWordIfNoResults) {
            val json = output as JSON.JsonOutput

            json.writeTree(JsonPrimitive(obj.raw))
        }

        override fun deserialize(input: Decoder): RemoveWordIfNoResults {
            val element = input.readAsTree() as JsonLiteral

            return when (val content = element.content) {
                KeyNone -> None
                KeyLastWords -> LastWords
                KeyFirstWords -> FirstWords
                KeyAllOptional -> AllOptional
                else -> Unknown(content)
            }
        }
    }
}