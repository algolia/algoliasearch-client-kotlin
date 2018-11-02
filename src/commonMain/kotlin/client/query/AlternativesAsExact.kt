package client.query


enum class AlternativesAsExact(val raw: String) {
    /**
     * Alternative words added by the [Query.ignorePlurals] feature.
     */
    IgnorePlurals("ignorePlurals"),
    /**
     * Single-word synonyms (example: “NY” = “NYC”).
     */
    SingleWordSynonym("singleWordSynonym"),
    /**
     * Multiple-words synonyms (example: “NY” = “New York”).
     */
    MultiWordSynonym("multiWordsSynonym")
}