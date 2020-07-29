package com.algolia.search.model.search

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyAttribute
import com.algolia.search.serialize.KeyNone
import com.algolia.search.serialize.KeyWord
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable(ExactOnSingleWordQuery.Companion::class)
public sealed class ExactOnSingleWordQuery(override val raw: String) : Raw<String> {

    /**
     * The exact ranking criterion is set to 1 if the query matches exactly an entire attribute value (default behavior).
     * For example, if you search for the TV show “V”, you want it to match the query “V” before all popular
     * TV shows starting with the letter V.
     */
    public object Attribute : ExactOnSingleWordQuery(KeyAttribute)

    /**
     * The exact ranking criterion is ignored on single word queries.
     */
    public object None : ExactOnSingleWordQuery(KeyNone)

    /**
     * The exact ranking criterion is set to 1 if the query word is found in the record.
     * The query word must be at least 3 characters long and must not be a stop word in any supported language.
     * For example, if you search for the TV show “Road”, and in your dataset you have 2 records,
     * “Road” and “Road Trip”, both will be considered to match exactly.
     */
    public object Word : ExactOnSingleWordQuery(KeyWord)

    public data class Other(override val raw: String) : ExactOnSingleWordQuery(raw)

    override fun toString(): String {
        return raw
    }

    public companion object : KSerializer<ExactOnSingleWordQuery> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: ExactOnSingleWordQuery) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): ExactOnSingleWordQuery {
            return when (val string = serializer.deserialize(decoder)) {
                KeyAttribute -> Attribute
                KeyNone -> None
                KeyWord -> Word
                else -> Other(string)
            }
        }
    }
}
