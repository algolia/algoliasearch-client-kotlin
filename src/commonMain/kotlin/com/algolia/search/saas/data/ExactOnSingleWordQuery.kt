package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KeyAttribute
import com.algolia.search.saas.serialize.KeyNone
import com.algolia.search.saas.serialize.KeyWord
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(ExactOnSingleWordQuery.Companion::class)
sealed class ExactOnSingleWordQuery(override val raw: String) : Raw<String> {

    /**
     * The exact ranking criterion is set to 1 if the query matches exactly an entire attribute value (default behavior).
     * For example, if you search for the TV show “V”, you want it to match the query “V” before all popular
     * TV shows starting with the letter V.
     */
    object Attribute : ExactOnSingleWordQuery(KeyAttribute)

    /**
     * The exact ranking criterion is ignored on single word queries.
     */
    object None : ExactOnSingleWordQuery(KeyNone)

    /**
     * The exact ranking criterion is set to 1 if the query word is found in the record.
     * The query word must be at least 3 characters long and must not be a stop word in any supported language.
     * For example, if you search for the TV show “Road”, and in your dataset you have 2 records,
     * “Road” and “Road Trip”, both will be considered to match exactly.
     */
    object Word : ExactOnSingleWordQuery(KeyWord)

    data class Other(override val raw: String) : ExactOnSingleWordQuery(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : KSerializer<ExactOnSingleWordQuery> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: ExactOnSingleWordQuery) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): ExactOnSingleWordQuery {
            val string = serializer.deserialize(decoder)

            return when (string) {
                KeyAttribute -> Attribute
                KeyNone -> None
                KeyWord -> Word
                else -> Other(string)
            }
        }
    }
}