package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable(QueryType.Companion::class)
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

    @Serializer(QueryType::class)
    internal companion object : KSerializer<QueryType> {

        override fun serialize(output: Encoder, obj: QueryType) {
            output.asJsonOutput().writeTree(JsonPrimitive(obj.raw))
        }

        override fun deserialize(input: Decoder): QueryType {
            val element = input.asJsonInput() as JsonLiteral

            return when (val content = element.content) {
                KeyPrefixLast -> PrefixLast
                KeyPrefixAll -> PrefixAll
                KeyPrefixNone -> PrefixNone
                else -> Unknown(content)
            }
        }
    }
}