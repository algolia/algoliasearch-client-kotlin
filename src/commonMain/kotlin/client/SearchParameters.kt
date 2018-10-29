package client

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer
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

    @Serializer(forClass = SearchParameters::class)
    companion object : KSerializer<SearchParameters> {

        override fun serialize(output: Encoder, obj: SearchParameters) {
            val encoder = output.beginStructure(descriptor)
            // Query
            if (obj.query != null) encoder.encodeStringElement(descriptor, 0, obj.query)
            // Attributes
            if (obj.attributesToRetrieve != null) encoder.encodeSerializableElement(descriptor, 1, StringSerializer.list, obj.attributesToRetrieve)
            if (obj.restrictSearchableAttributes != null) encoder.encodeSerializableElement(descriptor, 2, StringSerializer.list, obj.restrictSearchableAttributes)
            // Filtering
            if (obj.filters != null) encoder.encodeStringElement(descriptor, 3, obj.filters)
            if (obj.facetFilters != null) encoder.encodeSerializableElement(descriptor, 4, StringSerializer.list.list, obj.facetFilters)
            if (obj.optionalFilters != null) encoder.encodeSerializableElement(descriptor, 5, StringSerializer.list, obj.optionalFilters)
            if (obj.numericFilters != null) encoder.encodeSerializableElement(descriptor, 6, StringSerializer.list, obj.numericFilters)
            if (obj.tagFilters != null) encoder.encodeSerializableElement(descriptor, 7, StringSerializer.list, obj.tagFilters)
            if (obj.sumOrFiltersScores != null) encoder.encodeBooleanElement(descriptor, 8, obj.sumOrFiltersScores)
            // Faceting
            if (obj.facets != null) encoder.encodeSerializableElement(descriptor, 9, StringSerializer.list, obj.facets)
            if (obj.maxValuesPerFacet != null) encoder.encodeIntElement(descriptor, 10, obj.maxValuesPerFacet)
            if (obj.facetingAfterDistinct != null) encoder.encodeBooleanElement(descriptor, 11, obj.facetingAfterDistinct)
            if (obj.sortFacetValuesBy != null) encoder.encodeStringElement(descriptor, 12, obj.sortFacetValuesBy)
            // Highlighting
            if (obj.attributesToHighlight != null) encoder.encodeSerializableElement(descriptor, 13, StringSerializer.list, obj.attributesToHighlight)
            if (obj.attributesToSnippet != null) encoder.encodeSerializableElement(descriptor, 14, StringSerializer.list, obj.attributesToSnippet)
            if (obj.highlightPreTag != null) encoder.encodeStringElement(descriptor, 15,  obj.highlightPreTag)
            if (obj.highlightPostTag != null) encoder.encodeStringElement(descriptor, 16,  obj.highlightPostTag)
            if (obj.snippetEllipsisText != null) encoder.encodeStringElement(descriptor, 17,  obj.snippetEllipsisText)
            if (obj.restrictHighlightAndSnippetArrays != null) encoder.encodeBooleanElement(descriptor, 18, obj.restrictHighlightAndSnippetArrays)
            // Pagination
            if (obj.page != null) encoder.encodeIntElement(descriptor, 19, obj.page)
            if (obj.hitsPerPage != null) encoder.encodeIntElement(descriptor, 20, obj.hitsPerPage)
            if (obj.offset != null) encoder.encodeIntElement(descriptor, 21, obj.offset)
            if (obj.length != null) encoder.encodeIntElement(descriptor, 22, obj.length)
            // Typos
            if (obj.minWordSizefor1Typo != null) encoder.encodeIntElement(descriptor, 23, obj.minWordSizefor1Typo)
            if (obj.minWordSizefor2Typos != null) encoder.encodeIntElement(descriptor, 24, obj.minWordSizefor2Typos)
            if (obj.typoTolerance != null) encoder.encodeStringElement(descriptor, 25, obj.typoTolerance)
            if (obj.allowTyposOnNumericTokens != null) encoder.encodeBooleanElement(descriptor, 26, obj.allowTyposOnNumericTokens)
            if (obj.disableTypoToleranceOnAttributes != null) encoder.encodeSerializableElement(descriptor, 27, StringSerializer.list, obj.disableTypoToleranceOnAttributes)
            encoder.endStructure(descriptor)
        }
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