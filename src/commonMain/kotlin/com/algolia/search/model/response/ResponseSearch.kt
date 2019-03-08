package com.algolia.search.model.response

import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.QueryID
import com.algolia.search.model.search.*
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject


@Serializable
public data class ResponseSearch(
    @Optional @SerialName(KeyHits) val hitsOrNull: List<Hit>? = null,
    @Optional @SerialName(KeyNbHits) val nbHitsOrNull: Int? = null,
    @Optional @SerialName(KeyPage) val pageOrNull: Int? = null,
    @Optional @SerialName(KeyHitsPerPage) val hitsPerPageOrNull: Int? = null,
    @Optional @SerialName(KeyUserData) val userDataOrNull: List<JsonObject>? = null,
    @Optional @SerialName(KeyNbPages) val nbPagesOrNull: Int? = null,
    @Optional @SerialName(KeyProcessingTimeMS) val processingTimeMSOrNull: Long? = null,
    @Optional @SerialName(KeyExhaustiveNbHits) val exhaustiveNbHitsOrNull: Boolean? = null,
    @Optional @SerialName(KeyExhaustiveFacetsCount) val exhaustiveFacetsCountOrNull: Boolean? = null,
    @Optional @SerialName(KeyQuery) val queryOrNull: String? = null,
    @Optional @SerialName(KeyQueryAfterRemoval) val queryAfterRemovalOrNull: String? = null,
    @Optional @SerialName(KeyParams) val paramsOrNull: String? = null,
    @Optional @SerialName(KeyMessage) val messageOrNull: String? = null,
    @Optional @SerialName(KeyAroundLatLng) @Serializable(KSerializerPoint::class) val aroundLatLngOrNull: Point? = null,
    @Optional @SerialName(KeyAutomaticRadius) val automaticRadiusOrNull: Float? = null,
    @Optional @SerialName(KeyServerUsed) val serverUsedOrNull: String? = null,
    @Optional @SerialName(KeyIndexUsed) val indexUsedOrNull: IndexName? = null,
    @Optional @SerialName(KeyAbTestVariantID) val abTestVariantIDOrNull: Int? = null,
    @Optional @SerialName(KeyParsedQuery) val parsedQueryOrNull: String? = null,
    @Optional @SerialName(KeyFacets) @Serializable(KSerializerFacetMap::class) val facetsOrNull: Map<Attribute, List<Facet>>? = null,
    @Optional @SerialName(KeyFacets_Stats) val facetStatsOrNull: Map<Attribute, FacetStats>? = null,
    @Optional @SerialName(KeyCursor) val cursorOrNull: Cursor? = null,
    @Optional @SerialName(KeyIndex) val indexNameOrNull: IndexName? = null,
    @Optional @SerialName(KeyProcessed) val processedOrNull: Boolean? = null,
    @Optional @SerialName(KeyQueryID) val queryIDOrNull: QueryID? = null
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
    ) {

        @Transient
        public val highlights: Map<Attribute, HighlightResult>? =
            json.getObjectOrNull(Key_HighlightResult)?.toHighlightResults()

        @Transient
        val snippets: Map<Attribute, SnippetResult>? = json.getObjectOrNull(Key_SnippetResult)?.toSnippetResults()

        @Transient
        val rankingInfo: RankingInfo? = json.getObjectOrNull(Key_RankingInfo)?.toRankingInfo()

        @Transient
        val distinctSequentialID: Int? = json.getPrimitiveOrNull(Key_DistinctSeqID)?.int


        public fun <T> get(serializer: KSerializer<T>, attribute: Attribute): T {
            return Json.plain.fromJson(serializer, json.getAs(attribute.raw))
        }

        public fun <T> parse(serializer: KSerializer<T>): T {
            return Json.nonstrict.fromJson(serializer, json)
        }

        public fun getAsHierarchy(attribute: Attribute): Hierarchy {
            return Json.plain.fromJson(KSerializerHierarchy, json.getAs(attribute.raw))
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