package com.algolia.search.model.settings

import com.algolia.search.helper.toAttribute
import com.algolia.search.model.internal.Raw
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.regexAsc
import com.algolia.search.serialize.internal.regexDesc
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(RankingCriterion.Companion::class)
public sealed class RankingCriterion(override val raw: String) : Raw<String> {

    /**
     * Algolia can retrieve the records searched by the user even if a typing mistake was made.
     * By default, we’ll match words that have 0, 1 or 2 typos per word. This is called
     * [typo-tolerance][https://www.algolia.com/doc/guides/managing-results/optimize-search-results/typo-tolerance/].
     * The Typo criterion in the ranking formula makes sure that a record without typos will be ranked higher than one
     * with 1 typo, themselves being ranked higher than ones with 2 typos, and so on.
     */
    public object Typo : RankingCriterion(Key.Typo)

    /**
     * If you’re using the [geo-search][[https://www.algolia.com/doc/guides/managing-results/refine-results/geolocation/]
     * feature of our engine, we will rank results by distance, from the closest to the farthest.
     * The precision of this ranking is set by the parameter [Query.aroundPrecision].
     * For example, with [Query.aroundPrecision] = 100 two results up to 100 meters apart will be considered equal.
     */
    public object Geo : RankingCriterion(Key.Geo)

    /**
     * This criterion is only applicable if you are using the [Settings.optionalWords] setting.
     * By default, Algolia discards all results that don’t contain all the words of the query.
     * But with [Settings.optionalWords], where you declare some words as optional, the [Words] criterion will rank
     * them by the number of words typed by the user that matched. Keep in mind that this is not counting the number of
     * times the word appears in the record, but rather counting the number of words typed by the user that matched.
     * For example, if the user typed 2 words, the maximal score for this criterion is 2 - even if a record contains
     * this word 10 times.
     */
    public object Words : RankingCriterion(Key.Words)

    /**
     * If a [Query] has used [Query.filters] or [Query.optionalFilters], the [Filters] criterion will rank records
     * according to a filtering score. All filters default to a score of 1 - so, records that match a single filter
     * will have a score of 1 and will therefore score higher than records that do not match any filter (1 > 0).
     * Equally, records that match more than one filter will score higher than records with less matches - because
     * Algolia counts each match.
     * For purposes of tie-breaking, all records with the same score are ranked the same, and so the ranking formula
     * will drop to the next criterion to break the tie.
     *
     * You can adjust the scoring in 2 significant ways:
     *
     * - With [com.algolia.search.model.filter.Filter.Facet.score], you can use variable scores, scoring some
     *   filters higher than 1. By setting a filter with a score = 2, or score=3, you can favor that filter over others.
     * - With [Query.sumOrFiltersScores], you can accumulate the scores of disjunctive (OR) matches to come up with a total
     *   score, ranking records higher than records with a lesser total score.
     *
     * The [Filters] criterion can be quite powerful in defining relevance, as seen in the promoting results example.
     */
    public object Filters : RankingCriterion(Key.Filters)

    /**
     * For a [Query.query] that contains two or more words, [Proximity] calculates how physically near those words are
     * to each other in the matching record. This criterion will rank higher the objects that have the words closer
     * to each other.
     * For example, George Clooney is a better proximity match than George Timothy Clooney.
     */
    public object Proximity : RankingCriterion(Key.Proximity)

    /**
     * The [Attribute] criterion only considers attributes you have placed in the [Settings.searchableAttributes].
     * Additionally, attributes at the top of the [Settings.searchableAttributes] list rank higher than lower ones.
     * There is also an importance to the order of the matches within the attribute itself.
     * By default, records whose matched words are closer to the beginning of a given attribute will be ranked higher.
     * For example, words in position 2 of an attribute are ranked higher than words in position 5.
     * Otherwise, the position of the word is not taken into account.
     */
    public object Attribute : RankingCriterion(Key.Attribute)

    /**
     * Records with words (not just prefixes) that exactly match the query terms are ranked higher.
     */
    public object Exact : RankingCriterion(Key.Exact)

    /**
     * This criterion takes into account the settings that you have selected using [Settings.customRanking].
     * If you have multiple attributes in your Custom Ranking, the behavior will be the same as for the rest of the
     * Ranking Formula: we’ll only look at a criterion to refine the ranking when there is a tie on all the previous
     * criteria.
     * [Documentation][https://www.algolia.com/doc/guides/managing-results/must-do/custom-ranking/#custom-ranking]
     */
    public object Custom : RankingCriterion(Key.Custom)

    /**
     * Sort an [com.algolia.search.model.Attribute] value by ascending order.
     */
    public data class Asc(val attribute: com.algolia.search.model.Attribute) :
        RankingCriterion("${Key.Asc}($attribute)")

    /**
     * Sort an [com.algolia.search.model.Attribute] value by descending order.
     */
    public data class Desc(val attribute: com.algolia.search.model.Attribute) :
        RankingCriterion("${Key.Desc}($attribute)")

    public data class Other(override val raw: String) : RankingCriterion(raw)

    override fun toString(): String {
        return raw
    }

    public companion object : KSerializer<RankingCriterion> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: RankingCriterion) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): RankingCriterion {
            val string = serializer.deserialize(decoder)

            val findAsc = regexAsc.find(string)
            val findDesc = regexDesc.find(string)

            return when {
                findAsc != null -> Asc(findAsc.groupValues[1].toAttribute())
                findDesc != null -> Desc(findDesc.groupValues[1].toAttribute())
                string == Key.Typo -> Typo
                string == Key.Geo -> Geo
                string == Key.Words -> Words
                string == Key.Filters -> Filters
                string == Key.Proximity -> Proximity
                string == Key.Attribute -> Attribute
                string == Key.Exact -> Exact
                string == Key.Custom -> Custom
                else -> Other(string)
            }
        }
    }
}
