package client

import kotlinx.serialization.Serializable


@Serializable
data class SearchParameters(
    // Query
    val query: String = "",
    // Attributes
    val attributesToRetrieve: List<String> = listOf(),
    val restrictSearchableAttributes: List<String> = listOf(),
    // Filtering
    val filters: String = "",
    val facetFilters: List<List<String>> = listOf(),
    val optionalFilters: List<String> = listOf(),
    val numericFilters: List<String> = listOf(),
    val tagFilters: List<String> = listOf(),
    val sumOrFiltersScores: Boolean = false,
//    // Faceting
    val facets: List<String> = listOf(),
    val maxValuesPerFacet: Int = 100,
    val facetingAfterDistinct: Boolean = false,
    val sortFacetValuesBy: String = "count",
//    // Highlighting
    val attributesToHighlight: List<String> = listOf(),
    val attributesToSnippet: List<String> = listOf(),
    val highlightPreTag: String = "",
    val highlightPostTag: String = "",
    val snippetEllipsisText: String = "",
    val restrictHighlightAndSnippetArrays: Boolean = true,
//    // Pagination
    val page: Int = 0,
    val hitsPerPage: Int = 10,
    val offset: Int = 0,
    val length: Int = 5,
//    // Typos
    val minWordSizefor1Typo: Int = 2,
    val minWordSizefor2Typos: Int = 2,
    val typoTolerance: String = "true",
    val allowTyposOnNumericTokens: Boolean = false,
    val disableTypoToleranceOnAttributes: List<String> = listOf()
    // Geo-Search
    // Languages
    // Query-rules
    // Query-Strategy
    // Advanced
//    val analytics: Boolean = false
)