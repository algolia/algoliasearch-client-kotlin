package client.data

import client.serialize.KeyIgnorePlurals
import client.serialize.KeyMultiWordsSynonym
import client.serialize.KeySingleWordSynonym


sealed class AlternativesAsExact(open val raw: String) {

    /**
     * Alternative words added by the [client.query.Query.ignorePlurals] feature.
     */
    object IgnorePlurals : AlternativesAsExact(KeyIgnorePlurals)

    /**
     * Single-word synonyms (example: “NY” = “NYC”).
     */
    object SingleWordSynonym : AlternativesAsExact(KeySingleWordSynonym)

    /**
     * Multiple-words synonyms (example: “NY” = “New York”).
     */
    object MultiWordsSynonym : AlternativesAsExact(KeyMultiWordsSynonym)

    data class Unknown(override val raw: String) : AlternativesAsExact(raw)
}