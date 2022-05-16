package com.algolia.search.model.search

import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Type for [Alternative.type]
 */
@Serializable(AlternativeType.Companion::class)
public sealed class AlternativeType(override val raw: String) : Raw<String> {

    /**
     * Literal word from the query
     */
    public object Original : AlternativeType(Key.Original)

    /**
     * Original keywords that should not appear in the results because it had a “minus” sign at the beginning and
     * [Query.advancedSyntax] enabled.
     */
    public object Excluded : AlternativeType(Key.Excluded)

    /**
     * Original keywords that was declared in [Query.optionalWords].
     */
    public object Optional : AlternativeType(Key.Optional)

    /**
     * Original keywords that was discarded by [Query.removeStopWords].
     */
    public object StopWord : AlternativeType(Key.StopWord)

    /**
     * Alternative that mostly looks like another original keyword. “Mostly looks like” is defined by the
     * Damerau-Levenshtein distance between the two words, which counts 1 typo as any insertion, deletion, substitution,
     * or transposition of a single character. The field typos contains this number.
     * Because it would make no sense to display every combination of typos possible, the response only contains typos
     * that were found in the documents.
     */
    public object Typo : AlternativeType(Key.Typo)

    /**
     * Alternative that tries to split every original keyword into 2 valid sub-keywords.
     * As for typo, only sub-keywords that were found in the documents may return a split.
     * There is always 0 or 1 split per original keyword.
     */
    public object Split : AlternativeType(Key.Split)

    /**
     * Alternative that tries to build bigrams out of every adjacent pair of keywords (up to the 5th keyword),
     * and to build an n-gram out of all the words of the query (if it contains at least 3 words).
     */
    public object Concat : AlternativeType(Key.Concat)

    /**
     * Any of the following kinds of alternatives: one-way synonym, two-way synonym, n-way synonym.
     */
    public object Synonym : AlternativeType(Key.Synonym)

    /**
     * Any of the following kinds of alternatives: one-way alternative correction, two-way alternative correction,
     * n-way alternative correction. The difference between a synonym and an alternative correction can also be seen
     * in the field typos, which will always be 0 in the case of a synonym and 1 or 2 in the case of an alternative
     * correction.
     */
    public object AlternativeCorrection : AlternativeType(Key.Altcorrection)

    /**
     * Any declension of the original keywords, including singular and plural, case
     * (nominative, accusative, genitive etc.), gender, and others depending on the language.
     * Every possible combination is returned, regardless of what the documents contain.
     */
    public object Plural : AlternativeType(Key.Plural)

    /**
     * Word made of several consecutive original query words.
     * It is like a concatenation,but based on a decompounding dictionary.
     */
    public object Compound : AlternativeType(Key.Compound)

    public data class Other(override val raw: String) : AlternativeType(raw)

    public companion object : KSerializer<AlternativeType> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: AlternativeType) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): AlternativeType {
            return when (val string = serializer.deserialize(decoder)) {
                Key.Original -> Original
                Key.Excluded -> Excluded
                Key.Optional -> Optional
                Key.StopWord -> StopWord
                Key.Typo -> Typo
                Key.Split -> Split
                Key.Concat -> Concat
                Key.Synonym -> Synonym
                Key.Altcorrection -> AlternativeCorrection
                Key.Plural -> Plural
                Key.Compound -> Compound
                else -> Other(string)
            }
        }
    }
}
