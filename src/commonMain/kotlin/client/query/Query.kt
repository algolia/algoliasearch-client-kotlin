package client.query


class Query {

    /**
     * The text to search in the index.
     * Engine default: "" (empty string)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/query/]
     */
    var query: String? = null

    /**
     * Gives control over which attributes to retrieve and which not to retrieve.
     * Engine default: [*]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToRetrieve/]
     */
    var attributesToRetrieve: List<String>? = null

    /**
     * Restricts a given query to look in only a subset of your searchable attributes.
     * Engine default: all attributes in searchableAttributes.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/restrictSearchableAttributes/]
     */
    var restrictSearchableAttributes: List<String>? = null

    /**
     * Filter the query with numeric, facet and/or tag filters.
     * Engine default: "" (no filters)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
     */
    var filters: String? = null

    /**
     * Filter hits by facet value.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facetFilters/]
     */
    var facetFilters: List<List<String>>? = null


    /**
     * Create filters for ranking purposes, where records that match the filter are ranked highest.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalFilters/]
     */
    var optionalFilters: List<List<String>>? = null

    /**
     * Filter on numeric attributes.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/numericFilters/]
     */
    var numericFilters: List<List<String>>? = null

    /**
     * Filter hits by tags.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/tagFilters/]
     */
    var tagFilters: List<List<String>>? = null

    /**
     * Determines how to calculate the total score for filtering.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/sumOrFiltersScores/]
     */
    var sumOrFiltersScores: Boolean? = null

    /**
     * Facets to retrieve.
     * Engine default: [] (no facets retrieved)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facets/]
     */
    var facets: List<String>? = null

    /**
     * Maximum number of facet values to return for each facet during a regular search.
     * Engine default: 100
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/maxValuesPerFacet/]
     */
    var maxValuesPerFacet: Int? = null

    /**
     * Force faceting to be applied after de-duplication (via the Distinct setting).
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facetingAfterDistinct/]
     */
    var facetingAfterDistinct: Boolean? = null

    /**
     * Controls how facet values are sorted.
     * Engine default: [SortFacetValuesBy.Count]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/sortFacetValuesBy/]
     */
    var sortFacetValuesBy: SortFacetValuesBy? = null

    /**
     * List of attributes to highlight.
     * Engine default: null (all searchable attributes)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToHighlight/]
     */
    var attributesToHighlight: List<String>? = null

    /**
     * List of attributes to snippet, with an optional maximum number of words to snippet.
     * Engine default: [] (no attribute is snippeted)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToSnippet/]
     */
    var attributesToSnippet: List<Snippet>? = null

    /**
     * The HTML string to insert before the highlighted parts in all highlight and snippet results.
     * Engine default: <em>
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/highlightPreTag/]
     */
    var highlightPreTag: String? = null

    /**
     * The HTML string to insert after the highlighted parts in all highlight and snippet results.
     * Engine default: </em>
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/highlightPostTag/]
     */
    var highlightPostTag: String? = null

    /**
     * String used as an ellipsis indicator when a snippet is truncated.
     * Engine default: "…" (U+2026, HORIZONTAL ELLIPSIS)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/snippetEllipsisText/]
     */
    var snippetEllipsisText: String? = null

    /**
     * Restrict highlighting and snippeting to items that matched the query.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/restrictHighlightAndSnippetArrays/]
     */
    var restrictHighlightAndSnippetArray: Boolean? = null

    /**
     * Specify the page to retrieve.
     * Engine default: 0
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/page/]
     */
    var page: Int? = null

    /**
     * Set the number of hits per page.
     * Engine default: 20
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/hitsPerPage/]
     */
    var hitsPerPage: Int? = null

    /**
     * Specify the offset of the first hit to return.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/offset/]
     */
    var offset: Int? = null

    /**
     * Set the number of hits to retrieve (used only with offset).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/length/]
     */
    var length: Int? = null

    /**
     * Minimum number of characters a word in the query string must contain to accept matches with 1 typo.
     * Engine default: 4
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minWordSizefor1Typo/]
     */
    var minWordSizefor1Typo: Int? = null

    /**
     * Minimum number of characters a word in the query string must contain to accept matches with 2 typos.
     * Engine default: 8
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minWordSizefor2Typos/]
     */
    var minWordSizefor2Typos: Int? = null

    /**
     * Controls whether typo tolerance is enabled and how it is applied.
     * Engine defaults: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/typoTolerance/]
     */
    var typoTolerance: TypoTolerance? = null

    /**
     * Whether to allow typos on numbers (“numeric tokens”) in the query string.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/allowTyposOnNumericTokens/]
     */
    var allowTyposOnNumericTokens: Boolean? = null

    /**
     * List of attributes on which you want to disable typo tolerance.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disableTypoToleranceOnAttributes/]
     */
    var disableTypoToleranceOnAttributes: List<String>? = null

    /**
     * Search for entries around a central geolocation, enabling a geo search within a circular area.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundLatLng/]
     */
    var aroundLatLng: String? = null

    /**
     * Whether to search entries around a given location automatically computed from the requester’s IP address.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundLatLngViaIP/]
     */
    var aroundLatLngViaIP: Boolean? = null

    /**
     * Define the maximum radius for a geo search (in meters).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundRadius/]
     */
    var aroundRadius: AroundRadius? = null

