package com.algolia.search.model.search

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyPrefixAll
import com.algolia.search.serialize.KeyPrefixLast
import com.algolia.search.serialize.KeyPrefixNone
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable(QueryType.Companion::class)
public sealed class QueryType(override val raw: String) : Raw<String> {

    /**
     *  Only the last word is interpreted as a prefix (default behavior).
     */
    public object PrefixLast : QueryType(KeyPrefixLast)

    /**
     * All query words are interpreted as prefixes.
     * This option is not recommended, as it tends to yield counter intuitive results and has a negative impact
     * on performance.
     */
    public object PrefixAll : QueryType(KeyPrefixAll)

    /**
     * No query word is interpreted as a prefix.
     * This option is not recommended, especially in an instant search setup, as the user will have to type
     * the entire word(s) before getting any relevant results.
     */
    public object PrefixNone : QueryType(KeyPrefixNone)

    public data class Other(override val raw: String) : QueryType(raw)

    override fun toString(): String {
        return raw
    }

    public companion object : KSerializer<QueryType> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: QueryType) {
            serializer.serialize(encoder, value.raw)
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
