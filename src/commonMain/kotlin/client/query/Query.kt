package client.query


class Query {

    enum class SortFacetValuesBy(val raw: String) {
        /**
         * Facet values are sorted by decreasing count.
         * The count is the number of records containing this facet value in the results of the query.
         */
        Count("count"),
        /**
         * Facet values are sorted in alphabetical order, ascending from A to Z.
         * The count is the number of records containing this facet value in the results of the query.
         */
        Alpha("alpha")
    }

    enum class QueryLanguage(val code: String) {
        Afrikaans("af"),
        Arabic("ar"),
        Azeri("az"),
        Bulgarian("bg"),
        Brunei("bn"),
        Catalan("ca"),
        Czech("cs"),
        Welsh("cy"),
        Danis("da"),
        German("de"),
        English("en"),
        Esperanto("eo"),
        Spanish("es"),
        Estonian("et"),
        Basque("eu"),
        Finnish("fi"),
        Faroese("fo"),
        French("fr"),
        Galician("gl"),
        Hebrew("he"),
        Hindi("hi"),
        Hungarian("hu"),
        Armenian("hy"),
        Indonesian("id"),
        Icelandic("is"),
        Italian("it"),
        Japanese("ja"),
        Georgian("ka"),
        Kazakh("kk"),
        Korean("ko"),
        Kyrgyz("ky"),
        Lithuanian("lt"),
        Maori("mi"),
        Mongolian("mn"),
        Marathi("mr"),
        Malay("ms"),
        Maltese("mt"),
        Norwegian("nb"),
        Dutch("nl"),
        NorthernSotho("ns"),
        Polish("pl"),
        Pashto("ps"),
        Portuguese("pt"),
        Quechua("qu"),
        Romanian("ro"),
        Russian("ru"),
        Slovak("sk"),
        Albanian("sq"),
        Swedish("sv"),
        Swahili("sw"),
        Tamil("ta"),
        Telugu("te"),
        Tagalog("tl"),
        Tswana("tn"),
        Turkish("tr"),
        Tatar("tt")
    }

    enum class QueryType(val raw: String) {
        /**
         *  Only the last word is interpreted as a prefix (default behavior).
         */
        PrefixLast("prefixLast"),
        /**
         * # All query words are interpreted as prefixes.
         * This option is not recommended, as it tends to yield counterintuitiveresults and has a negative impact
         * on performance.
         */
        PrefixAll("prefixAll"),
        /**
         * No query word is interpreted as a prefix.
         * This option is not recommended, especially in an instant search setup, as the user will have to type
         * the entire word(s) before getting any relevant results.
         */
        PrefixNone("prefixNone")
    }

    enum class RemoveWordIfNoResults(val raw: String) {
        /**
         * No specific processing is done when a query does not return any results (default behavior).
         */
        None("none"),
        /**
         * When a query does not return any results, treat the last word as optional.
         * The process is repeated with words N-1, N-2, etc. until there are results, or the beginning of the query
         * string has been reached.
         */
        LastWords("lastWords"),
        /**
         * When a query does not return any results, treat the first word as optional.
         * The process is repeated with words 2, 3, etc. until there are results, or the end of the query string has
         * been reached.
         */
        FirstWords("firstWords"),
        /**
         * When a query does not return any results, make a second attempt treating all words as optional.
         * This is equivalent to transforming the implicit AND operator applied between query words to an OR.
         */
        AllOptional("allOptional")
    }

    enum class ExactOnSingleWordQuery(val raw: String) {
        /**
         * The exact ranking criterion is set to 1 if the query matches exactly an entire attribute value (default behavior).
         * For example, if you search for the TV show “V”, you want it to match the query “V” before all popular
         * TV shows starting with the letter V.
         */
        Attribute("attribute"),
        /**
         * The exact ranking criterion is ignored on single word queries.
         */
        None("none"),
        /**
         * The exact ranking criterion is set to 1 if the query word is found in the record.
         * The query word must be at least 3 characters long and must not be a stop word in any supported language.
         * For example, if you search for the TV show “Road”, and in your dataset you have 2 records,
         * “Road” and “Road Trip”, both will be considered to match exactly.
         */
        Word("word")
    }

    enum class AlternativesAsExact(val raw: String) {
        /**
         * Alternative words added by the [ignorePlurals] feature.
         */
        IgnorePlurals("ignorePlurals"),
        /**
         * Single-word synonyms (example: “NY” = “NYC”).
         */
        SingleWordSynonym("singleWordSynonym"),
        /**
         * Multiple-words synonyms (example: “NY” = “New York”).
         */
        MultiWordSynonym("multiWordsSynonym")
    }

    enum class ResponseFields(val raw: String) {
        All("*"),
        AroundLatLng("aroundLatLng"),
        AutomaticRadius("automaticRadius"),
        ExhaustiveFacetsCount("exhaustiveFacetsCount"),
        Facets("facets"),
        FacetsStats("facets_stats"),
        Hits("hits"),
        HitsPerPage("hitsPerPage"),
        Index("index"),
        Length("length"),
        NbHits("nbHits"),
        NbPages("nbPages"),
        Offset("offset"),
        Page("page"),
        Params("params"),
        ProcessingTimeMS("processingTimeMS"),
        Query("query"),
        QueryAfterRemoval("queryAfterRemoval"),
        UserData("userData")
    }

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
     * Controls how facet values are sorted.
     * Engine default: [SortFacetValuesBy.Count]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/sortFacetValuesBy/]
     */
    var sortFacetValuesBy: SortFacetValuesBy? = null

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
     * Treats singular, plurals, and other forms of declensions as matching terms.
     * Engine default: false.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/ignorePlurals/]
     */
    var ignorePlurals: BooleanOrISOCodes? = null

    /**
     * Removes stop (common) words from the query before executing it.
     * Engine default: false.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/removeStopWords/]
     */
    var removeStopWords: BooleanOrISOCodes? = null

    /**
     * Sets the languages to be used by language-specific settings and functionalities such as
     * [ignorePlurals], [removeStopWords], and
     * [CJK word-detection][https://www.algolia.com/doc/guides/textual-relevance/languages/#using-a-language-specific-dictionary-for-cjk-words].
     * Engine default: [].
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/queryLanguages/]
     */
    var queryLanguages: List<QueryLanguage>? = null

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
     * Choose which fields the response will contain. Applies to search and browse queries.
     * Engine default: [ResponseFields.All]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/responseFields/]
     */
    var responseFields: List<ResponseFields>? = null

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