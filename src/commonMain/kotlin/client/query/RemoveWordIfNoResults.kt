package client.query


enum class RemoveWordIfNoResults(val raw: String) {
    /**
     * No specific processing is done when a query does not return any results (default behavior).
     */
    None("none"),
    /**
     * When a query does not return any results, treat the last word as optional.
     * The process is repeated with words N-1, N-2, etc. until there are results, or the beginning of the query
     * string has been reached.
     */
    LastWords("lastWords"),
    /**
     * When a query does not return any results, treat the first word as optional.
     * The process is repeated with words 2, 3, etc. until there are results, or the end of the query string has
     * been reached.
     */
    FirstWords("firstWords"),
    /**
     * When a query does not return any results, make a second attempt treating all words as optional.
     * This is equivalent to transforming the implicit AND operator applied between query words to an OR.
     */
    AllOptional("allOptional")
}