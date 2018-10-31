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
     * @return A list of attributes set for retrieval.
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
     * Set the list of attributes to retrieve to null.
     */
    fun clearAttributesToRetrieve() {
        attributesToRetrieve = null
    }

    /**
     * Determines how to calculate the total score for filtering.
     * Engine default: false.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/sumOrFiltersScores/]
     */
    var sumOrFiltersScores: Boolean? = null

    /**
     * Force faceting to be applied after de-duplication (via the Distinct setting).
     * Engine default: false.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facetingAfterDistinct/]
     */
    var facetingAfterDistinct: Boolean? = null

    /**
     * Restrict highlighting and snippeting to items that matched the query.
     * Engine default: false.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/restrictHighlightAndSnippetArrays/]
     */
    var restrictHighlightAndSnippetArray: Boolean? = null

    /**
     * Whether to allow typos on numbers (“numeric tokens”) in the query string.
     * Engine default: true.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/allowTyposOnNumericTokens/]
     */
    var allowTyposOnNumericTokens: Boolean? = null

    /**
     * Whether to search entries around a given location automatically computed from the requester’s IP address.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundLatLngViaIP/]
     */
    var aroundLatLngViaIP: Boolean? = null

    /**
     * Whether rules should be globally enabled.
     * Engine default: true.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/enableRules/]
     */
    var enableRules: Boolean? = null

    /**
     * Enables the advanced query syntax.
     * Engine default: false.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/advancedSyntax/]
     */
    var advancedSyntax: Boolean? = null

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
     * Whether to include or exclude a query from the processing-time percentile computation.
     * Engine default: true.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/percentileComputation/]
     */
    var percentileComputation: Boolean? = null
}