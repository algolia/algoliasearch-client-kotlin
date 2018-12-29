package client.data

import client.serialize.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive


sealed class QueryType(override val raw: String) : RawString {

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

    override fun toString(): String {
        return raw
    }

    internal companion object : RawStringSerializer<QueryType>, Deserializer<QueryType> {

        override fun deserialize(element: JsonElement): QueryType? {
            return when (element) {
                is JsonPrimitive -> when (val content = element.contentOrNull) {
                    KeyPrefixLast -> PrefixLast
                    KeyPrefixAll -> PrefixAll
                    KeyPrefixNone -> PrefixNone
                    null -> null
                    else -> Unknown(content)
                }
                else -> null
            }
        }
    }
}