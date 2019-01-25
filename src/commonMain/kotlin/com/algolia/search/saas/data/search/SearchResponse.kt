package com.algolia.search.saas.data.search

import com.algolia.search.saas.data.Attribute
import com.algolia.search.saas.data.Cursor
import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.HashMapSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject


@Serializable
data class SearchResponse(
    @Optional @SerialName(KeyIndex) val index: IndexName? = null,
    @Optional @SerialName(KeyHits) val hits: List<Hit>? = null,
    @Optional @SerialName(KeyNbHits) val nbHits: Int? = null,
    @Optional @SerialName(KeyProcessingTimeMS) val processingTimeMS: Long? = null,
    @Optional @SerialName(KeyQuery) val query: String? = null,
    @Optional @SerialName(KeyParams) val params: String? = null,
    @Optional @SerialName(KeyFacetsStats) val facetStats: Map<Attribute, FacetStats>? = null,
    @Optional @SerialName(KeyCursor) val cursor: Cursor? = null,
    @Optional @SerialName(KeyHitsPerPage) val hitsPerPage: Int? = null,
    @Optional @SerialName(KeyPage) val page: Int? = null,
    @Optional @SerialName(KeyNbPages) val nbPages: Int? = null,
    @Optional @SerialName(KeyFacets) @Serializable(KSerializerFacets::class) val facets: Map<Attribute, List<Facet>>? = null,
    @Optional @SerialName(KeyExhaustiveFacetsCount) val exhaustiveFacetsCount: Boolean? = null
) {

    @Serializable(Hit.Companion::class)
    data class Hit(
        val json: JsonObject
    ) {

        val highlights = json.getObjectOrNull(KeyHighlightResult)?.let {
            Json.plain.fromJson(HashMapSerializer(Attribute, HighlightResult.serializer()), it)
        }
        val snippets = json.getObjectOrNull(KeySnippetResult)?.let {
            Json.plain.fromJson(KSerializerSnippets, it)
        }

        fun <T> get(serializer: KSerializer<T>, attribute: Attribute): T {
            return Json.plain.fromJson(serializer, json[attribute.raw])
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