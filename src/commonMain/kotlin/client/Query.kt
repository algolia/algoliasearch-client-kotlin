package client


class Query {

    /**
     * The text to search in the index.
     * Engine default: "" (empty string)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/query/]
     */
    var query: String? = null

    /**
     * A list of attributes set for retrieval.
     * Engine default: ["*"]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToRetrieve/]
     */
    private var attributesToRetrieve: List<String>? = null

    /**
     * @return The list of attributes to be retrieved in the search response.
     */
    fun getAttributesToRetrieve(): List<String>? = attributesToRetrieve

    /**
     * @param attributes A list of attributes.
     * Set which attributes should be included the in the search response.
     */
    fun setAttributesToRetrieve(vararg attributes: String) {
        attributesToRetrieve = attributes.toList()
    }

    /**
     * @param attributes A list of attributes.
     * Set which attributes should be excluded from the search response.
     */
    fun setAttributesToRetrieveExcept(vararg attributes: String) {
        val excepts = attributes.map { "-$it" }
        attributesToRetrieve = if (excepts.isNotEmpty()) excepts.plus("*") else excepts
    }

    /**
     * Set the list of attributes to be retrieved in the search response to null.
     */
    fun clearAttributesToRetrieve() {
        attributesToRetrieve = null
    }

    /**
     * Restricts a given query to look in only a subset of your searchable attributes.
     * Engine default: all attributes in searchableAttributes.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/restrictSearchableAttributes/]
     */
    var restrictSearchableAttributes: List<String>? = null

    /**
     * Determines how to calculate the total score for filtering.
     * Engine default: false.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/sumOrFiltersScores/]
     */
    var sumOrFiltersScores: Boolean? = null

    /**
     * Facets to retrieve.
     * Engine default: []. (no facets retrieved)
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
     * Engine default: false.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facetingAfterDistinct/]
     */
    var facetingAfterDistinct: Boolean? = null

    /**
     * List of attributes to highlight.
     * Engine default: null. (all searchable attributes)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToHighlight/]
     */
    private var attributesToHighlight: List<String>? = null

    /**
     * @return The list of attributes to be highlighted in the search response.
     */
    fun getAttributesToHighlight(): List<String>? = attributesToHighlight

    /**
     * @param attributes A list of attributes.
     * Set which attributes should be highlighted in the search response.
     */
    fun setAttributesToHighlight(vararg attributes: String) {
        attributesToHighlight = attributes.toList()
    }

    /**
     * Set all attributes to be highlighted in the search response.
     */
    fun highlightAllAttributes() {
        attributesToHighlight = listOf("*")
    }

    /**
     * Set the list of attributes to be highlighted in the search response to null.
     */
    fun clearAttributesToHighlight() {
        attributesToHighlight = null
    }

    /**
     * Restrict highlighting and snippeting to items that matched the query.
     * Engine default: false.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/restrictHighlightAndSnippetArrays/]
     */
    var restrictHighlightAndSnippetArray: Boolean? = null

    /**
     * Specify the page to retrieve.
     * Engine default: 0.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/page/]
     */
    var page: Int? = null

    /**
     * Set the number of hits per page.
     * Engine default: 20.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/hitsPerPage/]
     */
    var hitsPerPage: Int? = null

    /**
     * Specify the offset of the first hit to return.
     * Engine default: null.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/offset/]
     */
    var offset: Int? = null

    /**
     * Set the number of hits to retrieve (used only with offset).
     * Engine default: null.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/length/]
     */
    var length: Int? = null

    /**
     * Minimum number of characters a word in the query string must contain to accept matches with 1 typo.
     * Engine default: 4.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minWordSizefor1Typo/]
     */
    var minWordSizefor1Typo: Int? = null

    /**
     * Minimum number of characters a word in the query string must contain to accept matches with 2 typos.
     * Engine default: 8.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minWordSizefor2Typos/]
     */
    var minWordSizefor2Typos: Int? = null

    /**
     * Whether to allow typos on numbers (“numeric tokens”) in the query string.
     * Engine default: true.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/allowTyposOnNumericTokens/]
     */
    var allowTyposOnNumericTokens: Boolean? = null

    /**
     * List of attributes on which you want to disable typo tolerance.
     * Engine default: [].
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disableTypoToleranceOnAttributes/]
     */
    var disableTypoToleranceOnAttributes: List<String>? = null

    /**
     * Whether to search entries around a given location automatically computed from the requester’s IP address.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundLatLngViaIP/]
     */
    var aroundLatLngViaIP: Boolean? = null

    /**
     * Precision of geo search (in meters), to add grouping by geo location to the ranking formula.
     * Engine default: 1.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundPrecision/]
     */
    var aroundPrecision: Int? = null

    /**
     * Minimum radius (in meters) used for a geo search when [aroundRadius] is not set.
     * Engine default: null.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minimumAroundRadius/]
     */
    var minimumAroundRadius: Int? = null

    /**
     * Whether rules should be globally enabled.
     * Engine default: true.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/enableRules/]
     */
    var enableRules: Boolean? = null

    /**
     * Enables contextual rules.
     * Engine default: [].
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/ruleContexts/]
     */
    var ruleContexts: List<String>? = null

    /**
     * Enables the advanced query syntax.
     * Engine default: false.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/advancedSyntax/]
     */
    var advancedSyntax: Boolean? = null

    /**
     * A list of words that should be considered as optional when found in the query.
     * Engine default: [].
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
     * Engine default: [].
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disableExactOnAttributes/]
     */
    var disableExactOnAttributes: List<String>? = null

    /**
     * Enables de-duplication or grouping of results.
     * Engine default: 0.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/distinct/]
     */
    var distinct: Int? = null

    /**
     * Retrieve detailed ranking information.
     * Engine default: false.
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
     * Engine default: true.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/analytics/]
     */
    var analytics: Boolean? = null

    /**
     * Whether to take into account an index’s synonyms for a particular search.
     * Engine default: true.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/synonyms/]
     */
    var synonyms: Boolean? = null

    /**
     * Whether to highlight and snippet the original word that matches the synonym or the synonym itself.
     * Engine default: true.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/replaceSynonymsInHighlight/]
     */
    var replaceSynonymsInHighlight: Boolean? = null

    /**
     * Precision of the proximity ranking criterion.
     * Engine default: 1.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minProximity/]
     */
    var minProximity: Int? = null

    /**
     * Maximum number of facet hits to return during a search for facet values.
     * Engine default: 10.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/maxFacetHits/]
     */
    var maxFacetHits: Int? = null

    /**
     * Whether to include or exclude a query from the processing-time percentile computation.
     * Engine default: true.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/percentileComputation/]
     */
    var percentileComputation: Boolean? = null
}