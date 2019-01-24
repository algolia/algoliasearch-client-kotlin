package com.algolia.search.saas.data

import com.algolia.search.saas.query.FilterBuilder
import com.algolia.search.saas.query.OptionalFilterBuilder
import com.algolia.search.saas.query.QueryHelper
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@QueryHelper
@Serializable
data class Query(
    /**
     * The text to search in the index.
     * Engine default: "" (empty name)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/query/]
     */
    @Optional var query: String? = null,

    /**
     * Gives control over which attributes to retrieve and which not to retrieve.
     * Engine default: [*]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToRetrieve/]
     */
    @Optional var attributesToRetrieve: List<Attribute>? = null,

    /**
     * Restricts a given query to look in only a subset of your searchable attributes.
     * Engine default: all attributes in searchableAttributes.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/restrictSearchableAttributes/]
     */
    @Optional var restrictSearchableAttributes: List<Attribute>? = null,

    /**
     * Filter the query with numeric, facet and/or tag filters.
     * Engine default: "" (no filters)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
     */
    @Optional var filters: String? = null,

    /**
     * Filter hits by facet value.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facetFilters/]
     */
    @Optional var facetFilters: List<List<String>>? = null,

    /**
     * Create filters for ranking purposes, where records that match the filter are ranked highest.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalFilters/]
     */
    @Optional var optionalFilters: List<List<String>>? = null,

    /**
     * Filter on numeric attributes.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/numericFilters/]
     */
    @Optional var numericFilters: List<List<String>>? = null,

    /**
     * Filter hits by tags.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/tagFilters/]
     */
    @Optional var tagFilters: List<List<String>>? = null,

    /**
     * Determines how to calculate the total score for filtering.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/sumOrFiltersScores/]
     */
    @Optional var sumOrFiltersScores: Boolean? = null,

    /**
     * Facets to retrieve.
     * Engine default: [] (no facets retrieved)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facets/]
     */
    @Optional var facets: List<Attribute>? = null,

    /**
     * Maximum number of facet values to return for each facet during a regular search.
     * Engine default: 100
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/maxValuesPerFacet/]
     */
    @Optional var maxValuesPerFacet: Int? = null,

    /**
     * Force faceting to be applied after de-duplication (via the Distinct setting).
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facetingAfterDistinct/]
     */
    @Optional var facetingAfterDistinct: Boolean? = null,

    /**
     * Controls how facet values are sorted.
     * Engine default: [SortFacetValuesBy.Count]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/sortFacetValuesBy/]
     */
    @Optional var sortFacetValuesBy: SortFacetValuesBy? = null,

    /**
     * List of attributes to highlight.
     * Engine default: null (all searchable attributes)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToHighlight/]
     */
    @Optional var attributesToHighlight: List<Attribute>? = null,

    /**
     * List of attributes to snippet, with an optional maximum number of words to snippet.
     * Engine default: [] (no attribute is snippeted)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToSnippet/]
     */
    @Optional var attributesToSnippet: List<Snippet>? = null,

    /**
     * The HTML name to insert before the highlighted parts in all highlight and snippet results.
     * Engine default: <em>
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/highlightPreTag/]
     */
    @Optional var highlightPreTag: String? = null,

    /**
     * The HTML name to insert after the highlighted parts in all highlight and snippet results.
     * Engine default: </em>
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/highlightPostTag/]
     */
    @Optional var highlightPostTag: String? = null,

    /**
     * String used as an ellipsis indicator when a snippet is truncated.
     * Engine default: "…" (U+2026, HORIZONTAL ELLIPSIS)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/snippetEllipsisText/]
     */
    @Optional var snippetEllipsisText: String? = null,

    /**
     * Restrict highlighting and snippeting to items that matched the query.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/restrictHighlightAndSnippetArrays/]
     */
    @Optional var restrictHighlightAndSnippetArrays: Boolean? = null,

    /**
     * Specify the page to retrieve.
     * Engine default: 0
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/page/]
     */
    @Optional var page: Int? = null,

    /**
     * Set the number of hits per page.
     * Engine default: 20
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/hitsPerPage/]
     */
    @Optional var hitsPerPage: Int? = null,

    /**
     * Specify the offset of the first hit to return.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/offset/]
     */
    @Optional var offset: Int? = null,

    /**
     * Set the number of hits to retrieve (used only with offset).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/length/]
     */
    @Optional var length: Int? = null,

    /**
     * Minimum number of characters a word in the query name must contain to accept matches with 1 typo.
     * Engine default: 4
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minWordSizefor1Typo/]
     */
    @Optional var minWordSizefor1Typo: Int? = null,

    /**
     * Minimum number of characters a word in the query name must contain to accept matches with 2 typos.
     * Engine default: 8
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minWordSizefor2Typos/]
     */
    @Optional var minWordSizefor2Typos: Int? = null,

    /**
     * Controls whether typo tolerance is enabled and how it is applied.
     * Engine defaults: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/typoTolerance/]
     */
    @Optional var typoTolerance: TypoTolerance? = null,

    /**
     * Whether to allow typos on numbers (“numeric tokens”) in the query name.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/allowTyposOnNumericTokens/]
     */
    @Optional var allowTyposOnNumericTokens: Boolean? = null,

    /**
     * List of attributes on which you want to disable typo tolerance.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disableTypoToleranceOnAttributes/]
     */
    @Optional var disableTypoToleranceOnAttributes: List<Attribute>? = null,

    /**
     * Search for entries around a central geolocation, enabling a geo search within a circular area.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundLatLng/]
     */
    @Optional var aroundLatLng: String? = null,

    /**
     * Whether to search entries around a given location automatically computed from the requester’s IP address.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundLatLngViaIP/]
     */
    @Optional var aroundLatLngViaIP: Boolean? = null,

    /**
     * Define the maximum radius for a geo search (in meters).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundRadius/]
     */
    @Optional var aroundRadius: AroundRadius? = null,

    /**
     * Precision of geo search (in meters), to add grouping by geo location to the ranking formula.
     * Engine default: 1
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundPrecision/]
     */
    @Optional var aroundPrecision: Int? = null,

    /**
     * Minimum radius (in meters) used for a geo search when [aroundRadius] is not set.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minimumAroundRadius/]
     */
    @Optional var minimumAroundRadius: Int? = null,

    /**
     * Search inside a rectangular area (in geo coordinates).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/insideBoundingBox/]
     */
    @Optional var insideBoundingBox: List<BoundingBox>? = null,

    /**
     * Search inside a polygon (in geo coordinates).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/insidePolygon/]
     */
    @Optional var insidePolygon: List<Polygon>? = null,

    /**
     * Treats singular, plurals, and other forms of declensions as matching terms.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/ignorePlurals/]
     */
    @Optional var ignorePlurals: BooleanOrQueryLanguages? = null,

    /**
     * Removes stop (common) words from the query before executing it.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/removeStopWords/]
     */
    @Optional var removeStopWords: BooleanOrQueryLanguages? = null,

    /**
     * Sets the queryLanguage to be used by language-specific settings and functionalities such as
     * [ignorePlurals], [removeStopWords], and
     * [CJK word-detection][https://www.algolia.com/doc/guides/textual-relevance/queryLanguage/#using-a-language-specific-dictionary-for-cjk-words].
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/queryLanguages/]
     */
    @Optional var queryLanguages: List<QueryLanguage>? = null,

    /**
     * Whether rules should be globally enabled.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/enableRules/]
     */
    @Optional var enableRules: Boolean? = null,

    /**
     * Enables contextual rules.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/ruleContexts/]
     */
    @Optional var ruleContexts: List<String>? = null,

    /**
     * Enable the Personalization feature.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/enablePersonalization/]
     */
    @Optional var enablePersonalization: Boolean? = null,

    /**
     * Controls if and how query words are interpreted as [prefixes][https://www.algolia.com/doc/guides/textual-relevance/prefix-search/].
     * Engine default: [QueryType.PrefixLast]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/queryType/]
     */
    @Optional var queryType: QueryType? = null,

    /**
     * Selects a strategy to remove words from the query when it doesn’t match any hits.
     * Engine default: [RemoveWordIfNoResults.None]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/removeWordsIfNoResults/]
     */
    @Optional var removeWordsIfNoResults: RemoveWordIfNoResults? = null,

    /**
     * Enables the advanced query syntax.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/advancedSyntax/]
     */
    @Optional var advancedSyntax: Boolean? = null,

    /**
     * A list of words that should be considered as optional when found in the query.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalWords/]
     */
    @Optional var optionalWords: List<String>? = null,

    /**
     * List of attributes on which you want to disable the exact ranking criterion.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disableExactOnAttributes/]
     */
    @Optional var disableExactOnAttributes: List<Attribute>? = null,

    /**
     * Controls how the exact ranking criterion is computed when the query contains only one word.
     * Engine default: [ExactOnSingleWordQuery.Attribute]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/exactOnSingleWordQuery/]
     */
    @Optional var exactOnSingleWordQuery: ExactOnSingleWordQuery? = null,

    /**
     * List of alternatives that should be considered an exact match by the exact ranking criterion.
     * Engine default: [[AlternativesAsExact.IgnorePlurals], [AlternativesAsExact.SingleWordSynonym]]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/alternativesAsExact/]
     */
    @Optional var alternativesAsExact: List<AlternativesAsExact>? = null,

    /**
     * Enables de-duplication or grouping of results.
     * Engine default: 0
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/distinct/]
     */
    @Optional var distinct: Int? = null,

    /**
     * Retrieve detailed ranking information.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/getRankingInfo/]
     */
    @Optional var getRankingInfo: Boolean? = null,

    /**
     * Enable the Click Analytics feature.
     * Engine default: false.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/clickAnalytics/]
     */
    @Optional var clickAnalytics: Boolean? = null,

    /**
     * Whether the current query will be taken into account in the Analytics.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/analytics/]
     */
    @Optional var analytics: Boolean? = null,

    /**
     * List of tags to apply to the query in the analytics.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/analyticsTags/]
     */
    @Optional var analyticsTags: List<String>? = null,

    /**
     * Whether to take into account an index’s synonyms for a particular search.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/synonyms/]
     */
    @Optional var synonyms: Boolean? = null,

    /**
     * Whether to highlight and snippet the original word that matches the synonym or the synonym itself.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/replaceSynonymsInHighlight/]
     */
    @Optional var replaceSynonymsInHighlight: Boolean? = null,

    /**
     * Precision of the proximity ranking criterion.
     * Engine default: 1
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minProximity/]
     */
    @Optional var minProximity: Int? = null,

    /**
     * Choose which fields the response will contain. Applies to search and browse queries.
     * Engine default: [ResponseFields.All]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/responseFields/]
     */
    @Optional var responseFields: List<ResponseFields>? = null,

    /**
     * Maximum number of facet hits to return during a search for facet values.
     * Engine default: 10
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/maxFacetHits/]
     */
    @Optional var maxFacetHits: Int? = null,

    /**
     * Whether to include or exclude a query from the processing-time percentile computation.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/percentileComputation/]
     */
    @Optional var percentileComputation: Boolean? = null
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