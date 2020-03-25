package com.algolia.search.model.search

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyPrefixAll
import com.algolia.search.serialize.KeyPrefixLast
import com.algolia.search.serialize.KeyPrefixNone
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer

@Serializable(QueryType.Companion::class)
sealed class QueryType(override val raw: String) : Raw<String> {

    /**
     *  Only the last word is interpreted as a prefix (default behavior).
     */
    object PrefixLast : QueryType(KeyPrefixLast)

    /**
     * All query words are interpreted as prefixes.
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

    data class Other(override val raw: String) : QueryType(raw)

    override fun toString(): String {
        return raw
    }

    companion object : KSerializer<QueryType> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: QueryType) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): QueryType {
            return when (val string = serializer.deserialize(decoder)) {
                KeyPrefixLast -> PrefixLast
                KeyPrefixAll -> PrefixAll
                KeyPrefixNone -> PrefixNone
                else -> Other(string)
            }
        }
    }
}
