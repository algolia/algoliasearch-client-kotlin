package com.algolia.search.model.search

import com.algolia.search.model.Raw
import com.algolia.search.model.search.MatchLevel.Full
import com.algolia.search.model.settings.Settings
import com.algolia.search.serialize.KeyFull
import com.algolia.search.serialize.KeyNone
import com.algolia.search.serialize.KeyPartial
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer

/**
 * Indicates how well the value matched the search query.
 * The matching relates to the words in the query string not in the searched text of the records.
 * By “meaningful” we mean: if stop words are removed, they are not taken into account. So if you match everything but
 * stop words (and [Settings.removeStopWords] is enabled), then it’s a [Full] match.
 * This has nothing to do with prefixes, plurals, synonyms, or typos.
 * No matter how “accurately” a word matches, if it matches, it counts as one
 */
@Serializable(MatchLevel.Companion::class)
sealed class MatchLevel(override val raw: String) : Raw<String> {

    object None : MatchLevel(KeyNone)

    object Partial : MatchLevel(KeyPartial)

    object Full : MatchLevel(KeyFull)

    data class Other(override val raw: String) : MatchLevel(raw)

    companion object : KSerializer<MatchLevel> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: MatchLevel) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): MatchLevel {
            return when (val string = serializer.deserialize(decoder)) {
                KeyNone -> None
                KeyPartial -> Partial
                KeyFull -> Full
                else -> Other(string)
            }
        }
    }
}
