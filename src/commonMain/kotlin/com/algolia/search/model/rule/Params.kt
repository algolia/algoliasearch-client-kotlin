package com.algolia.search.model.rule

import com.algolia.search.model.Attribute
import com.algolia.search.model.search.*
import com.algolia.search.query.FilterBuilder
import com.algolia.search.query.OptionalFilterBuilder
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
data class Params(
    @Optional @SerialName(KeyAutomaticFacetFilters) var automaticFacetFilters: List<AutomaticFacetFilters>? = null,
    @Optional @SerialName(KeyAutomaticOptionalFacetFilters) var automaticOptionalFacetFilters: List<AutomaticFacetFilters>? = null,
    @Optional @SerialName(KeyQuery) var query: QueryOrEdits? = null,
    /**
     * Gives control over which attributes to retrieve and which not to retrieve.
     * Engine default: [*]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToRetrieve/]
     */
    @Optional @SerialName(KeyAttributesToRetrieve) var attributesToRetrieve: List<Attribute>? = null,

    /**
     * Restricts a given query to look in only a subset of your searchable attributes.
     * Engine default: all attributes in searchableAttributes.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/restrictSearchableAttributes/]
     */
    @Optional @SerialName(KeyRestrictSearchableAttributes) var restrictSearchableAttributes: List<Attribute>? = null,

    /**
     * Filter the query with numeric, facet and/or tag filters.
     * Engine default: "" (no filters)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
     */
    @Optional @SerialName(KeyFilters) var filters: String? = null,

    /**
     * Filter hits by facet value.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facetFilters/]
     */
    @Optional @SerialName(KeyFacetFilters) var facetFilters: List<List<String>>? = null,

    /**
     * Create filters for ranking purposes, where records that match the filter are ranked highest.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalFilters/]
     */
    @Optional @SerialName(KeyOptionalFilters) var optionalFilters: List<List<String>>? = null,

    /**
     * Filter on numeric attributes.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/numericFilters/]
     */
    @Optional @SerialName(KeyNumericFilters) var numericFilters: List<List<String>>? = null,

    /**
     * Filter hits by tags.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/tagFilters/]
     */
    @Optional @SerialName(KeyTagFilters) var tagFilters: List<List<String>>? = null,

    /**
     * Determines how to calculate the total score for filtering.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/sumOrFiltersScores/]
     */
    @Optional @SerialName(KeySumOrFiltersScores) var sumOrFiltersScores: Boolean? = null,

    /**
     * Facets to retrieve.
     * Engine default: [] (no facets retrieved)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facets/]
     */
    @Optional @SerialName(KeyFacets) var facets: List<Attribute>? = null,

    /**
     * Maximum number of facet values to return for each facet during a regular search.
     * Engine default: 100
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/maxValuesPerFacet/]
     */
    @Optional @SerialName(KeyMaxValuesPerFacet) var maxValuesPerFacet: Int? = null,

    /**
     * Force faceting to be applied after de-duplication (via the Distinct setting).
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facetingAfterDistinct/]
     */
    @Optional @SerialName(KeyFacetingAfterDistinct) var facetingAfterDistinct: Boolean? = null,

    /**
     * Controls how facet values are sorted.
     * Engine default: [SortFacetValuesBy.Count]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/sortFacetValuesBy/]
     */
    @Optional @SerialName(KeySortFacetValuesBy) var sortFacetValuesBy: SortFacetValuesBy? = null,

    /**
     * List of attributes to highlight.
     * Engine default: null (all searchable attributes)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToHighlight/]
     */
    @Optional @SerialName(KeyAttributesToHighlight) var attributesToHighlight: List<Attribute>? = null,

    /**
     * List of attributes to snippet, with an optional maximum number of words to snippet.
     * Engine default: [] (no attribute is snippeted)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToSnippet/]
     */
    @Optional @SerialName(KeyAttributesToSnippet) var attributesToSnippet: List<Snippet>? = null,

    /**
     * The HTML name to insert before the highlighted parts in all highlight and snippet results.
     * Engine default: <em>
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/highlightPreTag/]
     */
    @Optional @SerialName(KeyHighlightPreTag) var highlightPreTag: String? = null,

    /**
     * The HTML name to insert after the highlighted parts in all highlight and snippet results.
     * Engine default: </em>
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/highlightPostTag/]
     */
    @Optional @SerialName(KeyHighlightPostTag) var highlightPostTag: String? = null,

    /**
     * String used as an ellipsis indicator when a snippet is truncated.
     * Engine default: "…" (U+2026, HORIZONTAL ELLIPSIS)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/snippetEllipsisText/]
     */
    @Optional @SerialName(KeySnippetEllipsisText) var snippetEllipsisText: String? = null,

    /**
     * Restrict highlighting and snippeting to items that matched the query.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/restrictHighlightAndSnippetArrays/]
     */
    @Optional @SerialName(KeyRestrictHighlightAndSnippetArrays) var restrictHighlightAndSnippetArrays: Boolean? = null,

    /**
     * Specify the page to retrieve.
     * Engine default: 0
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/page/]
     */
    @Optional @SerialName(KeyPage) var page: Int? = null,

    /**
     * Set the number of hits per page.
     * Engine default: 20
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/hitsPerPage/]
     */
    @Optional @SerialName(KeyHitsPerPage) var hitsPerPage: Int? = null,

    /**
     * Specify the offset of the first hit to return.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/offset/]
     */
    @Optional @SerialName(KeyOffset) var offset: Int? = null,

    /**
     * Set the number of hits to retrieve (used only with offset).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/length/]
     */
    @Optional @SerialName(KeyLength) var length: Int? = null,

    /**
     * Minimum number of characters a word in the query name must contain to accept matches with 1 typo.
     * Engine default: 4
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minWordSizefor1Typo/]
     */
    @Optional @SerialName(KeyMinWordSizefor1Typo) var minWordSizefor1Typo: Int? = null,

    /**
     * Minimum number of characters a word in the query name must contain to accept matches with 2 typos.
     * Engine default: 8
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minWordSizefor2Typos/]
     */
    @Optional @SerialName(KeyMinWordSizefor2Typos) var minWordSizefor2Typos: Int? = null,

    /**
     * Controls whether typo tolerance is enabled and how it is applied.
     * Engine defaults: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/typoTolerance/]
     */
    @Optional @SerialName(KeyTypoTolerance) var typoTolerance: TypoTolerance? = null,

    /**
     * Whether to allow typos on numbers (“numeric tokens”) in the query name.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/allowTyposOnNumericTokens/]
     */
    @Optional @SerialName(KeyAllowTyposOnNumericTokens) var allowTyposOnNumericTokens: Boolean? = null,

    /**
     * List of attributes on which you want to disable typo tolerance.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disableTypoToleranceOnAttributes/]
     */
    @Optional @SerialName(KeyDisableTypoToleranceOnAttributes) var disableTypoToleranceOnAttributes: List<Attribute>? = null,

    /**
     * Search for entries around a central geolocation, enabling a geo search within a circular area.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundLatLng/]
     */
    @Optional @SerialName(KeyAroundLatLng) var aroundLatLng: String? = null,

    /**
     * Whether to search entries around a given location automatically computed from the requester’s IP address.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundLatLngViaIP/]
     */
    @Optional @SerialName(KeyAroundLatLngViaIP) var aroundLatLngViaIP: Boolean? = null,

    /**
     * Define the maximum radius for a geo search (in meters).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundRadius/]
     */
    @Optional @SerialName(KeyAroundRadius) var aroundRadius: AroundRadius? = null,

    /**
     * Precision of geo search (in meters), to add grouping by geo location to the ranking formula.
     * Engine default: 1
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundPrecision/]
     */
    @Optional @SerialName(KeyAroundPrecision) var aroundPrecision: Int? = null,

    /**
     * Minimum radius (in meters) used for a geo search when [aroundRadius] is not set.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minimumAroundRadius/]
     */
    @Optional @SerialName(KeyMinimumAroundRadius) var minimumAroundRadius: Int? = null,

    /**
     * Search inside a rectangular area (in geo coordinates).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/insideBoundingBox/]
     */
    @Optional @SerialName(KeyInsideBoundingBox) var insideBoundingBox: List<BoundingBox>? = null,

    /**
     * Search inside a polygon (in geo coordinates).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/insidePolygon/]
     */
    @Optional @SerialName(KeyInsidePolygon) var insidePolygon: List<Polygon>? = null,

    /**
     * Treats singular, plurals, and other forms of declensions as matching terms.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/ignorePlurals/]
     */
    @Optional @SerialName(KeyIgnorePlurals) var ignorePlurals: BooleanOrQueryLanguages? = null,

    /**
     * Removes stop (task) words from the query before executing it.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/removeStopWords/]
     */
    @Optional @SerialName(KeyRemoveStopWords) var removeStopWords: BooleanOrQueryLanguages? = null,

    /**
     * Sets the queryLanguage to be used by language-specific settings and functionalities such as
     * [ignorePlurals], [removeStopWords], and
     * [CJK word-detection][https://www.algolia.com/doc/guides/textual-relevance/queryLanguage/#using-a-language-specific-dictionary-for-cjk-words].
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/queryLanguages/]
     */
    @Optional @SerialName(KeyQueryLanguages) var queryLanguages: List<QueryLanguage>? = null,

    /**
     * Whether rules should be globally enabled.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/enableRules/]
     */
    @Optional @SerialName(KeyEnableRules) var enableRules: Boolean? = null,

    /**
     * Enables contextual rules.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/ruleContexts/]
     */
    @Optional @SerialName(KeyRuleContexts) var ruleContexts: List<String>? = null,

    /**
     * Enable the Personalization feature.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/enablePersonalization/]
     */
    @Optional @SerialName(KeyEnablePersonalization) var enablePersonalization: Boolean? = null,

    /**
     * Controls if and how query words are interpreted as [prefixes][https://www.algolia.com/doc/guides/textual-relevance/prefix-search/].
     * Engine default: [QueryType.PrefixLast]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/queryType/]
     */
    @Optional @SerialName(KeyQueryType) var queryType: QueryType? = null,

    /**
     * Selects a strategy to remove words from the query when it doesn’t match any hits.
     * Engine default: [RemoveWordIfNoResults.None]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/removeWordsIfNoResults/]
     */
    @Optional @SerialName(KeyRemoveWordsIfNoResults) var removeWordsIfNoResults: RemoveWordIfNoResults? = null,

    /**
     * Enables the advanced query syntax.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/advancedSyntax/]
     */
    @Optional @SerialName(KeyAdvancedSyntax) var advancedSyntax: Boolean? = null,

    /**
     * A list of words that should be considered as optional when found in the query.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalWords/]
     */
    @Optional @SerialName(KeyOptionalWords) var optionalWords: List<String>? = null,

    /**
     * List of attributes on which you want to disable the exact ranking criterion.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disableExactOnAttributes/]
     */
    @Optional @SerialName(KeyDisableExactOnAttributes) var disableExactOnAttributes: List<Attribute>? = null,

    /**
     * Controls how the exact ranking criterion is computed when the query contains only one word.
     * Engine default: [ExactOnSingleWordQuery.Attribute]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/exactOnSingleWordQuery/]
     */
    @Optional @SerialName(KeyExactOnSingleWordQuery) var exactOnSingleWordQuery: ExactOnSingleWordQuery? = null,

    /**
     * List of alternatives that should be considered an exact match by the exact ranking criterion.
     * Engine default: [[AlternativesAsExact.IgnorePlurals], [AlternativesAsExact.SingleWordSynonym]]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/alternativesAsExact/]
     */
    @Optional @SerialName(KeyAlternativesAsExact) var alternativesAsExact: List<AlternativesAsExact>? = null,

    /**
     * Enables de-duplication or grouping of results.
     * Engine default: 0
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/distinct/]
     */
    @Optional @SerialName(KeyDistinct) var distinct: Int? = null,

    /**
     * Retrieve detailed ranking information.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/getRankingInfo/]
     */
    @Optional @SerialName(KeyGetRankingInfo) var getRankingInfo: Boolean? = null,

    /**
     * Enable the Click Analytics feature.
     * Engine default: false.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/clickAnalytics/]
     */
    @Optional @SerialName(KeyClickAnalytics) var clickAnalytics: Boolean? = null,

    /**
     * Whether the current query will be taken into account in the Analytics.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/analytics/]
     */
    @Optional @SerialName(KeyAnalytics) var analytics: Boolean? = null,

    /**
     * List of tags to apply to the query in the analytics.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/analyticsTags/]
     */
    @Optional @SerialName(KeyAnalyticsTags) var analyticsTags: List<String>? = null,

    /**
     * Whether to take into account an index’s synonyms for a particular search.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/synonyms/]
     */
    @Optional @SerialName(KeySynonyms) var synonyms: Boolean? = null,

    /**
     * Whether to highlight and snippet the original word that matches the synonym or the synonym itself.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/replaceSynonymsInHighlight/]
     */
    @Optional @SerialName(KeyReplaceSynonymsInHighlight) var replaceSynonymsInHighlight: Boolean? = null,

    /**
     * Precision of the proximity ranking criterion.
     * Engine default: 1
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minProximity/]
     */
    @Optional @SerialName(KeyMinProximity) var minProximity: Int? = null,

    /**
     * Choose which fields the response will contain. Applies to search and browse queries.
     * Engine default: [ResponseFields.All]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/responseFields/]
     */
    @Optional @SerialName(KeyResponseFields) var responseFields: List<ResponseFields>? = null,

    /**
     * Maximum number of facet hits to return during a search for facet values.
     * Engine default: 10
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/maxFacetHits/]
     */
    @Optional @SerialName(KeyMaxFacetHits) var maxFacetHits: Int? = null,

    /**
     * Whether to include or exclude a query from the processing-time percentile computation.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/percentileComputation/]
     */
    @Optional @SerialName(KeyPercentileComputation) var percentileComputation: Boolean? = null
) {

    /**
     * When this attribute is set to true, the list of words in [optionalWords] will be replaced by all the words
     * found in the current [query].
     * This will trigger the engine to return records containing any word matching the query (OR operation).
     * Otherwise, the engine return records containing all the word matching the query (AND operation).
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalWords/#doing-an-or-between-all-words-of-a-query]
     */
    @Transient
    var isEveryWordInQueryOptional: Boolean = false

    /**
     * You can modify this instance of [FilterBuilder] or assign a new one.
     * If [filters] is null, the encoder of [FilterBuilder.build] will be passed to the request body of the next request.
     */
    @Transient
    var filterBuilder: FilterBuilder = FilterBuilder()

    /**
     * You can modify this instance of [OptionalFilterBuilder] or assign a new one.
     * If [optionalFilters] is null, the encoder of [OptionalFilterBuilder.build] will be passed to
     * the request body of the next request.
     */
    @Transient
    var optionalFilterBuilder: OptionalFilterBuilder = OptionalFilterBuilder()
}