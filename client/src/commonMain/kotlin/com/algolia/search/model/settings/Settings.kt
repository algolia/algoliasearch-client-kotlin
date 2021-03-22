package com.algolia.search.model.settings

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.params.SettingsParameters
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
import com.algolia.search.serialize.KeyAdvancedSyntax
import com.algolia.search.serialize.KeyAdvancedSyntaxFeatures
import com.algolia.search.serialize.KeyAllowCompressionOfIntegerArray
import com.algolia.search.serialize.KeyAllowTyposOnNumericTokens
import com.algolia.search.serialize.KeyAlternativesAsExact
import com.algolia.search.serialize.KeyAttributeCriteriaComputedByMinProximity
import com.algolia.search.serialize.KeyAttributeForDistinct
import com.algolia.search.serialize.KeyAttributesForFaceting
import com.algolia.search.serialize.KeyAttributesToHighlight
import com.algolia.search.serialize.KeyAttributesToRetrieve
import com.algolia.search.serialize.KeyAttributesToSnippet
import com.algolia.search.serialize.KeyAttributesToTransliterate
import com.algolia.search.serialize.KeyCamelCaseAttributes
import com.algolia.search.serialize.KeyCustomNormalization
import com.algolia.search.serialize.KeyCustomRanking
import com.algolia.search.serialize.KeyDecompoundQuery
import com.algolia.search.serialize.KeyDecompoundedAttributes
import com.algolia.search.serialize.KeyDisableExactOnAttributes
import com.algolia.search.serialize.KeyDisablePrefixOnAttributes
import com.algolia.search.serialize.KeyDisableTypoToleranceOnAttributes
import com.algolia.search.serialize.KeyDisableTypoToleranceOnWords
import com.algolia.search.serialize.KeyDistinct
import com.algolia.search.serialize.KeyEnablePersonalization
import com.algolia.search.serialize.KeyEnableRules
import com.algolia.search.serialize.KeyExactOnSingleWordQuery
import com.algolia.search.serialize.KeyHighlightPostTag
import com.algolia.search.serialize.KeyHighlightPreTag
import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyIgnorePlurals
import com.algolia.search.serialize.KeyIndexLanguages
import com.algolia.search.serialize.KeyKeepDiacriticsOnCharacters
import com.algolia.search.serialize.KeyMaxFacetHits
import com.algolia.search.serialize.KeyMaxValuesPerFacet
import com.algolia.search.serialize.KeyMinProximity
import com.algolia.search.serialize.KeyMinWordSizeFor1Typo
import com.algolia.search.serialize.KeyMinWordSizeFor2Typos
import com.algolia.search.serialize.KeyNumericAttributesForFiltering
import com.algolia.search.serialize.KeyOptionalWords
import com.algolia.search.serialize.KeyPaginationLimitedTo
import com.algolia.search.serialize.KeyPrimary
import com.algolia.search.serialize.KeyQueryLanguages
import com.algolia.search.serialize.KeyQueryType
import com.algolia.search.serialize.KeyRanking
import com.algolia.search.serialize.KeyRelevancyStrictness
import com.algolia.search.serialize.KeyRemoveStopWords
import com.algolia.search.serialize.KeyRemoveWordsIfNoResults
import com.algolia.search.serialize.KeyReplaceSynonymsInHighlight
import com.algolia.search.serialize.KeyReplicas
import com.algolia.search.serialize.KeyResponseFields
import com.algolia.search.serialize.KeyRestrictHighlightAndSnippetArrays
import com.algolia.search.serialize.KeySearchableAttributes
import com.algolia.search.serialize.KeySeparatorsToIndex
import com.algolia.search.serialize.KeySnippetEllipsisText
import com.algolia.search.serialize.KeySortFacetValuesBy
import com.algolia.search.serialize.KeyTypoTolerance
import com.algolia.search.serialize.KeyUnretrievableAttributes
import com.algolia.search.serialize.KeyUserData
import com.algolia.search.serialize.KeyVersion
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
    @SerialName(KeySearchableAttributes) override var searchableAttributes: List<SearchableAttribute>? = null,

    /**
     * The complete list of attributes that will be used for faceting.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesForFaceting/?language=kotlin]
     */
    @SerialName(KeyAttributesForFaceting) override var attributesForFaceting: List<AttributeForFaceting>? = null,

    /**
     * List of attributes that cannot be retrieved at query time.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/unretrievableAttributes/?language=kotlin]
     */
    @SerialName(KeyUnretrievableAttributes) override var unretrievableAttributes: List<Attribute>? = null,

    /**
     * Gives control over which attributes to retrieve and which not to retrieve.
     * Engine default: [*]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToRetrieve/?language=kotlin]
     */
    @SerialName(KeyAttributesToRetrieve) override var attributesToRetrieve: List<Attribute>? = null,

    /**
     * Controls the way results are sorted.
     * Engine default: [[RankingCriterion.Typo], [RankingCriterion.Geo], [RankingCriterion.Words],
     * [RankingCriterion.Filters], [RankingCriterion.Proximity], [RankingCriterion.Attribute], [RankingCriterion.Exact],
     * [RankingCriterion.Custom]]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/ranking/?language=kotlin]
     */

    @SerialName(KeyRanking) override var ranking: List<RankingCriterion>? = null,
    /**
     * Specifies the [CustomRankingCriterion].
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/customRanking/?language=kotlin]
     */

    @SerialName(KeyCustomRanking) override var customRanking: List<CustomRankingCriterion>? = null,
    /**
     * Creates replicas, exact copies of an index.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/replicas/?language=kotlin]
     */

    @SerialName(KeyReplicas) override var replicas: List<IndexName>? = null,
    /**
     * Engine default: 100
     * Maximum number of facet values to return for each facet during a regular search.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/maxValuesPerFacet/?language=kotlin]
     */
    @SerialName(KeyMaxValuesPerFacet) override var maxValuesPerFacet: Int? = null,

    /**
     * Engine default: [SortFacetsBy.Count]
     * Controls how facet values are sorted.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/sortFacetValuesBy/?language=kotlin]
     */
    @SerialName(KeySortFacetValuesBy) override var sortFacetsBy: SortFacetsBy? = null,

    /**
     * List of attributes to highlight.
     * Engine default: [*]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToHighlight/?language=kotlin]
     */
    @SerialName(KeyAttributesToHighlight) override var attributesToHighlight: List<Attribute>? = null,

    /**
     * List of attributes to snippet.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToSnippet/?language=kotlin]
     */
    @SerialName(KeyAttributesToSnippet) override var attributesToSnippet: List<Snippet>? = null,

    /**
     * The HTML string to insert before the highlighted parts in all highlight and snippet results.
     * Needs to be used along [highlightPostTag].
     * Engine default: "<em>"
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/highlightPreTag/?language=kotlin]
     */
    @SerialName(KeyHighlightPreTag) override var highlightPreTag: String? = null,

    /**
     * The HTML string to insert after the highlighted parts in all highlight and snippet results.
     * Needs to be used along [highlightPreTag].
     * Engine default: "</em>"
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/highlightPostTag/?language=kotlin]
     */
    @SerialName(KeyHighlightPostTag) override var highlightPostTag: String? = null,

    /**
     * String used as an ellipsis indicator when a snippet is truncated.
     * Engine default: "…" (U+2026, HORIZONTAL ELLIPSIS)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/snippetEllipsisText/?language=kotlin]
     */
    @SerialName(KeySnippetEllipsisText) override var snippetEllipsisText: String? = null,

    /**
     * Restrict highlighting and snippeting to items that matched the query.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/restrictHighlightAndSnippetArrays/?language=kotlin]
     */
    @SerialName(KeyRestrictHighlightAndSnippetArrays) override var restrictHighlightAndSnippetArrays: Boolean? = null,

    /**
     * Set the number of hits per page.
     * Engine default: 20
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/hitsPerPage/?language=kotlin]
     */
    @SerialName(KeyHitsPerPage) override var hitsPerPage: Int? = null,

    /**
     * Set the maximum number of hits accessible via pagination.
     * Engine default: 1000
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/paginationlimitedto/?language=kotlin]
     */
    @SerialName(KeyPaginationLimitedTo) override var paginationLimitedTo: Int? = null,

    /**
     * Minimum number of characters a word in the query name must contain to accept matches with 1 typo.
     * Engine default: 4
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minWordSizefor1Typo/?language=kotlin]
     */
    @SerialName(KeyMinWordSizeFor1Typo) override var minWordSizeFor1Typo: Int? = null,

    /**
     * Minimum number of characters a word in the query name must contain to accept matches with 2 typos.
     * Engine default: 8
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minWordSizefor2Typos/?language=kotlin]
     */
    @SerialName(KeyMinWordSizeFor2Typos) override var minWordSizeFor2Typos: Int? = null,

    /**
     * Controls whether typo tolerance is enabled and how it is applied.
     * Engine defaults: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/typoTolerance/?language=kotlin]
     */
    @SerialName(KeyTypoTolerance) override var typoTolerance: TypoTolerance? = null,

    /**
     * Whether to allow typos on numbers (“numeric tokens”) in the query name.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/allowTyposOnNumericTokens/?language=kotlin]
     */
    @SerialName(KeyAllowTyposOnNumericTokens) override var allowTyposOnNumericTokens: Boolean? = null,

    /**
     * List of attributes on which you want to disable typo tolerance.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disabletypotoleranceonattributes/?language=kotlin]
     */
    @SerialName(KeyDisableTypoToleranceOnAttributes) override var disableTypoToleranceOnAttributes: List<Attribute>? = null,

    /**
     * List of words on which you want to disable typo tolerance.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disableTypoToleranceOnWords/?language=kotlin]
     */
    @SerialName(KeyDisableTypoToleranceOnWords) override var disableTypoToleranceOnWords: List<String>? = null,

    /**
     * Control which separators are indexed. Separators are all non-alphanumeric characters except space.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/separatorsToIndex/?language=kotlin]
     */
    @SerialName(KeySeparatorsToIndex) override var separatorsToIndex: String? = null,

    /**
     * Treats singular, plurals, and other forms of declensions as matching terms.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/ignorePlurals/?language=kotlin]
     */
    @SerialName(KeyIgnorePlurals) override var ignorePlurals: IgnorePlurals? = null,

    /**
     * Removes stop (task) words from the query before executing it.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/removeStopWords/?language=kotlin]
     */
    @SerialName(KeyRemoveStopWords) override var removeStopWords: RemoveStopWords? = null,

    /**
     * List of [Attribute] on which to do a decomposition of camel case words.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/camelCaseAttributes/?language=kotlin]
     */
    @SerialName(KeyCamelCaseAttributes) override var camelCaseAttributes: List<Attribute>? = null,

    /**
     * Specify on which [Attribute] in your index Algolia should apply word-splitting (“decompounding”).
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/decompoundedAttributes/?language=kotlin]
     */
    @SerialName(KeyDecompoundedAttributes) @Serializable(KSerializerDecompoundedAttributes::class) override var decompoundedAttributes: List<DecompoundedAttributes>? = null,

    /**
     * Characters that should not be automatically normalized by the search engine.
     * Engine default: "&quot;&quot;"
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/keepDiacriticsOnCharacters/?language=kotlin]
     */
    @SerialName(KeyKeepDiacriticsOnCharacters) override var keepDiacriticsOnCharacters: String? = null,

    /**
     * Sets the languages to be used by language-specific settings and functionalities such as [ignorePlurals],
     * [removeStopWords], and [CJK word-detection][https://www.algolia.com/doc/guides/managing-results/optimize-search-results/handling-natural-languages-nlp/in-depth/normalization/#using-a-language-specific-dictionary-for-cjk-words].
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/queryLanguages/?language=kotlin]
     */
    @SerialName(KeyQueryLanguages) override var queryLanguages: List<Language>? = null,

    /**
     * Whether rules should be globally enabled.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/enableRules/?language=kotlin]
     */
    @SerialName(KeyEnableRules) override var enableRules: Boolean? = null,

    /**
     * Controls if and how query words are interpreted as [prefixes][https://www.algolia.com/doc/guides/textual-relevance/prefix-search/?language=kotlin].
     * Engine default: [QueryType.PrefixLast]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/queryType/?language=kotlin]
     */
    @SerialName(KeyQueryType) override var queryType: QueryType? = null,

    /**
     * Selects a strategy to remove words from the query when it doesn’t match any hits.
     * Engine default: [RemoveWordIfNoResults.None]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/removeWordsIfNoResults/?language=kotlin]
     */
    @SerialName(KeyRemoveWordsIfNoResults) override var removeWordsIfNoResults: RemoveWordIfNoResults? = null,

    /**
     * Enables the advanced query syntax.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/advancedSyntax/?language=kotlin]
     */
    @SerialName(KeyAdvancedSyntax) override var advancedSyntax: Boolean? = null,

    /**
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters//?language=kotlin]
     */
    @SerialName(KeyAdvancedSyntaxFeatures) override var advancedSyntaxFeatures: List<AdvancedSyntaxFeatures>? = null,

    /**
     * A list of words that should be considered as optional when found in the query.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalWords/?language=kotlin]
     */
    @SerialName(KeyOptionalWords) override var optionalWords: List<String>? = null,

    /**
     * List of [Attribute] on which you want to disable prefix matching.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disablePrefixOnAttributes/?language=kotlin]
     */
    @SerialName(KeyDisablePrefixOnAttributes) override var disablePrefixOnAttributes: List<Attribute>? = null,

    /**
     * List of [Attribute] on which you want to disable the exact ranking criterion.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disableExactOnAttributes/?language=kotlin]
     */
    @SerialName(KeyDisableExactOnAttributes) override var disableExactOnAttributes: List<Attribute>? = null,

    /**
     * Controls how the exact ranking criterion is computed when the query contains only one word.
     * Engine default: [ExactOnSingleWordQuery.Attribute]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/exactOnSingleWordQuery/?language=kotlin]
     */
    @SerialName(KeyExactOnSingleWordQuery) override var exactOnSingleWordQuery: ExactOnSingleWordQuery? = null,

    /**
     * List of alternatives that should be considered an exact match by the exact ranking criterion.
     * Engine default: [[AlternativesAsExact.IgnorePlurals], [AlternativesAsExact.SingleWordSynonym]]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/alternativesAsExact/?language=kotlin]
     */
    @SerialName(KeyAlternativesAsExact) override var alternativesAsExact: List<AlternativesAsExact>? = null,

    /**
     * List of [NumericAttributeFilter] that can be used as numerical filters.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/numericAttributesForFiltering/?language=kotlin]
     */
    @SerialName(KeyNumericAttributesForFiltering) override var numericAttributesForFiltering: List<NumericAttributeFilter>? = null,

    /**
     * Enables compression of large integer arrays.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/allowCompressionOfIntegerArray/?language=kotlin]
     */
    @SerialName(KeyAllowCompressionOfIntegerArray) override var allowCompressionOfIntegerArray: Boolean? = null,

    /**
     * Name of the de-duplication [Attribute] to be used with the [distinct] feature.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributeForDistinct/?language=kotlin]
     */
    @SerialName(KeyAttributeForDistinct) override var attributeForDistinct: Attribute? = null,

    /**
     * Enables de-duplication or grouping of results.
     * Engine default: 0
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/distinct/?language=kotlin]
     */
    @SerialName(KeyDistinct) override var distinct: Distinct? = null,

    /**
     * Whether to highlight and snippet the original word that matches the synonym or the synonym itself.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters//?language=kotlin]
     */
    @SerialName(KeyReplaceSynonymsInHighlight) override var replaceSynonymsInHighlight: Boolean? = null,

    /**
     * Precision of the proximity ranking criterion.
     * Engine default: 1
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minProximity/?language=kotlin]
     */
    @SerialName(KeyMinProximity) override var minProximity: Int? = null,

    /**
     * Choose which fields the response will contain. Applies to search and browse queries.
     * Engine default: [ResponseFields.All]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/responseFields/?language=kotlin]
     */
    @SerialName(KeyResponseFields) override var responseFields: List<ResponseFields>? = null,

    /**
     * Maximum number of facet hits to return during a search for facet values.
     * Engine default: 10
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/maxFacetHits/?language=kotlin]
     */
    @SerialName(KeyMaxFacetHits) override var maxFacetHits: Int? = null,
    /**
     * Settings version.
     */
    @SerialName(KeyVersion) override var version: Int? = null,
    /**
     *  Lets you store custom data in your indices.
     */
    @SerialName(KeyUserData) override var userData: JsonObject? = null,
    /**
     * This parameter configures the segmentation of text at indexing time.
     * Accepted value: [Language.Japanese]
     * Input data to index is treated as the given language(s) for segmentation.
     */
    @SerialName(KeyIndexLanguages) override var indexLanguages: List<Language>? = null,
    /**
     * Override the custom normalization handled by the engine.
     */
    @SerialName(KeyCustomNormalization) override var customNormalization: Map<String, Map<String, String>>? = null,
    /**
     * Enable the Personalization feature.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/enablePersonalization/?language=kotlin]
     */
    @SerialName(KeyEnablePersonalization) override var enablePersonalization: Boolean = false,

    /**
     * When `attribute` is ranked above `proximity` in your ranking formula, `proximity` is used to select which
     * searchable attribute is matched in the **attribute ranking stage**.
     * Engine default: false
     * [Documentation](https://www.algolia.com/doc/api-reference/api-parameters/attributeCriteriaComputedByMinProximity/?language=kotlin)
     */
    @SerialName(KeyAttributeCriteriaComputedByMinProximity) override var attributeCriteriaComputedByMinProximity: Boolean? = false,

    /**
     * Relevancy score to apply to search in virtual index [0-100]. Bigger value means less, but more relevant results,
     * lesser value - less relevant results.
     */
    @SerialName(KeyRelevancyStrictness) override var relevancyStrictness: Int? = null,

    /**
     * Enable word segmentation (also called decompounding) at query time for compatible languages. For example, this
     * turns the Dutch query "spaanplaatbehang" into "spaan plaat behang" to retrieve more relevant results.
     * Engine default: true
     * [Documentation](https://www.algolia.com/doc/api-reference/api-parameters/decompoundQuery/?client=kotlin)
     */
    @SerialName(KeyDecompoundQuery) override var decompoundQuery: Boolean? = null,

    /**
     * Specify on which attributes to apply transliteration. Transliteration refers to the ability of finding results in
     * a given alphabet with a query in another alphabet. For example, in Japanese, transliteration enables users to
     * find results indexed in Kanji or Katakana with a query in Hiragana.
     * Engine default: [*]
     * [Documentation](https://www.algolia.com/doc/api-reference/api-parameters/attributesToTransliterate/?language=kotlin)
     */
    @SerialName(KeyAttributesToTransliterate) override var attributesToTransliterate: List<Attribute>? = null
) : SettingsParameters {

    /**
     *  This parameter keeps track of which primary index (if any) a replica is connected to.
     */
    @SerialName(KeyPrimary)
    val primary: IndexName? = null
}
