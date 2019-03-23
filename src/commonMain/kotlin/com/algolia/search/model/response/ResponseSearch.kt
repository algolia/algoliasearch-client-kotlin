package com.algolia.search.model.response

import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.QueryID
import com.algolia.search.model.search.*
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject


@Serializable
public data class ResponseSearch(
    @SerialName(KeyHits) val hitsOrNull: List<Hit>? = null,
    @SerialName(KeyNbHits) val nbHitsOrNull: Int? = null,
    @SerialName(KeyPage) val pageOrNull: Int? = null,
    @SerialName(KeyHitsPerPage) val hitsPerPageOrNull: Int? = null,
    @SerialName(KeyUserData) val userDataOrNull: List<JsonObject>? = null,
    @SerialName(KeyNbPages) val nbPagesOrNull: Int? = null,
    @SerialName(KeyProcessingTimeMS) val processingTimeMSOrNull: Long? = null,
    @SerialName(KeyExhaustiveNbHits) val exhaustiveNbHitsOrNull: Boolean? = null,
    @SerialName(KeyExhaustiveFacetsCount) val exhaustiveFacetsCountOrNull: Boolean? = null,
    @SerialName(KeyQuery) val queryOrNull: String? = null,
    @SerialName(KeyQueryAfterRemoval) val queryAfterRemovalOrNull: String? = null,
    @SerialName(KeyParams) val paramsOrNull: String? = null,
    @SerialName(KeyMessage) val messageOrNull: String? = null,
    @SerialName(KeyAroundLatLng) @Serializable(KSerializerPoint::class) val aroundLatLngOrNull: Point? = null,
    @SerialName(KeyAutomaticRadius) val automaticRadiusOrNull: Float? = null,
    @SerialName(KeyServerUsed) val serverUsedOrNull: String? = null,
    @SerialName(KeyIndexUsed) val indexUsedOrNull: IndexName? = null,
    @SerialName(KeyAbTestVariantID) val abTestVariantIDOrNull: Int? = null,
    @SerialName(KeyParsedQuery) val parsedQueryOrNull: String? = null,
    @SerialName(KeyFacets) @Serializable(KSerializerFacetMap::class) val facetsOrNull: Map<Attribute, List<Facet>>? = null,
    @SerialName(KeyDisjunctiveFacets) @Serializable(KSerializerFacetMap::class) val disjunctiveFacetsOrNull: Map<Attribute, List<Facet>>? = null,
    @SerialName(KeyFacets_Stats) val facetStatsOrNull: Map<Attribute, FacetStats>? = null,
    @SerialName(KeyCursor) val cursorOrNull: Cursor? = null,
    @SerialName(KeyIndex) val indexNameOrNull: IndexName? = null,
    @SerialName(KeyProcessed) val processedOrNull: Boolean? = null,
    @SerialName(KeyQueryID) val queryIDOrNull: QueryID? = null
) {

    @Transient
    public val hits: List<Hit>
        get() = hitsOrNull!!

    @Transient
    public val nbHits: Int
        get() = nbHitsOrNull!!

    @Transient
    public val page: Int
        get() = pageOrNull!!

    @Transient
    public val hitsPerPage: Int
        get() = hitsPerPageOrNull!!

    @Transient
    public val userData: List<JsonObject>
        get() = userDataOrNull!!

    @Transient
    public val nbPages: Int
        get() = nbPagesOrNull!!

    @Transient
    public val processingTimeMS: Long
        get() = processingTimeMSOrNull!!

    @Transient
    public val exhaustiveNbHits: Boolean
        get() = exhaustiveNbHitsOrNull!!

    @Transient
    public val exhaustiveFacetsCount: Boolean
        get() = exhaustiveFacetsCountOrNull!!

    @Transient
    public val query: String
        get() = queryOrNull!!

    @Transient
    public val queryAfterRemoval: String
        get() = queryAfterRemovalOrNull!!

    @Transient
    public val params: String
        get() = paramsOrNull!!

    @Transient
    public val message: String
        get() = messageOrNull!!

    @Transient
    public val aroundLatLng: Point
        get() = aroundLatLngOrNull!!

    @Transient
    public val automaticRadius: Float
        get() = automaticRadiusOrNull!!

    @Transient
    public val serverUsed: String
        get() = serverUsedOrNull!!

    @Transient
    public val indexUsed: IndexName
        get() = indexUsedOrNull!!

    @Transient
    public val abTestVariantID: Int
        get() = abTestVariantIDOrNull!!

    @Transient
    public val parsedQuery: String
        get() = parsedQueryOrNull!!

    @Transient
    public val facets: Map<Attribute, List<Facet>>
        get() = facetsOrNull!!

    @Transient
    public val disjunctiveFacets: Map<Attribute, List<Facet>>
        get() = disjunctiveFacetsOrNull!!

    @Transient
    public val facetStats: Map<Attribute, FacetStats>
        get() = facetStatsOrNull!!

    @Transient
    public val cursor: Cursor
        get() = cursorOrNull!!

    @Transient
    public val indexName: IndexName
        get() = indexNameOrNull!!

    @Transient
    public val processed: Boolean
        get() = processedOrNull!!

    @Transient
    public val queryID: QueryID
        get() = queryIDOrNull!!

    @Serializable(Hit.Companion::class)
    public data class Hit(
        val json: JsonObject
    ) : Map<String, JsonElement> by json {

        public fun <T> get(serializer: KSerializer<T>, attribute: Attribute): T {
            return Json.plain.fromJson(serializer, json.getAs(attribute.raw))
        }

        public fun <T> parse(serializer: KSerializer<T>): T {
            return Json.nonstrict.fromJson(serializer, json)
        }

        public fun getDistinctSequentialID(): Int {
            return json.getPrimitive(Key_DistinctSeqID).int
        }

        public fun getHierarchy(attribute: Attribute): Hierarchy {
            return Json.plain.fromJson(KSerializerHierarchy, json.getAs(attribute.raw))
        }

        public fun getRankingInfo(): RankingInfo {
            return Json.plain.fromJson(RankingInfo.serializer(), json.getAs(Key_RankingInfo))
        }

        public fun getHighlightResult(attribute: Attribute): HighlightResult {
            return getHighlightResult(HighlightResult.serializer(), attribute)
        }

        public fun getHighlightResults(attribute: Attribute): List<HighlightResult> {
            return getHighlightResult(HighlightResult.serializer().list, attribute)
        }

        public fun getHighlightResultMap(attribute: Attribute): Map<Attribute, List<HighlightResult>> {
            return getHighlightResult(KSerializerHighlightResults, attribute)
        }

        public fun getSnippetResult(attribute: Attribute): SnippetResult {
            return getSnippetResult(SnippetResult.serializer(), attribute)
        }

        public fun getSnippetResults(attribute: Attribute): List<SnippetResult> {
            return getSnippetResult(SnippetResult.serializer().list, attribute)
        }

        public fun getSnippetResultMap(attribute: Attribute): Map<Attribute, List<SnippetResult>> {
            return getSnippetResult(KSerializerSnippetResults, attribute)
        }

        private fun <T> getHighlightResult(serializer: KSerializer<T>, attribute: Attribute): T {
            return Json.plain.fromJson(serializer, json.getObject(Key_HighlightResult).getAs(attribute.raw))
        }

        private fun <T> getSnippetResult(serializer: KSerializer<T>, attribute: Attribute): T {
            return Json.plain.fromJson(serializer, json.getObject(Key_SnippetResult).getAs(attribute.raw))
        }

        @Serializer(Hit::class)
        internal companion object : KSerializer<Hit> {

            override fun deserialize(decoder: Decoder): Hit {
                return Hit(decoder.asJsonInput().jsonObject)
            }

            override fun serialize(encoder: Encoder, obj: Hit) {
                encoder.asJsonOutput().encodeJson(obj.json)
            }
        }
    }
}