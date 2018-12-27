package client.data

import client.serialize.KeyPrefixAll
import client.serialize.KeyPrefixLast
import client.serialize.KeyPrefixNone


sealed class QueryType(open val raw: String) {

    /**
     *  Only the last word is interpreted as a prefix (default behavior).
     */
    object PrefixLast : QueryType(KeyPrefixLast)

    /**
     * # All query words are interpreted as prefixes.
     * This option is not recommended, as it tends to yield counter intuitive results and has a negative impact
     * on performance.
     */
    object PrefixAll : QueryType(KeyPrefixAll)

    /**
     * No query word is interpreted as a prefix.
     * This option is not recommended, especially in an instant search setup, as the user will have to type
     * the entire word(s) before getting any relevant results.
     */
    object PrefixNone : QueryType(KeyPrefixNone)

    data class Unknown(override val raw: String) : QueryType(raw)
}