package client.query


enum class QueryType(val raw: String) {
    /**
     *  Only the last word is interpreted as a prefix (default behavior).
     */
    PrefixLast("prefixLast"),
    /**
     * # All query words are interpreted as prefixes.
     * This option is not recommended, as it tends to yield counter intuitive results and has a negative impact
     * on performance.
     */
    PrefixAll("prefixAll"),
    /**
     * No query word is interpreted as a prefix.
     * This option is not recommended, especially in an instant search setup, as the user will have to type
     * the entire word(s) before getting any relevant results.
     */
    PrefixNone("prefixNone")
}