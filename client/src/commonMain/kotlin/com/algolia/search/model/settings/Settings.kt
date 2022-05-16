package com.algolia.search.model.settings

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.params.SettingsParameters
import com.algolia.search.model.rule.RenderingContent
import com.algolia.search.model.search.AlternativesAsExact
import com.algolia.search.model.search.ExactOnSingleWordQuery
import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.Language
import com.algolia.search.model.search.QueryType
import com.algolia.search.model.search.RemoveStopWords
import com.algolia.search.model.search.RemoveWordIfNoResults
import com.algolia.search.model.search.ResponseFields
import com.algolia.search.model.search.Snippet
import com.algolia.search.model.search.SortFacetsBy
import com.algolia.search.model.search.TypoTolerance
import com.algolia.search.serialize.KSerializerDecompoundedAttributes
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
@DSLParameters
public data class Settings(
    /**
     * The complete list of attributes that will be used for searching.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/searchableAttributes/?language=kotlin]
     */
    @SerialName(Key.SearchableAttributes) override var searchableAttributes: List<SearchableAttribute>? = null,

    /**
     * The complete list of attributes that will be used for faceting.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesForFaceting/?language=kotlin]
     */
    @SerialName(Key.AttributesForFaceting) override var attributesForFaceting: List<AttributeForFaceting>? = null,

    /**
     * List of attributes that cannot be retrieved at query time.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/unretrievableAttributes/?language=kotlin]
     */
    @SerialName(Key.UnretrievableAttributes) override var unretrievableAttributes: List<Attribute>? = null,

    /**
     * Gives control over which attributes to retrieve and which not to retrieve.
     * Engine default: [*]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToRetrieve/?language=kotlin]
     */
    @SerialName(Key.AttributesToRetrieve) override var attributesToRetrieve: List<Attribute>? = null,

    /**
     * Controls the way results are sorted.
     * Engine default: [[RankingCriterion.Typo], [RankingCriterion.Geo], [RankingCriterion.Words],
     * [RankingCriterion.Filters], [RankingCriterion.Proximity], [RankingCriterion.Attribute], [RankingCriterion.Exact],
     * [RankingCriterion.Custom]]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/ranking/?language=kotlin]
     */

    @SerialName(Key.Ranking) override var ranking: List<RankingCriterion>? = null,
    /**
     * Specifies the [CustomRankingCriterion].
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/customRanking/?language=kotlin]
     */

    @SerialName(Key.CustomRanking) override var customRanking: List<CustomRankingCriterion>? = null,
    /**
     * Creates replicas, exact copies of an index.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/replicas/?language=kotlin]
     */

    @SerialName(Key.Replicas) override var replicas: List<IndexName>? = null,
    /**
     * Engine default: 100
     * Maximum number of facet values to return for each facet during a regular search.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/maxValuesPerFacet/?language=kotlin]
     */
    @SerialName(Key.MaxValuesPerFacet) override var maxValuesPerFacet: Int? = null,

    /**
     * Engine default: [SortFacetsBy.Count]
     * Controls how facet values are sorted.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/sortFacetValuesBy/?language=kotlin]
     */
    @SerialName(Key.SortFacetValuesBy) override var sortFacetsBy: SortFacetsBy? = null,

    /**
     * List of attributes to highlight.
     * Engine default: [*]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToHighlight/?language=kotlin]
     */
    @SerialName(Key.AttributesToHighlight) override var attributesToHighlight: List<Attribute>? = null,

    /**
     * List of attributes to snippet.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToSnippet/?language=kotlin]
     */
    @SerialName(Key.AttributesToSnippet) override var attributesToSnippet: List<Snippet>? = null,

    /**
     * The HTML string to insert before the highlighted parts in all highlight and snippet results.
     * Needs to be used along [highlightPostTag].
     * Engine default: "<em>"
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/highlightPreTag/?language=kotlin]
     */
    @SerialName(Key.HighlightPreTag) override var highlightPreTag: String? = null,

    /**
     * The HTML string to insert after the highlighted parts in all highlight and snippet results.
     * Needs to be used along [highlightPreTag].
     * Engine default: "</em>"
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/highlightPostTag/?language=kotlin]
     */
    @SerialName(Key.HighlightPostTag) override var highlightPostTag: String? = null,

    /**
     * String used as an ellipsis indicator when a snippet is truncated.
     * Engine default: "…" (U+2026, HORIZONTAL ELLIPSIS)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/snippetEllipsisText/?language=kotlin]
     */
    @SerialName(Key.SnippetEllipsisText) override var snippetEllipsisText: String? = null,

    /**
     * Restrict highlighting and snippeting to items that matched the query.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/restrictHighlightAndSnippetArrays/?language=kotlin]
     */
    @SerialName(Key.RestrictHighlightAndSnippetArrays) override var restrictHighlightAndSnippetArrays: Boolean? = null,

    /**
     * Set the number of hits per page.
     * Engine default: 20
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/hitsPerPage/?language=kotlin]
     */
    @SerialName(Key.HitsPerPage) override var hitsPerPage: Int? = null,

    /**
     * Set the maximum number of hits accessible via pagination.
     * Engine default: 1000
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/paginationlimitedto/?language=kotlin]
     */
    @SerialName(Key.PaginationLimitedTo) override var paginationLimitedTo: Int? = null,

    /**
     * Minimum number of characters a word in the query name must contain to accept matches with 1 typo.
     * Engine default: 4
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minWordSizefor1Typo/?language=kotlin]
     */
    @SerialName(Key.MinWordSizeFor1Typo) override var minWordSizeFor1Typo: Int? = null,

    /**
     * Minimum number of characters a word in the query name must contain to accept matches with 2 typos.
     * Engine default: 8
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minWordSizefor2Typos/?language=kotlin]
     */
    @SerialName(Key.MinWordSizeFor2Typos) override var minWordSizeFor2Typos: Int? = null,

    /**
     * Controls whether typo tolerance is enabled and how it is applied.
     * Engine defaults: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/typoTolerance/?language=kotlin]
     */
    @SerialName(Key.TypoTolerance) override var typoTolerance: TypoTolerance? = null,

    /**
     * Whether to allow typos on numbers (“numeric tokens”) in the query name.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/allowTyposOnNumericTokens/?language=kotlin]
     */
    @SerialName(Key.AllowTyposOnNumericTokens) override var allowTyposOnNumericTokens: Boolean? = null,

    /**
     * List of attributes on which you want to disable typo tolerance.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disabletypotoleranceonattributes/?language=kotlin]
     */
    @SerialName(Key.DisableTypoToleranceOnAttributes) override var disableTypoToleranceOnAttributes: List<Attribute>? = null,

    /**
     * List of words on which you want to disable typo tolerance.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disableTypoToleranceOnWords/?language=kotlin]
     */
    @SerialName(Key.DisableTypoToleranceOnWords) override var disableTypoToleranceOnWords: List<String>? = null,

    /**
     * Control which separators are indexed. Separators are all non-alphanumeric characters except space.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/separatorsToIndex/?language=kotlin]
     */
    @SerialName(Key.SeparatorsToIndex) override var separatorsToIndex: String? = null,

    /**
     * Treats singular, plurals, and other forms of declensions as matching terms.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/ignorePlurals/?language=kotlin]
     */
    @SerialName(Key.IgnorePlurals) override var ignorePlurals: IgnorePlurals? = null,

    /**
     * Removes stop (task) words from the query before executing it.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/removeStopWords/?language=kotlin]
     */
    @SerialName(Key.RemoveStopWords) override var removeStopWords: RemoveStopWords? = null,

    /**
     * List of [Attribute] on which to do a decomposition of camel case words.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/camelCaseAttributes/?language=kotlin]
     */
    @SerialName(Key.CamelCaseAttributes) override var camelCaseAttributes: List<Attribute>? = null,

    /**
     * Specify on which [Attribute] in your index Algolia should apply word-splitting (“decompounding”).
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/decompoundedAttributes/?language=kotlin]
     */
    @SerialName(Key.DecompoundedAttributes) @Serializable(KSerializerDecompoundedAttributes::class) override var decompoundedAttributes: List<DecompoundedAttributes>? = null,

    /**
     * Characters that should not be automatically normalized by the search engine.
     * Engine default: "&quot;&quot;"
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/keepDiacriticsOnCharacters/?language=kotlin]
     */
    @SerialName(Key.KeepDiacriticsOnCharacters) override var keepDiacriticsOnCharacters: String? = null,

    /**
     * Sets the languages to be used by language-specific settings and functionalities such as [ignorePlurals],
     * [removeStopWords], and [CJK word-detection][https://www.algolia.com/doc/guides/managing-results/optimize-search-results/handling-natural-languages-nlp/in-depth/normalization/#using-a-language-specific-dictionary-for-cjk-words].
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/queryLanguages/?language=kotlin]
     */
    @SerialName(Key.QueryLanguages) override var queryLanguages: List<Language>? = null,

    /**
     * Whether rules should be globally enabled.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/enableRules/?language=kotlin]
     */
    @SerialName(Key.EnableRules) override var enableRules: Boolean? = null,

    /**
     * Controls if and how query words are interpreted as [prefixes][https://www.algolia.com/doc/guides/textual-relevance/prefix-search/?language=kotlin].
     * Engine default: [QueryType.PrefixLast]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/queryType/?language=kotlin]
     */
    @SerialName(Key.QueryType) override var queryType: QueryType? = null,

    /**
     * Selects a strategy to remove words from the query when it doesn’t match any hits.
     * Engine default: [RemoveWordIfNoResults.None]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/removeWordsIfNoResults/?language=kotlin]
     */
    @SerialName(Key.RemoveWordsIfNoResults) override var removeWordsIfNoResults: RemoveWordIfNoResults? = null,

    /**
     * Enables the advanced query syntax.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/advancedSyntax/?language=kotlin]
     */
    @SerialName(Key.AdvancedSyntax) override var advancedSyntax: Boolean? = null,

    /**
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters//?language=kotlin]
     */
    @SerialName(Key.AdvancedSyntaxFeatures) override var advancedSyntaxFeatures: List<AdvancedSyntaxFeatures>? = null,

    /**
     * A list of words that should be considered as optional when found in the query.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalWords/?language=kotlin]
     */
    @SerialName(Key.OptionalWords) override var optionalWords: List<String>? = null,

    /**
     * List of [Attribute] on which you want to disable prefix matching.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disablePrefixOnAttributes/?language=kotlin]
     */
    @SerialName(Key.DisablePrefixOnAttributes) override var disablePrefixOnAttributes: List<Attribute>? = null,

    /**
     * List of [Attribute] on which you want to disable the exact ranking criterion.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disableExactOnAttributes/?language=kotlin]
     */
    @SerialName(Key.DisableExactOnAttributes) override var disableExactOnAttributes: List<Attribute>? = null,

    /**
     * Controls how the exact ranking criterion is computed when the query contains only one word.
     * Engine default: [ExactOnSingleWordQuery.Attribute]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/exactOnSingleWordQuery/?language=kotlin]
     */
    @SerialName(Key.ExactOnSingleWordQuery) override var exactOnSingleWordQuery: ExactOnSingleWordQuery? = null,

    /**
     * List of alternatives that should be considered an exact match by the exact ranking criterion.
     * Engine default: [[AlternativesAsExact.IgnorePlurals], [AlternativesAsExact.SingleWordSynonym]]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/alternativesAsExact/?language=kotlin]
     */
    @SerialName(Key.AlternativesAsExact) override var alternativesAsExact: List<AlternativesAsExact>? = null,

    /**
     * List of [NumericAttributeFilter] that can be used as numerical filters.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/numericAttributesForFiltering/?language=kotlin]
     */
    @SerialName(Key.NumericAttributesForFiltering) override var numericAttributesForFiltering: List<NumericAttributeFilter>? = null,

    /**
     * Enables compression of large integer arrays.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/allowCompressionOfIntegerArray/?language=kotlin]
     */
    @SerialName(Key.AllowCompressionOfIntegerArray) override var allowCompressionOfIntegerArray: Boolean? = null,

    /**
     * Name of the de-duplication [Attribute] to be used with the [distinct] feature.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributeForDistinct/?language=kotlin]
     */
    @SerialName(Key.AttributeForDistinct) override var attributeForDistinct: Attribute? = null,

    /**
     * Enables de-duplication or grouping of results.
     * Engine default: 0
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/distinct/?language=kotlin]
     */
    @SerialName(Key.Distinct) override var distinct: Distinct? = null,

    /**
     * Whether to highlight and snippet the original word that matches the synonym or the synonym itself.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters//?language=kotlin]
     */
    @SerialName(Key.ReplaceSynonymsInHighlight) override var replaceSynonymsInHighlight: Boolean? = null,

    /**
     * Precision of the proximity ranking criterion.
     * Engine default: 1
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minProximity/?language=kotlin]
     */
    @SerialName(Key.MinProximity) override var minProximity: Int? = null,

    /**
     * Choose which fields the response will contain. Applies to search and browse queries.
     * Engine default: [ResponseFields.All]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/responseFields/?language=kotlin]
     */
    @SerialName(Key.ResponseFields) override var responseFields: List<ResponseFields>? = null,

    /**
     * Maximum number of facet hits to return during a search for facet values.
     * Engine default: 10
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/maxFacetHits/?language=kotlin]
     */
    @SerialName(Key.MaxFacetHits) override var maxFacetHits: Int? = null,
    /**
     * Settings version.
     */
    @SerialName(Key.Version) override var version: Int? = null,
    /**
     *  Lets you store custom data in your indices.
     */
    @SerialName(Key.UserData) override var userData: JsonObject? = null,
    /**
     * This parameter configures the segmentation of text at indexing time.
     * Accepted value: [Language.Japanese]
     * Input data to index is treated as the given language(s) for segmentation.
     */
    @SerialName(Key.IndexLanguages) override var indexLanguages: List<Language>? = null,
    /**
     * Override the custom normalization handled by the engine.
     */
    @SerialName(Key.CustomNormalization) override var customNormalization: Map<String, Map<String, String>>? = null,
    /**
     * Enable the Personalization feature.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/enablePersonalization/?language=kotlin]
     */
    @SerialName(Key.EnablePersonalization) override var enablePersonalization: Boolean = false,

    /**
     * When `attribute` is ranked above `proximity` in your ranking formula, `proximity` is used to select which
     * searchable attribute is matched in the **attribute ranking stage**.
     * Engine default: false
     * [Documentation](https://www.algolia.com/doc/api-reference/api-parameters/attributeCriteriaComputedByMinProximity/?language=kotlin)
     */
    @SerialName(Key.AttributeCriteriaComputedByMinProximity) override var attributeCriteriaComputedByMinProximity: Boolean? = false,

    /**
     * Relevancy score to apply to search in virtual index [0-100]. Bigger value means less, but more relevant results,
     * lesser value - less relevant results.
     */
    @SerialName(Key.RelevancyStrictness) override var relevancyStrictness: Int? = null,

    /**
     * Enable word segmentation (also called decompounding) at query time for compatible languages. For example, this
     * turns the Dutch query "spaanplaatbehang" into "spaan plaat behang" to retrieve more relevant results.
     * Engine default: true
     * [Documentation](https://www.algolia.com/doc/api-reference/api-parameters/decompoundQuery/?client=kotlin)
     */
    @SerialName(Key.DecompoundQuery) override var decompoundQuery: Boolean? = null,

    /**
     * Specify on which attributes to apply transliteration. Transliteration refers to the ability of finding results in
     * a given alphabet with a query in another alphabet. For example, in Japanese, transliteration enables users to
     * find results indexed in Kanji or Katakana with a query in Hiragana.
     * Engine default: [*]
     * [Documentation](https://www.algolia.com/doc/api-reference/api-parameters/attributesToTransliterate/?language=kotlin)
     */
    @SerialName(Key.AttributesToTransliterate) override var attributesToTransliterate: List<Attribute>? = null,

    /**
     * Content defining how the search interface should be rendered.
     * This is set via the settings for a default value and can be overridden via rules
     */
    @SerialName(Key.RenderingContent) override var renderingContent: RenderingContent? = null,
) : SettingsParameters {

    /**
     *  This parameter keeps track of which primary index (if any) a replica is connected to.
     */
    @SerialName(Key.Primary)
    val primary: IndexName? = null
}
