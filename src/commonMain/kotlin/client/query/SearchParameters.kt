package client.query

import kotlinx.serialization.*
import kotlinx.serialization.internal.FloatSerializer
import kotlinx.serialization.internal.StringSerializer


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
    @Optional val optionalFilters: List<List<String>>? = null,
    @Optional val numericFilters: List<List<String>>? = null,
    @Optional val tagFilters: List<List<String>>? = null,
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

    @Serializer(forClass = SearchParameters::class)
    companion object : KSerializer<SearchParameters> {

        private fun CompositeEncoder.encodeString(property: String?, index: Int) {
            if (property != null) encodeStringElement(descriptor, index, property)
        }

        private fun CompositeEncoder.encodeInt(property: Int?, index: Int) {
            if (property != null) encodeIntElement(descriptor, index, property)
        }

        private fun CompositeEncoder.encodeBoolean(property: Boolean?, index: Int) {
            if (property != null) encodeBooleanElement(descriptor, index, property)
        }

        private fun <T> CompositeEncoder.encodeSerializable(property: T?, index: Int, saver: SerializationStrategy<T>) {
            if (property != null) encodeSerializableElement(descriptor, index, saver, property)
        }

        override fun serialize(output: Encoder, obj: SearchParameters) {
            val encoder = output.beginStructure(descriptor)

            // Query
            encoder.encodeString(obj.query, 0)
            // Attributes
            encoder.encodeSerializable(obj.attributesToRetrieve, 1, StringSerializer.list)
            encoder.encodeSerializable(obj.restrictSearchableAttributes, 2, StringSerializer.list)
            // Filtering
            encoder.encodeString(obj.filters, 3)
            encoder.encodeSerializable(obj.facetFilters, 4, StringSerializer.list.list)
            encoder.encodeSerializable(obj.optionalFilters, 5, StringSerializer.list.list)
            encoder.encodeSerializable(obj.numericFilters, 6, StringSerializer.list.list)
            encoder.encodeSerializable(obj.tagFilters, 7, StringSerializer.list.list)
            encoder.encodeBoolean(obj.sumOrFiltersScores, 8)
            // Faceting
            encoder.encodeSerializable(obj.facets, 9, StringSerializer.list)
            encoder.encodeInt(obj.maxValuesPerFacet, 10)
            encoder.encodeBoolean(obj.facetingAfterDistinct, 11)
            encoder.encodeString(obj.sortFacetValuesBy, 12)
            // Highlighting

            encoder.encodeSerializable(obj.attributesToHighlight, 13, StringSerializer.list)
            encoder.encodeSerializable(obj.attributesToSnippet, 14, StringSerializer.list)
            encoder.encodeString(obj.highlightPreTag, 15)
            encoder.encodeString(obj.highlightPostTag, 16)
            encoder.encodeString(obj.snippetEllipsisText, 17)
            encoder.encodeBoolean(obj.restrictHighlightAndSnippetArrays, 18)
            // Pagination
            encoder.encodeInt(obj.page, 19)
            encoder.encodeInt(obj.hitsPerPage, 20)
            encoder.encodeInt(obj.offset, 21)
            encoder.encodeInt(obj.length, 22)
            // Typos
            encoder.encodeInt(obj.minWordSizefor1Typo, 23)
            encoder.encodeInt(obj.minWordSizefor2Typos, 24)
            encoder.encodeString(obj.typoTolerance, 25)
            encoder.encodeBoolean(obj.allowTyposOnNumericTokens, 26)
            encoder.encodeSerializable(obj.disableTypoToleranceOnAttributes, 27, StringSerializer.list)
            // Geo-search
            encoder.encodeString(obj.aroundLatLng, 28)
            encoder.encodeBoolean(obj.aroundLatLngViaIP, 29)
            encoder.encodeSerializable(obj.aroundRadius, 30, AroundRadius.serializer())
            encoder.encodeInt(obj.aroundPrecision, 31)
            encoder.encodeInt(obj.minimumAroundRadius, 32)
            encoder.encodeSerializable(obj.insideBoundingBox, 33, FloatSerializer.list)
            encoder.encodeSerializable(obj.insidePolygon, 34, FloatSerializer.list)
            // Languages
            encoder.encodeSerializable(obj.ignorePlurals, 35, BooleanOrISOCodes.serializer())
            encoder.encodeSerializable(obj.removeStopWords, 36, BooleanOrISOCodes.serializer())
            encoder.encodeSerializable(obj.queryLanguages, 37, StringSerializer.list)
            // Query-rules
            encoder.encodeBoolean(obj.enableRules, 38)
            encoder.encodeSerializable(obj.ruleContexts, 39, StringSerializer.list)
            // Query-Strategy
            encoder.encodeString(obj.queryType, 40)
            encoder.encodeString(obj.removeWordsIfNoResults, 41)
            encoder.encodeBoolean(obj.advancedSyntax, 42)
            encoder.encodeSerializable(obj.optionalWords, 43, StringSerializer.list)
            encoder.encodeSerializable(obj.disableExactOnAttributes, 44, StringSerializer.list)
            encoder.encodeString(obj.exactOnSingleWordQuery, 45)
            encoder.encodeSerializable(obj.alternativesAsExact, 46, StringSerializer.list)
            // Advanced
            encoder.encodeInt(obj.distinct, 47)
            encoder.encodeBoolean(obj.getRankingInfo, 48)
            encoder.encodeBoolean(obj.clickAnalytics, 49)
            encoder.encodeBoolean(obj.analytics, 50)
            encoder.encodeBoolean(obj.analyticsTags, 51)
            encoder.encodeBoolean(obj.synonyms, 52)
            encoder.encodeBoolean(obj.replaceSynonymsInHighlight, 53)
            encoder.encodeInt(obj.minProximity, 54)
            encoder.encodeSerializable(obj.responseFields, 55, StringSerializer.list)
            encoder.encodeInt(obj.maxFacetHits, 56)
            encoder.encodeBoolean(obj.percentileComputation, 57)

            encoder.endStructure(descriptor)
        }
    }
}