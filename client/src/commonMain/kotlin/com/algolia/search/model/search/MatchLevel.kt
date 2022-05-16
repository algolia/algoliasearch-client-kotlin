package com.algolia.search.model.search

import com.algolia.search.model.internal.Raw
import com.algolia.search.model.search.MatchLevel.Full
import com.algolia.search.model.settings.Settings
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Indicates how well the value matched the search query.
 * The matching relates to the words in the query string not in the searched text of the records.
 * By “meaningful” we mean: if stop words are removed, they are not taken into account. So if you match everything but
 * stop words (and [Settings.removeStopWords] is enabled), then it’s a [Full] match.
 * This has nothing to do with prefixes, plurals, synonyms, or typos.
 * No matter how “accurately” a word matches, if it matches, it counts as one
 */
@Serializable(MatchLevel.Companion::class)
public sealed class MatchLevel(override val raw: String) : Raw<String> {

    public object None : MatchLevel(Key.None)

    public object Partial : MatchLevel(Key.Partial)

    public object Full : MatchLevel(Key.Full)

    public data class Other(override val raw: String) : MatchLevel(raw)

    public companion object : KSerializer<MatchLevel> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: MatchLevel) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): MatchLevel {
            return when (val string = serializer.deserialize(decoder)) {
                Key.None -> None
                Key.Partial -> Partial
                Key.Full -> Full
                else -> Other(string)
            }
        }
    }
}
