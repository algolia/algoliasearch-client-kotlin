package com.algolia.search.model.response

import com.algolia.search.ExperimentalAlgoliaClientAPI
import com.algolia.search.endpoint.EndpointSearch
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.QueryID
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.filter.FilterGroup
import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.model.rule.RenderingContent
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.search.Explain
import com.algolia.search.model.search.Facet
import com.algolia.search.model.search.FacetStats
import com.algolia.search.model.search.Point
import com.algolia.search.model.search.Query
import com.algolia.search.model.search.RankingInfo
import com.algolia.search.model.search.RemoveWordIfNoResults
import com.algolia.search.model.settings.Settings
import com.algolia.search.serialize.KSerializerFacetMap
import com.algolia.search.serialize.KSerializerPoint
import com.algolia.search.serialize.internal.Json
import com.algolia.search.serialize.internal.JsonNonStrict
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import com.algolia.search.serialize.internal.jsonObjectOrNull
import com.algolia.search.serialize.internal.jsonPrimitiveOrNull
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.floatOrNull
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject

@Serializable
public data class ResponseSearch(
    /**
     * The hits returned by the search. Hits are ordered according to the ranking or sorting of the index being queried.
     * Hits are made of the schemaless JSON objects that you stored in the index.
     */
    @SerialName(Key.Hits) val hitsOrNull: List<Hit>? = null,
    /**
     * The number of hits matched by the query.
     */
    @SerialName(Key.NbHits) val nbHitsOrNull: Int? = null,
    /**
     * Index of the current page (zero-based). See the [Query.page] search parameter.
     * Not returned if you use offset/length for pagination.
     */
    @SerialName(Key.Page) val pageOrNull: Int? = null,
    /**
     * The maximum number of hits returned per page. See the [Query.hitsPerPage] search parameter.
     * Not returned if you use offset & length for pagination.
     */
    @SerialName(Key.HitsPerPage) val hitsPerPageOrNull: Int? = null,
    /**
     * Alternative to [page] (zero-based). Is returned only when [Query.offset] [Query.length] is specified.
     */
    @SerialName(Key.Offset) val offsetOrNull: Int? = null,
    /**
     * Alternative to [hitsPerPageOrNull] (zero-based). Is returned only when [Query.offset] [Query.length] is specified.
     */
    @SerialName(Key.Length) val lengthOrNull: Int? = null,
    /**
     * Array of userData object. Only returned if at least one query rule containing a custom userData
     * consequence was applied.
     */
    @SerialName(Key.UserData) val userDataOrNull: List<JsonObject>? = null,
    /**
     * The number of returned pages. Calculation is based on the total number of hits (nbHits) divided by the number of
     * hits per page (hitsPerPage), rounded up to the nearest integer.
     * Not returned if you use offset & length for pagination.
     */
    @SerialName(Key.NbPages) val nbPagesOrNull: Int? = null,
    /**
     * Time the server took to process the request, in milliseconds. This does not include network time.
     */
    @SerialName(Key.ProcessingTimeMS) val processingTimeMSOrNull: Long? = null,
    /**
     * Whether the nbHits is exhaustive (true) or approximate (false). An approximation is done when the query takes
     * more than 50ms to be processed (this can happen when using complex filters on millions on records).
     * See the related [discussion][https://www.algolia.com/doc/faq/index-configuration/my-facet-and-hit-counts-are-not-accurate/]
     */
    @SerialName(Key.ExhaustiveNbHits) val exhaustiveNbHitsOrNull: Boolean? = null,
    /**
     * Whether the facet count is exhaustive (true) or approximate (false).
     * See the related [discussion][https://www.algolia.com/doc/faq/index-configuration/my-facet-and-hit-counts-are-not-accurate/].
     */
    @SerialName(Key.ExhaustiveFacetsCount) val exhaustiveFacetsCountOrNull: Boolean? = null,
    /**
     * An echo of the query text. See the [Query.query] search parameter.
     */
    @SerialName(Key.Query) val queryOrNull: String? = null,
    /**
     * A markup text indicating which parts of the original query have been removed in order to retrieve a non-empty
     * result set.
     * The removed parts are surrounded by <em> tags.
     * Only returned when [Query.removeWordsIfNoResults] or [Settings.removeWordsIfNoResults] is set to
     * [RemoveWordIfNoResults.LastWords] or [RemoveWordIfNoResults.FirstWords].
     */
    @SerialName(Key.QueryAfterRemoval) val queryAfterRemovalOrNull: String? = null,
    /**
     * An url-encoded string of all [Query] parameters.
     */
    @SerialName(Key.Params) val paramsOrNull: String? = null,
    /**
     * Used to return warnings about the query.
     */
    @SerialName(Key.Message) val messageOrNull: String? = null,
    /**
     * The computed geo location.
     * Only returned when [Query.aroundLatLngViaIP] or [Query.aroundLatLng] is set.
     */
    @SerialName(Key.AroundLatLng) @Serializable(KSerializerPoint::class) val aroundLatLngOrNull: Point? = null,
    /**
     * The automatically computed radius. For legacy reasons, this parameter is a string and not an integer.
     * Only returned for geo queries without an explicitly specified [Query.aroundRadius].
     */
    @SerialName(Key.AutomaticRadius) val automaticRadiusOrNull: Float? = null,
    /**
     * Actual host name of the server that processed the request. Our DNS supports automatic failover and load
     * balancing, so this may differ from the host name used in the request.
     * Returned only if [Query.getRankingInfo] is set to true.
     */
    @SerialName(Key.ServerUsed) val serverUsedOrNull: String? = null,
    /**
     * Index name used for the query. In case of A/B test, the index targeted isn’t always the index used by the query.
     * Returned only if [Query.getRankingInfo] is set to true.
     */
    @SerialName(Key.IndexUsed) val indexUsedOrNull: IndexName? = null,
    /**
     * In case of A/B test, reports the variant ID used. The variant ID is the position in the array of variants
     * (starting at 1).
     * Returned only if [Query.getRankingInfo] is set to true.
     */
    @SerialName(Key.AbTestVariantID) val abTestVariantIDOrNull: Int? = null,
    /**
     * The query string that will be searched, after
     * [normalization][https://www.algolia.com/doc/guides/managing-results/optimize-search-results/handling-natural-languages-nlp/in-depth/normalization/#what-is-normalization].
     * Normalization includes removing stop words (if [Query.removeStopWords] or [Settings.removeStopWords] is enabled),
     * and transforming portions of the query string into phrase queries
     * (see [Query.advancedSyntax] or [Settings.advancedSyntax]).
     * Returned only if [Query.getRankingInfo] is set to true.
     */
    @SerialName(Key.ParsedQuery) val parsedQueryOrNull: String? = null,
    /**
     * A mapping of each facet name to the corresponding facet counts.
     * Returned only if [Query.facets] is non-empty.
     */
    @SerialName(Key.Facets) @Serializable(KSerializerFacetMap::class) val facetsOrNull: Map<Attribute, List<Facet>>? = null,
    /**
     * A mapping of each facet name to the corresponding facet counts for disjunctive facets.
     * Returned only by the [EndpointSearch.advancedSearch] method.
     * [Documentation][https://www.algolia.com/doc/guides/building-search-ui/going-further/backend-search/how-to/faceting/?language=kotlin#conjunctive-and-disjunctive-faceting]
     */
    @SerialName(Key.DisjunctiveFacets) @Serializable(KSerializerFacetMap::class) val disjunctiveFacetsOrNull: Map<Attribute, List<Facet>>? = null,
    /**
     * Statistics for numerical facets.
     * Returned only if [Query.facets] is non-empty and at least one of the returned facets contains numerical values.
     */
    @SerialName(Key.Facets_Stats) val facetStatsOrNull: Map<Attribute, FacetStats>? = null,
    /**
     * Returned only by the [EndpointSearch.browse] method.
     */
    @SerialName(Key.Cursor) val cursorOrNull: Cursor? = null,
    /**
     * Index name used for the query. In case of A/B test, the index targeted isn’t always the index used by the query.
     * Returned only if [Query.getRankingInfo] is set to true.
     */
    @SerialName(Key.Index) val indexNameOrNull: IndexName? = null,
    @SerialName(Key.Processed) val processedOrNull: Boolean? = null,
    /**
     * Identifies the query uniquely. Can be used by [InsightsEvent].
     */
    @SerialName(Key.QueryID) val queryIDOrNull: QueryID? = null,
    /**
     * A mapping of each facet name to the corresponding facet counts for hierarchical facets.
     * Returned only by the [EndpointSearch.advancedSearch] method, only if a [FilterGroup.And.Hierarchical] is used.
     */
    @SerialName(Key.HierarchicalFacets) val hierarchicalFacetsOrNull: Map<Attribute, List<Facet>>? = null,
    /**
     * Meta-information as to how the query was processed.
     */
    @SerialName(Key.Explain) val explainOrNull: Explain? = null,
    /**
     * The rules applied to the query.
     */
    @SerialName(Key.AppliedRules) val appliedRulesOrNull: List<JsonObject>? = null,
    /**
     * Applied relevancy score in the virtual index [0-100].
     */
    @SerialName(Key.AppliedRelevancyStrictness) val appliedRelevancyStrictnessOrNull: Int? = null,
    /**
     * Number of relevant hits to display in case of non-zero `relevancyStrictness` applied.
     */
    @SerialName(Key.NbSortedHits) val nbSortedHitsOrNull: Int? = null,
    /**
     * Content defining how the search interface should be rendered.
     */
    @SerialName(Key.RenderingContent) val renderingContentOrNull: RenderingContent? = null,

    /**
     * In case of A/B test, reports the ID of the A/B test used.
     * Returned only if [Query.getRankingInfo] is set to true.
     */
    @SerialName(Key.ABTestID) val abTestIDOrNull: ABTestID? = null
) : ResultSearch {

    /**
     * The hits returned by the search. Hits are ordered according to the ranking or sorting of the index being queried.
     * Hits are made of the schemaless JSON objects that you stored in the index.
     *
     * @throws IllegalStateException if [hitsOrNull] is null
     */
    public val hits: List<Hit>
        get() = checkNotNull(hitsOrNull)

    /**
     * The number of hits matched by the query.
     *
     * @@throws IllegalStateException if [nbHitsOrNull] is null
     */
    public val nbHits: Int
        get() = checkNotNull(nbHitsOrNull)

    /**
     * Index of the current page (zero-based). See the [Query.page] search parameter.
     * Not returned if you use offset/length for pagination.
     *
     * @throws IllegalStateException if [pageOrNull] is null
     */
    public val page: Int
        get() = checkNotNull(pageOrNull)

    /**
     * The maximum number of hits returned per page. See the [Query.hitsPerPage] search parameter.
     * Not returned if you use offset & length for pagination.
     *
     * @throws IllegalStateException if [hitsPerPage] is null
     */
    public val hitsPerPage: Int
        get() = checkNotNull(hitsPerPageOrNull)

    /**
     * Alternative to [hitsPerPageOrNull] (zero-based). Is returned only when [Query.offset] [Query.length] is specified.
     *
     * @throws IllegalStateException if [lengthOrNull] is null
     */
    public val length: Int
        get() = checkNotNull(lengthOrNull)

    /**
     * Alternative to [page] (zero-based). Is returned only when [Query.offset] [Query.length] is specified.
     *
     * @throws IllegalStateException if [offsetOrNull] is null
     */
    public val offset: Int
        get() = checkNotNull(offsetOrNull)

    /**
     * Array of userData object. Only returned if at least one query rule containing a custom userData
     * consequence was applied.
     *
     * @throws IllegalStateException if [userDataOrNull] is null
     */
    public val userData: List<JsonObject>
        get() = checkNotNull(userDataOrNull)

    /**
     * The number of returned pages. Calculation is based on the total number of hits (nbHits) divided by the number of
     * hits per page (hitsPerPage), rounded up to the nearest integer.
     * Not returned if you use offset & length for pagination.
     *
     * @throws IllegalStateException if [nbPagesOrNull] is null
     */
    public val nbPages: Int
        get() = checkNotNull(nbPagesOrNull)

    /**
     * Time the server took to process the request, in milliseconds. This does not include network time.
     *
     * @throws IllegalStateException if [processingTimeMSOrNull] is null
     */
    public val processingTimeMS: Long
        get() = checkNotNull(processingTimeMSOrNull)

    /**
     * Whether the nbHits is exhaustive (true) or approximate (false). An approximation is done when the query takes
     * more than 50ms to be processed (this can happen when using complex filters on millions on records).
     * See the related [discussion][https://www.algolia.com/doc/faq/index-configuration/my-facet-and-hit-counts-are-not-accurate/]
     *
     * @throws IllegalStateException if [exhaustiveNbHitsOrNull] is null
     */
    public val exhaustiveNbHits: Boolean
        get() = checkNotNull(exhaustiveNbHitsOrNull)

    /**
     * Whether the facet count is exhaustive (true) or approximate (false).
     * See the related [discussion][https://www.algolia.com/doc/faq/index-configuration/my-facet-and-hit-counts-are-not-accurate/].
     *
     * @throws IllegalStateException if [exhaustiveFacetsCountOrNull] is null
     */
    public val exhaustiveFacetsCount: Boolean
        get() = checkNotNull(exhaustiveFacetsCountOrNull)

    /**
     * An echo of the query text. See the [Query.query] search parameter.
     *
     * @throws IllegalStateException if [queryOrNull] is null
     */
    public val query: String
        get() = checkNotNull(queryOrNull)

    /**
     * A markup text indicating which parts of the original query have been removed in order to retrieve a non-empty
     * result set.
     * The removed parts are surrounded by <em> tags.
     * Only returned when [Query.removeWordsIfNoResults] or [Settings.removeWordsIfNoResults] is set to
     * [RemoveWordIfNoResults.LastWords] or [RemoveWordIfNoResults.FirstWords].
     *
     * @throws IllegalStateException if [queryAfterRemovalOrNull] is null
     */
    public val queryAfterRemoval: String
        get() = checkNotNull(queryAfterRemovalOrNull)

    /**
     * An url-encoded string of all [Query] parameters.
     *
     * @throws IllegalStateException if [paramsOrNull] is null
     */
    public val params: String
        get() = checkNotNull(paramsOrNull)

    /**
     * Used to return warnings about the query.
     *
     * @throws IllegalStateException if [messageOrNull] is null
     */
    public val message: String
        get() = checkNotNull(messageOrNull)

    /**
     * The computed geo location.
     * Only returned when [Query.aroundLatLngViaIP] or [Query.aroundLatLng] is set.
     *
     * @throws IllegalStateException if [aroundLatLngOrNull] is null
     */
    public val aroundLatLng: Point
        get() = checkNotNull(aroundLatLngOrNull)

    /**
     * The automatically computed radius. For legacy reasons, this parameter is a string and not an integer.
     * Only returned for geo queries without an explicitly specified [Query.aroundRadius].
     *
     * @throws IllegalStateException if [automaticRadiusOrNull] is null
     */
    public val automaticRadius: Float
        get() = checkNotNull(automaticRadiusOrNull)

    /**
     * Actual host name of the server that processed the request. Our DNS supports automatic failover and load
     * balancing, so this may differ from the host name used in the request.
     * Returned only if [Query.getRankingInfo] is set to true.
     *
     * @throws IllegalStateException if [serverUsedOrNull] is null
     */
    public val serverUsed: String
        get() = checkNotNull(serverUsedOrNull)

    /**
     * Index name used for the query. In case of A/B test, the index targeted isn’t always the index used by the query.
     * Returned only if [Query.getRankingInfo] is set to true.
     *
     * @throws IllegalStateException if [indexUsedOrNull] is null
     */
    public val indexUsed: IndexName
        get() = checkNotNull(indexUsedOrNull)

    /**
     * In case of A/B test, reports the variant ID used. The variant ID is the position in the array of variants
     * (starting at 1).
     * Returned only if [Query.getRankingInfo] is set to true.
     *
     * @throws IllegalStateException if [abTestVariantIDOrNull] is null
     */
    public val abTestVariantID: Int
        get() = checkNotNull(abTestVariantIDOrNull)

    /**
     * The query string that will be searched, after
     * [normalization][https://www.algolia.com/doc/guides/managing-results/optimize-search-results/handling-natural-languages-nlp/in-depth/normalization/#what-is-normalization].
     * Normalization includes removing stop words (if [Query.removeStopWords] or [Settings.removeStopWords] is enabled),
     * and transforming portions of the query string into phrase queries
     * (see [Query.advancedSyntax] or [Settings.advancedSyntax]).
     * Returned only if [Query.getRankingInfo] is set to true.
     *
     * @throws IllegalStateException if [parsedQueryOrNull] is null
     */
    public val parsedQuery: String
        get() = checkNotNull(parsedQueryOrNull)

    /**
     * A mapping of each facet name to the corresponding facet counts.
     * Returned only if [Query.facets] is non-empty.
     *
     * @throws IllegalStateException if [facetsOrNull] is null
     */
    public val facets: Map<Attribute, List<Facet>>
        get() = checkNotNull(facetsOrNull)

    /**
     * A mapping of each facet name to the corresponding facet counts for disjunctive facets.
     * Returned only by the [EndpointSearch.advancedSearch] method.
     * [Documentation][https://www.algolia.com/doc/guides/building-search-ui/going-further/backend-search/how-to/faceting/?language=kotlin#conjunctive-and-disjunctive-faceting]
     *
     * @throws IllegalStateException if [disjunctiveFacetsOrNull] is null
     */
    public val disjunctiveFacets: Map<Attribute, List<Facet>>
        get() = checkNotNull(disjunctiveFacetsOrNull)

    /**
     * Statistics for numerical facets.
     * Returned only if [Query.facets] is non-empty and at least one of the returned facets contains numerical values.
     *
     * @throws IllegalStateException if [facetStatsOrNull] is null
     */
    public val facetStats: Map<Attribute, FacetStats>
        get() = checkNotNull(facetStatsOrNull)

    /**
     * Returned only by the [EndpointSearch.browse] method.
     *
     * @throws IllegalStateException if [cursorOrNull] is null
     */
    public val cursor: Cursor
        get() = checkNotNull(cursorOrNull)

    /**
     * Index name used for the query. In case of A/B test, the index targeted isn’t always the index used by the query.
     * Returned only if [Query.getRankingInfo] is set to true.
     *
     * @throws IllegalStateException if [indexNameOrNull] is null
     */
    public val indexName: IndexName
        get() = checkNotNull(indexNameOrNull)

    public val processed: Boolean
        get() = checkNotNull(processedOrNull)

    /**
     * Identifies the query uniquely. Can be used by [InsightsEvent].
     *
     * @throws IllegalStateException if [queryIDOrNull] is null
     */
    public val queryID: QueryID
        get() = checkNotNull(queryIDOrNull)

    /**
     * A mapping of each facet name to the corresponding facet counts for hierarchical facets.
     * Returned only by the [EndpointSearch.advancedSearch] method, only if a [FilterGroup.And.Hierarchical] is used.
     *
     * @throws IllegalStateException if [hierarchicalFacetsOrNull] is null
     */
    public val hierarchicalFacets: Map<Attribute, List<Facet>>
        get() = checkNotNull(hierarchicalFacetsOrNull)

    /**
     * Meta-information as to how the query was processed.
     *
     * @throws IllegalStateException if [explainOrNull] is null
     */
    public val explain: Explain
        get() = checkNotNull(explainOrNull)

    /**
     * The rules applied to the query.
     *
     * @throws IllegalStateException if [appliedRulesOrNull] is null
     */
    public val appliedRules: List<JsonObject>
        get() = checkNotNull(appliedRulesOrNull)

    /**
     * Applied relevancy score in the virtual index [0-100].
     *
     * @throws IllegalStateException if [appliedRelevancyStrictnessOrNull] is null
     */
    public val appliedRelevancyStrictness: Int
        get() = requireNotNull(appliedRelevancyStrictnessOrNull)

    /**
     * Number of relevant hits to display in case of non-zero `relevancyStrictness` applied.
     *
     * @throws IllegalStateException if [nbSortedHitsOrNull] is null
     */
    public val nbSortedHits: Int
        get() = requireNotNull(nbSortedHitsOrNull)

    /**
     * Content defining how the search interface should be rendered.
     *
     * @throws IllegalStateException if [renderingContentOrNull] is null
     */
    public val renderingContent: RenderingContent
        get() = requireNotNull(renderingContentOrNull)

    /**
     * In case of A/B test, reports the ID of the A/B test used.
     * Returned only if [Query.getRankingInfo] is set to true.
     *
     * @throws IllegalStateException if [abTestIDOrNull] is null.
     */
    public val abTestID: ABTestID
        get() = checkNotNull(abTestIDOrNull)

    /**
     * Returns the position (0-based) within the [hits] result list of the record matching against the given [objectID].
     * If the [objectID] is not found, -1 is returned.
     */
    public fun getObjectPosition(objectID: ObjectID): Int {
        return hits.indexOfFirst { it.json["objectID"]?.jsonPrimitiveOrNull?.content == objectID.raw }
    }

    /**
     * A Hit returned by the search.
     */
    @Serializable(Hit.Companion::class)
    public data class Hit(
        val json: JsonObject,
    ) : Map<String, JsonElement> by json {

        public val distinctSeqIDOrNull: Int? = json[Key._DistinctSeqID]?.jsonPrimitiveOrNull?.int

        public val rankingInfoOrNull: RankingInfo? = json[Key._RankingInfo]?.jsonObjectOrNull?.let {
            JsonNonStrict.decodeFromJsonElement(RankingInfo.serializer(), it)
        }

        public val highlightResultOrNull: JsonObject? = json[Key._HighlightResult]?.jsonObjectOrNull

        public val snippetResultOrNull: JsonObject? = json[Key._SnippetResult]?.jsonObjectOrNull

        @ExperimentalAlgoliaClientAPI
        public val answerOrNull: Answer? = json[Key._Answer]?.jsonObjectOrNull?.let {
            JsonNonStrict.decodeFromJsonElement(Answer.serializer(), it)
        }

        public val scoreOrNull: Float? = json[Key._Score]?.jsonPrimitiveOrNull?.floatOrNull

        public val rankingInfo: RankingInfo
            get() = checkNotNull(rankingInfoOrNull)

        public val distinctSeqID: Int
            get() = checkNotNull(distinctSeqIDOrNull)

        public val highlightResult: JsonObject
            get() = checkNotNull(highlightResultOrNull)

        public val snippetResult: JsonObject
            get() = checkNotNull(snippetResultOrNull)

        @ExperimentalAlgoliaClientAPI
        public val answer: Answer
            get() = checkNotNull(answerOrNull)

        public val score: Float
            get() = checkNotNull(scoreOrNull)

        /**
         * Deserialize the value of an [Attribute] to [T].
         */
        public fun <T> getValue(serializer: KSerializer<T>, attribute: Attribute): T {
            return Json.decodeFromJsonElement(serializer, json.getValue(attribute.raw))
        }

        /**
         * Deserialize the entire [json] to [T].
         */
        public fun <T> deserialize(deserializer: DeserializationStrategy<T>): T {
            return JsonNonStrict.decodeFromJsonElement(deserializer, json)
        }

        @OptIn(ExperimentalSerializationApi::class)
        @Serializer(Hit::class)
        public companion object : KSerializer<Hit> {

            override fun deserialize(decoder: Decoder): Hit {
                return Hit(decoder.asJsonInput().jsonObject)
            }

            override fun serialize(encoder: Encoder, value: Hit) {
                encoder.asJsonOutput().encodeJsonElement(value.json)
            }
        }
    }

    @Serializable
    public data class Answer(
        @SerialName(Key.Extract) val extract: String,
        @SerialName(Key.Score) val score: Double,
        @SerialName(Key.ExtractAttribute) val extractAttribute: Attribute,
    )
}
