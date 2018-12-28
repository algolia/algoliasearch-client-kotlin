package client.query

import client.data.*
import client.query.helper.FilterBuilder
import client.query.helper.OptionalFilterBuilder
import client.query.helper.QueryHelper
import client.serialize.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.json

@QueryHelper
class Query(
    /**
     * The text to search in the index.
     * Engine default: "" (empty name)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/query/]
     */
    var query: String? = null,

    /**
     * Gives control over which attributes to retrieve and which not to retrieve.
     * Engine default: [*]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToRetrieve/]
     */
    var attributesToRetrieve: List<Attribute>? = null,

    /**
     * Restricts a given query to look in only a subset of your searchable attributes.
     * Engine default: all attributes in searchableAttributes.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/restrictSearchableAttributes/]
     */
    var restrictSearchableAttributes: List<Attribute>? = null,

    /**
     * Filter the query with numeric, facet and/or tag filters.
     * Engine default: "" (no filters)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
     */
    var filters: String? = null,

    /**
     * Filter hits by facet value.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facetFilters/]
     */
    var facetFilters: List<List<String>>? = null,

    /**
     * Create filters for ranking purposes, where records that match the filter are ranked highest.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalFilters/]
     */
    var optionalFilters: List<List<String>>? = null,

    /**
     * Filter on numeric attributes.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/numericFilters/]
     */
    var numericFilters: List<List<String>>? = null,

    /**
     * Filter hits by tags.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/tagFilters/]
     */
    var tagFilters: List<List<String>>? = null,

    /**
     * Determines how to calculate the total score for filtering.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/sumOrFiltersScores/]
     */
    var sumOrFiltersScores: Boolean? = null,

    /**
     * Facets to retrieve.
     * Engine default: [] (no facets retrieved)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facets/]
     */
    var facets: List<Attribute>? = null,

    /**
     * Maximum number of facet values to return for each facet during a regular search.
     * Engine default: 100
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/maxValuesPerFacet/]
     */
    var maxValuesPerFacet: Int? = null,

    /**
     * Force faceting to be applied after de-duplication (via the Distinct setting).
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facetingAfterDistinct/]
     */
    var facetingAfterDistinct: Boolean? = null,

    /**
     * Controls how facet values are sorted.
     * Engine default: [SortFacetValuesBy.Count]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/sortFacetValuesBy/]
     */
    var sortFacetValuesBy: SortFacetValuesBy? = null,

    /**
     * List of attributes to highlight.
     * Engine default: null (all searchable attributes)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToHighlight/]
     */
    var attributesToHighlight: List<Attribute>? = null,

    /**
     * List of attributes to snippet, with an optional maximum number of words to snippet.
     * Engine default: [] (no attribute is snippeted)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToSnippet/]
     */
    var attributesToSnippet: List<Snippet>? = null,

    /**
     * The HTML name to insert before the highlighted parts in all highlight and snippet results.
     * Engine default: <em>
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/highlightPreTag/]
     */
    var highlightPreTag: String? = null,

    /**
     * The HTML name to insert after the highlighted parts in all highlight and snippet results.
     * Engine default: </em>
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/highlightPostTag/]
     */
    var highlightPostTag: String? = null,

    /**
     * String used as an ellipsis indicator when a snippet is truncated.
     * Engine default: "…" (U+2026, HORIZONTAL ELLIPSIS)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/snippetEllipsisText/]
     */
    var snippetEllipsisText: String? = null,

    /**
     * Restrict highlighting and snippeting to items that matched the query.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/restrictHighlightAndSnippetArrays/]
     */
    var restrictHighlightAndSnippetArrays: Boolean? = null,

    /**
     * Specify the page to retrieve.
     * Engine default: 0
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/page/]
     */
    var page: Int? = null,

    /**
     * Set the number of hits per page.
     * Engine default: 20
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/hitsPerPage/]
     */
    var hitsPerPage: Int? = null,

    /**
     * Specify the offset of the first hit to return.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/offset/]
     */
    var offset: Int? = null,

    /**
     * Set the number of hits to retrieve (used only with offset).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/length/]
     */
    var length: Int? = null,

    /**
     * Minimum number of characters a word in the query name must contain to accept matches with 1 typo.
     * Engine default: 4
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minWordSizefor1Typo/]
     */
    var minWordSizefor1Typo: Int? = null,

    /**
     * Minimum number of characters a word in the query name must contain to accept matches with 2 typos.
     * Engine default: 8
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minWordSizefor2Typos/]
     */
    var minWordSizefor2Typos: Int? = null,

    /**
     * Controls whether typo tolerance is enabled and how it is applied.
     * Engine defaults: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/typoTolerance/]
     */
    var typoTolerance: TypoTolerance? = null,

    /**
     * Whether to allow typos on numbers (“numeric tokens”) in the query name.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/allowTyposOnNumericTokens/]
     */
    var allowTyposOnNumericTokens: Boolean? = null,

    /**
     * List of attributes on which you want to disable typo tolerance.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disableTypoToleranceOnAttributes/]
     */
    var disableTypoToleranceOnAttributes: List<Attribute>? = null,

    /**
     * Search for entries around a central geolocation, enabling a geo search within a circular area.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundLatLng/]
     */
    var aroundLatLng: String? = null,

    /**
     * Whether to search entries around a given location automatically computed from the requester’s IP address.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundLatLngViaIP/]
     */
    var aroundLatLngViaIP: Boolean? = null,

    /**
     * Define the maximum radius for a geo search (in meters).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundRadius/]
     */
    var aroundRadius: AroundRadius? = null,

    /**
     * Precision of geo search (in meters), to add grouping by geo location to the ranking formula.
     * Engine default: 1
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundPrecision/]
     */
    var aroundPrecision: Int? = null,

    /**
     * Minimum radius (in meters) used for a geo search when [aroundRadius] is not set.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minimumAroundRadius/]
     */
    var minimumAroundRadius: Int? = null,

    /**
     * Search inside a rectangular area (in geo coordinates).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/insideBoundingBox/]
     */
    var insideBoundingBox: List<BoundingBox>? = null,

    /**
     * Search inside a polygon (in geo coordinates).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/insidePolygon/]
     */
    var insidePolygon: List<Polygon>? = null,

    /**
     * Treats singular, plurals, and other forms of declensions as matching terms.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/ignorePlurals/]
     */
    var ignorePlurals: BooleanOrQueryLanguages? = null,

    /**
     * Removes stop (common) words from the query before executing it.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/removeStopWords/]
     */
    var removeStopWords: BooleanOrQueryLanguages? = null,

    /**
     * Sets the queryLanguage to be used by language-specific settings and functionalities such as
     * [ignorePlurals], [removeStopWords], and
     * [CJK word-detection][https://www.algolia.com/doc/guides/textual-relevance/queryLanguage/#using-a-language-specific-dictionary-for-cjk-words].
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/queryLanguages/]
     */
    var queryLanguages: List<QueryLanguage>? = null,

    /**
     * Whether rules should be globally enabled.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/enableRules/]
     */
    var enableRules: Boolean? = null,

    /**
     * Enables contextual rules.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/ruleContexts/]
     */
    var ruleContexts: List<String>? = null,

    /**
     * Controls if and how query words are interpreted as [prefixes][https://www.algolia.com/doc/guides/textual-relevance/prefix-search/].
     * Engine default: [QueryType.PrefixLast]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/queryType/]
     */
    var queryType: QueryType? = null,

    /**
     * Selects a strategy to remove words from the query when it doesn’t match any hits.
     * Engine default: [RemoveWordIfNoResults.None]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/removeWordsIfNoResults/]
     */
    var removeWordsIfNoResults: RemoveWordIfNoResults? = null,

    /**
     * Enables the advanced query syntax.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/advancedSyntax/]
     */
    var advancedSyntax: Boolean? = null,

    /**
     * A list of words that should be considered as optional when found in the query.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalWords/]
     */
    var optionalWords: List<String>? = null,

    /**
     * List of attributes on which you want to disable the exact ranking criterion.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disableExactOnAttributes/]
     */
    var disableExactOnAttributes: List<Attribute>? = null,

    /**
     * Controls how the exact ranking criterion is computed when the query contains only one word.
     * Engine default: [ExactOnSingleWordQuery.Attribute]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/exactOnSingleWordQuery/]
     */
    var exactOnSingleWordQuery: ExactOnSingleWordQuery? = null,

    /**
     * List of alternatives that should be considered an exact match by the exact ranking criterion.
     * Engine default: [[AlternativesAsExact.IgnorePlurals], [AlternativesAsExact.SingleWordSynonym]]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/alternativesAsExact/]
     */
    var alternativesAsExact: List<AlternativesAsExact>? = null,

    /**
     * Enables de-duplication or grouping of results.
     * Engine default: 0
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/distinct/]
     */
    var distinct: Int? = null,

    /**
     * Retrieve detailed ranking information.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/getRankingInfo/]
     */
    var getRankingInfo: Boolean? = null,

    /**
     * Enable the Click Analytics feature.
     * Engine default: false.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/clickAnalytics/]
     */
    var clickAnalytics: Boolean? = null,

    /**
     * Whether the current query will be taken into account in the Analytics.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/analytics/]
     */
    var analytics: Boolean? = null,

    /**
     * List of tags to apply to the query in the analytics.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/analyticsTags/]
     */
    var analyticsTags: List<String>? = null,

    /**
     * Whether to take into account an index’s synonyms for a particular search.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/synonyms/]
     */
    var synonyms: Boolean? = null,

    /**
     * Whether to highlight and snippet the original word that matches the synonym or the synonym itself.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/replaceSynonymsInHighlight/]
     */
    var replaceSynonymsInHighlight: Boolean? = null,

    /**
     * Precision of the proximity ranking criterion.
     * Engine default: 1
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minProximity/]
     */
    var minProximity: Int? = null,

    /**
     * Choose which fields the response will contain. Applies to search and browse queries.
     * Engine default: [ResponseFields.All]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/responseFields/]
     */
    var responseFields: List<ResponseFields>? = null,

    /**
     * Maximum number of facet hits to return during a search for facet values.
     * Engine default: 10
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/maxFacetHits/]
     */
    var maxFacetHits: Int? = null,

    /**
     * Whether to include or exclude a query from the processing-time percentile computation.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/percentileComputation/]
     */
    var percentileComputation: Boolean? = null
) {

    /**
     * When this attribute is set to true, the list of words in [optionalWords] will be replaced by all the words
     * found in the current [query].
     * This will trigger the engine to return records containing any word matching the query (OR operation).
     * Otherwise, the engine return records containing all the word matching the query (AND operation).
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalWords/#doing-an-or-between-all-words-of-a-query]
     */
    var isEveryWordInQueryOptional: Boolean = false

    /**
     * You can modify this instance of [FilterBuilder] or assign a new one.
     * If [filters] is null, the output of [FilterBuilder.build] will be passed to the request body of the next request.
     */
    var filterBuilder: FilterBuilder = FilterBuilder()

    /**
     * You can modify this instance of [OptionalFilterBuilder] or assign a new one.
     * If [optionalFilters] is null, the output of [OptionalFilterBuilder.build] will be passed to
     * the request body of the next request.
     */
    var optionalFilterBuilder: OptionalFilterBuilder = OptionalFilterBuilder()

    internal companion object : Serializer<Query> {

        override fun serialize(input: Query): JsonElement {
            return input.run {
                json {
                    // Query
                    query?.let { KeyQuery to it }
                    //Attributes
                    attributesToRetrieve?.let { KeyAttributesToRetrieve to Attribute.serializeList(it) }
                    restrictSearchableAttributes?.let { KeyRestrictSearchableAttributes to Attribute.serializeList(it) }
                    // Filters
                    filters?.let { KeyFilters to it }
                    facetFilters?.let { KeyFacetFilters to ListSerializer.serializeList(it) }
                    optionalFilters?.let { KeyOptionalFilters to ListSerializer.serializeList(it) }
                    numericFilters?.let { KeyNumericFilters to ListSerializer.serializeList(it) }
                    tagFilters?.let { KeyTagFilters to ListSerializer.serializeList(it) }
                    sumOrFiltersScores?.let { KeySumOrFiltersScores to it }
                    // Facets
                    facets?.let { KeyFacets to Attribute.serializeList(it) }
                    maxValuesPerFacet?.let { KeyMaxValuesPerFacet to it }
                    facetingAfterDistinct?.let { KeyFacetingAfterDistinct to it }
                    sortFacetValuesBy?.let { KeySortFacetValuesBy to SortFacetValuesBy.serialize(it) }
                    // Highlighting
                    attributesToHighlight?.let { KeyAttributesToHighlight to Attribute.serializeList(it) }
                    attributesToSnippet?.let { KeyAttributesToSnippet to Snippet.serializeList(it) }
                    highlightPreTag?.let { KeyHighlightPreTag to it }
                    highlightPostTag?.let { KeyHighlightPostTag to it }
                    snippetEllipsisText?.let { KeySnippetEllipsisText to it }
                    restrictHighlightAndSnippetArrays?.let { KeyRestrictHighlightAndSnippetArrays to it }
                    // Pagination
                    page?.let { KeyPage to it }
                    hitsPerPage?.let { KeyHitsPerPage to it }
                    offset?.let { KeyOffset to it }
                    length?.let { KeyLength to it }
                    //Typos
                    minWordSizefor1Typo?.let { KeyMinWordSizefor1Typo to it }
                    minWordSizefor2Typos?.let { KeyMinWordSizefor2Typos to it }
                    typoTolerance?.let { KeyTypoTolerance to TypoTolerance.serialize(it) }
                    allowTyposOnNumericTokens?.let { KeyAllowTyposOnNumericTokens to it }
                    disableTypoToleranceOnAttributes?.let {
                        KeyDisableTypoToleranceOnAttributes to Attribute.serializeList(it)
                    }
                    // Geo-Search
                    aroundLatLng?.let { KeyAroundLatLng to it }
                    aroundLatLngViaIP?.let { KeyAroundLatLngViaIP to it }
                    aroundRadius?.let { KeyAroundRadius to AroundRadius.serialize(it) }
                    aroundPrecision?.let { KeyAroundPrecision to it }
                    minimumAroundRadius?.let { KeyMinimumAroundRadius to it }
                    insideBoundingBox?.let { KeyInsideBoundingBox to BoundingBox.serializeList(it) }
                    insidePolygon?.let { KeyInsidePolygon to Polygon.serializeList(it) }
                    // Languages
                    ignorePlurals?.let { KeyIgnorePlurals to BooleanOrQueryLanguages.serialize(it) }
                    removeStopWords?.let { KeyRemoveStopWords to BooleanOrQueryLanguages.serialize(it) }
                    queryLanguages?.let { KeyQueryLanguages to QueryLanguage.serializeList(it) }
                    // Query-rules
                    enableRules?.let { KeyEnableRules to it }
                    ruleContexts?.let { KeyRuleContexts to ListSerializer.serialize(it) }
                    // Query-strategy
                    queryType?.let { KeyQueryType to QueryType.serialize(it) }
                    removeWordsIfNoResults?.let { KeyRemoveWordsIfNoResults to RemoveWordIfNoResults.serialize(it) }
                    advancedSyntax?.let { KeyAdvancedSyntax to it }
                    optionalWords?.let { KeyOptionalWords to ListSerializer.serialize(it) }
                    disableExactOnAttributes?.let { KeyDisableExactOnAttributes to Attribute.serializeList(it) }
                    exactOnSingleWordQuery?.let { KeyExactOnSingleWordQuery to ExactOnSingleWordQuery.serialize(it) }
                    alternativesAsExact?.let { KeyAlternativesAsExact to AlternativesAsExact.serializeList(it) }
                    // Advanced
                    distinct?.let { KeyDistinct to it }
                    getRankingInfo?.let { KeyGetRankingInfo to it }
                    clickAnalytics?.let { KeyClickAnalytics to it }
                    analytics?.let { KeyAnalytics to it }
                    analyticsTags?.let { KeyAnalyticsTags to ListSerializer.serialize(it) }
                    synonyms?.let { KeySynonyms to it }
                    replaceSynonymsInHighlight?.let { KeyReplaceSynonymsInHighlight to it }
                    minProximity?.let { KeyMinProximity to it }
                    responseFields?.let { KeyResponseFields to ResponseFields.serializeList(it) }
                    maxFacetHits?.let { KeyMaxFacetHits to it }
                    percentileComputation?.let { KeyPercentileComputation to it }
                }
            }
        }
    }
}