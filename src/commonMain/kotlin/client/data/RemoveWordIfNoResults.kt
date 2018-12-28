package client.data

import client.serialize.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull


sealed class RemoveWordIfNoResults(open val raw: String) {

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

    internal companion object : Serializer<RemoveWordIfNoResults> {

        override fun serialize(input: RemoveWordIfNoResults?): JsonElement {
            return input.unwrap { JsonPrimitive(raw) }
        }

        override fun deserialize(element: JsonElement): RemoveWordIfNoResults? {
            return when (val content = element.contentOrNull) {
                KeyNone -> None
                KeyLastWords -> LastWords
                KeyFirstWords -> FirstWords
                KeyAllOptional -> AllOptional
                null -> null
                else -> Unknown(content)
            }
        }
    }
}