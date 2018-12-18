package client.data


sealed class AlternativesAsExact(open val raw: String) {

    /**
     * Alternative words added by the [Query.ignorePlurals] feature.
     */
    object IgnorePlurals : AlternativesAsExact("ignorePlurals")

    /**
     * Single-word synonyms (example: “NY” = “NYC”).
     */
    object SingleWordSynonym : AlternativesAsExact("singleWordSynonym")

    /**
     * Multiple-words synonyms (example: “NY” = “New York”).
     */
    object MultiWordsSynonym : AlternativesAsExact("multiWordsSynonym")

    data class Unknown(override val raw: String) : AlternativesAsExact(raw)
}