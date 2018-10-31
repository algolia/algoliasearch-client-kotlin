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
    @Optional val disableTypoToleranceOnAttributes: List<String>? = null,
    // Geo-Search
    @Optional val aroundLatLng: String? = null,
    @Optional val aroundLatLngViaIP: Boolean? = null,
    @Optional val aroundRadius: AroundRadius? = null,
    @Optional val aroundPrecision: Int? = null,
    @Optional val minimumAroundRadius: Int? = null,
    @Optional val insideBoundingBox: List<Float>? = null,
    @Optional val insidePolygon: List<Float>? = null,
    // Languages
    @Optional val ignorePlurals: BooleanOrISOCodes? = null,
    @Optional val removeStopWords: BooleanOrISOCodes? = null,
    @Optional val queryLanguages: List<String>? = null,
    // Query-rules
    @Optional val enableRules: Boolean? = null,
    @Optional val ruleContexts: List<String>? = null,
    // Query-Strategy
    @Optional val queryType: String? = null,
    @Optional val removeWordsIfNoResults: String? = null,
    @Optional val advancedSyntax: Boolean? = null,
    @Optional val optionalWords: List<String>? = null,
    @Optional val disableExactOnAttributes: List<String>? = null,
    @Optional val exactOnSingleWordQuery: String? = null,
    @Optional val alternativesAsExact: List<String>? = null,
    // Advanced
    @Optional val distinct: Int? = null,
    @Optional val getRankingInfo: Boolean? = null,
    @Optional val clickAnalytics: Boolean? = null,
    @Optional val analytics: Boolean? = null,
    @Optional val analyticsTags: Boolean? = null,
    @Optional val synonyms: Boolean? = null,
    @Optional val replaceSynonymsInHighlight: Boolean? = null,
    @Optional val minProximity: Int? = null,
    @Optional val responseFields: List<String>? = null,
    @Optional val maxFacetHits: Int? = null,
    @Optional val percentileComputation: Boolean? = null
) {

    sealed class AroundRadius {

        object All : AroundRadius() {

            const val parameter = "all"
        }

        data class InMeters(val int: kotlin.Int) : AroundRadius()
    }

    sealed class BooleanOrISOCodes {

        class Boolean(val boolean: kotlin.Boolean) : BooleanOrISOCodes()

        class ISOCodes(val codes: List<String>) : BooleanOrISOCodes()
    }

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