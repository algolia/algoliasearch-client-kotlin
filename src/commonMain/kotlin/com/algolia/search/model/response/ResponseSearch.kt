package com.algolia.search.model.response

import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.*
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject


@Serializable
data class ResponseSearch(
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
    @Optional @SerialName(KeyAroundLatLng) val aroundLatLngOrNull: String? = null,
    @Optional @SerialName(KeyAutomaticRadius) val automaticRadiusOrNull: Float? = null,
    @Optional @SerialName(KeyServerUsed) val serverUsedOrNull: String? = null,
    @Optional @SerialName(KeyIndexUsed) val indexUsedOrNull: IndexName? = null,
    @Optional @SerialName(KeyAbTestVariantID) val abTestVariantIDOrNull: Int? = null,
    @Optional @SerialName(KeyParsedQuery) val parsedQueryOrNull: String? = null,
    @Optional @SerialName(KeyFacets) @Serializable(KSerializerFacets::class) val facetsOrNull: Map<Attribute, List<Facet>>? = null,
    @Optional @SerialName(KeyFacets_Stats) val facetStatsOrNull: Map<Attribute, FacetStats>? = null,
    @Optional @SerialName(KeyCursor) val cursorOrNull: Cursor? = null,
    @Optional @SerialName(KeyIndex) val indexNameOrNull: IndexName? = null,
    @Optional @SerialName(KeyProcessed) val processedOrNull: Boolean? = null,
    @Optional @SerialName(KeyQueryID) val queryIDOrNull: String? = null
) {

    @Transient
    val hits: List<Hit>
        get() = hitsOrNull!!

    @Transient
    val nbHits: Int
        get() = nbHitsOrNull!!

    @Transient
    val page: Int
        get() = pageOrNull!!

    @Transient
    val hitsPerPage: Int
        get() = hitsPerPageOrNull!!

    @Transient
    val userData: List<JsonObject>
        get() = userDataOrNull!!

    @Transient
    val nbPages: Int
        get() = nbPagesOrNull!!

    @Transient
    val processingTimeMS: Long
        get() = processingTimeMSOrNull!!

    @Transient
    val exhaustiveNbHits: Boolean
        get() = exhaustiveNbHitsOrNull!!

    @Transient
    val exhaustiveFacetsCount: Boolean
        get() = exhaustiveFacetsCountOrNull!!

    @Transient
    val query: String
        get() = queryOrNull!!

    @Transient
    val queryAfterRemoval: String
        get() = queryAfterRemovalOrNull!!

    @Transient
    val params: String
        get() = paramsOrNull!!

    @Transient
    val message: String
        get() = messageOrNull!!

    @Transient
    val aroundLatLng: String
        get() = aroundLatLngOrNull!!

    @Transient
    val automaticRadius: Float
        get() = automaticRadiusOrNull!!

    @Transient
    val serverUsed: String
        get() = serverUsedOrNull!!

    @Transient
    val indexUsed: IndexName
        get() = indexUsedOrNull!!

    @Transient
    val abTestVariantID: Int
        get() = abTestVariantIDOrNull!!

    @Transient
    val parsedQuery: String
        get() = parsedQueryOrNull!!

    @Transient
    val facets: Map<Attribute, List<Facet>>
        get() = facetsOrNull!!

    @Transient
    val facetStats: Map<Attribute, FacetStats>
        get() = facetStatsOrNull!!

    @Transient
    val cursor: Cursor
        get() = cursorOrNull!!

    @Transient
    val indexName: IndexName
        get() = indexNameOrNull!!

    @Transient
    val processed: Boolean
        get() = processedOrNull!!

    @Transient
    val queryID: String
        get() = queryIDOrNull!!

    @Serializable(Hit.Companion::class)
    data class Hit(
        val json: JsonObject
    ) {

        @Transient
        val highlights: Map<Attribute, HighlightResult>? =
            json.getObjectOrNull(Key_HighlightResult)?.toHighlightResults()

        @Transient
        val snippets: Map<Attribute, SnippetResult>? = json.getObjectOrNull(Key_SnippetResult)?.toSnippetResults()

        @Transient
        val rankingInfo: RankingInfo? = json.getObjectOrNull(Key_RankingInfo)?.toRankingInfo()

        @Transient
        val distinctSequentialID: Int? = json.getPrimitiveOrNull(Key_DistinctSeqID)?.int


        fun <T> get(serializer: KSerializer<T>, attribute: Attribute): T {
            return Json.plain.fromJson(serializer, json[attribute.raw])
        }

        fun <T> parse(serializer: KSerializer<T>): T {
            return Json.nonstrict.fromJson(serializer, json)
        }

        @Serializer(Hit::class)
        companion object : KSerializer<Hit> {

            override fun deserialize(decoder: Decoder): Hit {
                return Hit(decoder.asJsonInput().jsonObject)
            }

            override fun serialize(encoder: Encoder, obj: Hit) {
                encoder.asJsonOutput().encodeJson(obj.json)
            }
        }
    }
}