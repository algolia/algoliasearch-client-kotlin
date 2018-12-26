package client.data


sealed class RemoveWordIfNoResults(open val raw: String) {

    /**
     * No specific processing is done when a query does not return any results (default behavior).
     */
    object None : RemoveWordIfNoResults("none")

    /**
     * When a query does not return any results, treat the last word as optional.
     * The process is repeated with words N-1, N-2, etc. until there are results, or the beginning of the query
     * name has been reached.
     */
    object LastWords : RemoveWordIfNoResults("lastWords")

    /**
     * When a query does not return any results, treat the first word as optional.
     * The process is repeated with words 2, 3, etc. until there are results, or the end of the query name has
     * been reached.
     */
    object FirstWords : RemoveWordIfNoResults("firstWords")

    /**
     * When a query does not return any results, make a second attempt treating all words as optional.
     * This is equivalent to transforming the implicit AND operator applied between query words to an OR.
     */
    object AllOptional : RemoveWordIfNoResults("allOptional")

    data class Unknown(override val raw: String) : RemoveWordIfNoResults(raw)
}