    /**
     * Precision of geo search (in meters), to add grouping by geo location to the ranking formula.
     * Engine default: 1
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundPrecision/]
     */
    var aroundPrecision: Int? = null

    /**
     * Minimum radius (in meters) used for a geo search when [aroundRadius] is not set.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minimumAroundRadius/]
     */
    var minimumAroundRadius: Int? = null

    /**
     * Search inside a rectangular area (in geo coordinates).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/insideBoundingBox/]
     */
    var insideBoundingBox: List<Float>? = null

    /**
     * Search inside a polygon (in geo coordinates).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/insidePolygon/]
     */
    var insidePolygon: List<Float>? = null

    /**
     * Treats singular, plurals, and other forms of declensions as matching terms.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/ignorePlurals/]
     */
    var ignorePlurals: BooleanOrISOCodes? = null

    /**
     * Removes stop (common) words from the query before executing it.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/removeStopWords/]
     */
    var removeStopWords: BooleanOrISOCodes? = null

    /**
     * Sets the languages to be used by language-specific settings and functionalities such as
     * [ignorePlurals], [removeStopWords], and
     * [CJK word-detection][https://www.algolia.com/doc/guides/textual-relevance/languages/#using-a-language-specific-dictionary-for-cjk-words].
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/queryLanguages/]
     */
    var queryLanguages: List<QueryLanguage>? = null

    /**
     * Whether rules should be globally enabled.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/enableRules/]
     */
    var enableRules: Boolean? = null

    /**
     * Enables contextual rules.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/ruleContexts/]
     */
    var ruleContexts: List<String>? = null

    /**
     * Controls if and how query words are interpreted as [prefixes][https://www.algolia.com/doc/guides/textual-relevance/prefix-search/].
     * Engine default: [QueryType.PrefixLast]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/queryType/]
     */
    var queryType: QueryType? = null

    /**
     * Selects a strategy to remove words from the query when it doesn’t match any hits.
     * Engine default: [RemoveWordIfNoResults.None]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/removeWordsIfNoResults/]
     */
    var removeWordsIfNoResults: RemoveWordIfNoResults? = null

    /**
     * Enables the advanced query syntax.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/advancedSyntax/]
     */
    var advancedSyntax: Boolean? = null

    /**
     * A list of words that should be considered as optional when found in the query.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalWords/]
     */
    var optionalWords: List<String>? = null

    /**
     * When this attribute is set to true, the list of words in [optionalWords] will be replaced by all the words
     * found in the current [query].
     * This will trigger the engine to return records containing any word matching the query (OR operation).
     * Otherwise, the engine return records containing all the word matching the query (AND operation).
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalWords/#doing-an-or-between-all-words-of-a-query]
     */
    var isEveryWordInQueryOptional: Boolean = false

    /**
     * List of attributes on which you want to disable the exact ranking criterion.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disableExactOnAttributes/]
     */
    var disableExactOnAttributes: List<String>? = null

    /**
     * Controls how the exact ranking criterion is computed when the query contains only one word.
     * Engine default: [ExactOnSingleWordQuery.Attribute]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/exactOnSingleWordQuery/]
     */
    var exactOnSingleWordQuery: ExactOnSingleWordQuery? = null

    /**
     * List of alternatives that should be considered an exact match by the exact ranking criterion.
     * Engine default: [[AlternativesAsExact.IgnorePlurals], [AlternativesAsExact.SingleWordSynonym]]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/alternativesAsExact/]
     */
    var alternativesAsExact: List<AlternativesAsExact>? = null

    /**
     * Enables de-duplication or grouping of results.
     * Engine default: 0
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/distinct/]
     */
    var distinct: Int? = null

    /**
     * Retrieve detailed ranking information.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/getRankingInfo/]
     */
    var getRankingInfo: Boolean? = null

    /**
     * Enable the Click Analytics feature.
     * Engine default: false.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/clickAnalytics/]
     */
    var clickAnalytics: Boolean? = null

    /**
     * Whether the current query will be taken into account in the Analytics.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/analytics/]
     */
    var analytics: Boolean? = null

    /**
     * List of tags to apply to the query in the analytics.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/analyticsTags/]
     */
    var analyticsTags: List<String>? = null

    /**
     * Whether to take into account an index’s synonyms for a particular search.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/synonyms/]
     */
    var synonyms: Boolean? = null

    /**
     * Whether to highlight and snippet the original word that matches the synonym or the synonym itself.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/replaceSynonymsInHighlight/]
     */
    var replaceSynonymsInHighlight: Boolean? = null

    /**
     * Precision of the proximity ranking criterion.
     * Engine default: 1
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minProximity/]
     */
    var minProximity: Int? = null

    /**
     * Choose which fields the response will contain. Applies to search and browse queries.
     * Engine default: [ResponseFields.All]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/responseFields/]
     */
    var responseFields: List<ResponseFields>? = null

    /**
     * Maximum number of facet hits to return during a search for facet values.
     * Engine default: 10
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/maxFacetHits/]
     */
    var maxFacetHits: Int? = null

    /**
     * Whether to include or exclude a query from the processing-time percentile computation.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/percentileComputation/]
     */
    var percentileComputation: Boolean? = null
}