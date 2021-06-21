package com.algolia.search.model.multipleindex

import com.algolia.search.endpoint.EndpointMultipleIndex
import com.algolia.search.model.internal.Raw
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.KeyNone
import com.algolia.search.serialize.KeyStopIfEnoughMatches
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * The strategy used by [EndpointMultipleIndex.multipleQueries].
 */
@Serializable(MultipleQueriesStrategy.Companion::class)
public sealed class MultipleQueriesStrategy(override val raw: String) : Raw<String> {

    /**
     * Execute the sequence of queries until the end. This is recommended when each query is of equal importance,
     * meaning all records of all queries need to be returned.
     */
    public object None : MultipleQueriesStrategy(KeyNone)

    /**
     * Execute queries one by one, but stop as soon as the cumulated number of hits is at least [Query.hitsPerPage].
     * This is recommended when each query is an alternative, and where, if the first returns enough records,
     * there is no need to perform the remaining queries.
     */
    public object StopIfEnoughMatches : MultipleQueriesStrategy(KeyStopIfEnoughMatches)

    public data class Other(override val raw: String) : MultipleQueriesStrategy(raw)

    override fun toString(): String {
        return raw
    }

    public companion object : KSerializer<MultipleQueriesStrategy> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: MultipleQueriesStrategy) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): MultipleQueriesStrategy {
            return when (val string = serializer.deserialize(decoder)) {
                KeyNone -> None
                KeyStopIfEnoughMatches -> StopIfEnoughMatches
                else -> Other(string)
            }
        }
    }
}
