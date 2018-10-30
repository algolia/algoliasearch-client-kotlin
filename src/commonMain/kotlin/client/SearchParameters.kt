package client

import kotlinx.serialization.*
import kotlinx.serialization.json.JSON
import kotlin.reflect.KProperty


@Serializable
data class SearchParameters(
    // Query
    @Optional val query: String? = null,
    // Attributes
    @Optional val attributesToRetrieve: List<String>? = null,
    @Optional val restrictSearchableAttributes: List<String>? = null,
    // Filtering
    @Optional val filters: String? = null,
    @Optional val facetFilters: List<List<String>>? = null,
    @Optional val optionalFilters: List<String>? = null,
    @Optional val numericFilters: List<String>? = null,
    @Optional val tagFilters: List<String>? = null,
    @Optional val sumOrFiltersScores: Boolean? = null,
    // Faceting
    @Optional val facets: List<String>? = null,
    @Optional val maxValuesPerFacet: Int? = null,
    @Optional val facetingAfterDistinct: Boolean? = null,
    @Optional val sortFacetValuesBy: String? = null,
    // Highlighting
    @Optional val attributesToHighlight: List<String>? = null,
    @Optional val attributesToSnippet: List<String>? = null,
    @Optional val highlightPreTag: String? = null,
    @Optional val highlightPostTag: String? = null,
    @Optional val snippetEllipsisText: String? = null,
    @Optional val restrictHighlightAndSnippetArrays: Boolean? = null,
    // Pagination
    @Optional val page: Int? = null,
    @Optional val hitsPerPage: Int? = null,
    @Optional val offset: Int? = null,
    @Optional val length: Int? = null,
    // Typos
    @Optional val minWordSizefor1Typo: Int? = null,
    @Optional val minWordSizefor2Typos: Int? = null,
    @Optional val typoTolerance: String? = null,
    @Optional val allowTyposOnNumericTokens: Boolean? = null,
    @Optional val disableTypoToleranceOnAttributes: List<String>? = null
    // Geo-Search
    // Languages
    // Query-rules
    // Query-Strategy
    // Advanced
//    @Optional val analytics: Boolean = false
) {

    fun stringify(): String {
        val map = mutableMapOf<String, String>()

        this::class.members.filterIsInstance<KProperty<*>>().forEach { property ->
            property.call(this)?.let {
                map[property.name] = it.toString()
            }
        }
        return JSON.stringify(map)
    }
}