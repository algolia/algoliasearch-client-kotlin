package com.algolia.search.model.search

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyAltcorrection
import com.algolia.search.serialize.KeyCompound
import com.algolia.search.serialize.KeyConcat
import com.algolia.search.serialize.KeyExcluded
import com.algolia.search.serialize.KeyOptional
import com.algolia.search.serialize.KeyOriginal
import com.algolia.search.serialize.KeyPlural
import com.algolia.search.serialize.KeySplit
import com.algolia.search.serialize.KeyStopWord
import com.algolia.search.serialize.KeySynonym
import com.algolia.search.serialize.KeyTypo
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer

/**
 * Type for [Alternative.type]
 */
@Serializable(AlternativeType.Companion::class)
sealed class AlternativeType(override val raw: String) : Raw<String> {

    /**
     * Literal word from the query
     */
    object Original : AlternativeType(KeyOriginal)

    /**
     * Original keywords that should not appear in the results because it had a “minus” sign at the beginning and
     * [Query.advancedSyntax] enabled.
     */
    object Excluded : AlternativeType(KeyExcluded)

    /**
     * Original keywords that was declared in [Query.optionalWords].
     */
    object Optional : AlternativeType(KeyOptional)

    /**
     * Original keywords that was discarded by [Query.removeStopWords].
     */
    object StopWord : AlternativeType(KeyStopWord)

    /**
     * Alternative that mostly looks like another original keyword. “Mostly looks like” is defined by the
     * Damerau-Levenshtein distance between the two words, which counts 1 typo as any insertion, deletion, substitution,
     * or transposition of a single character. The field typos contains this number.
     * Because it would make no sense to display every combination of typos possible, the response only contains typos
     * that were found in the documents.
     */
    object Typo : AlternativeType(KeyTypo)

    /**
     * Alternative that tries to split every original keyword into 2 valid sub-keywords.
     * As for typo, only sub-keywords that were found in the documents may return a split.
     * There is always 0 or 1 split per original keyword.
     */
    object Split : AlternativeType(KeySplit)

    /**
     * Alternative that tries to build bigrams out of every adjacent pair of keywords (up to the 5th keyword),
     * and to build an n-gram out of all the words of the query (if it contains at least 3 words).
     */
    object Concat : AlternativeType(KeyConcat)

    /**
     * Any of the following kinds of alternatives: one-way synonym, two-way synonym, n-way synonym.
     */
    object Synonym : AlternativeType(KeySynonym)

    /**
     * Any of the following kinds of alternatives: one-way alternative correction, two-way alternative correction,
     * n-way alternative correction. The difference between a synonym and an alternative correction can also be seen
     * in the field typos, which will always be 0 in the case of a synonym and 1 or 2 in the case of an alternative
     * correction.
     */
    object AlternativeCorrection : AlternativeType(KeyAltcorrection)

    /**
     * Any declension of the original keywords, including singular and plural, case
     * (nominative, accusative, genitive etc.), gender, and others depending on the language.
     * Every possible combination is returned, regardless of what the documents contain.
     */
    object Plural : AlternativeType(KeyPlural)

    /**
     * Word made of several consecutive original query words.
     * It is like a concatenation,but based on a decompounding dictionary.
     */
    object Compound : AlternativeType(KeyCompound)

    data class Other(override val raw: String) : AlternativeType(raw)

    companion object : KSerializer<AlternativeType> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: AlternativeType) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): AlternativeType {
            return when (val string = serializer.deserialize(decoder)) {
                KeyOriginal -> Original
                KeyExcluded -> Excluded
                KeyOptional -> Optional
                KeyStopWord -> StopWord
                KeyTypo -> Typo
                KeySplit -> Split
                KeyConcat -> Concat
                KeySynonym -> Synonym
                KeyAltcorrection -> AlternativeCorrection
                KeyPlural -> Plural
                KeyCompound -> Compound
                else -> Other(string)
            }
        }
    }
}
