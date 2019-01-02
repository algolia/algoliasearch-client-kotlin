package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KSerializerFacets
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Hits(
    @Optional val index: IndexName? = null,
    @Optional val hits: List<Hit>? = null,
    @Optional val nbHits: Int? = null,
    @Optional val processingTimeMS: Long? = null,
    @Optional val exhaustiveNbHits: Boolean? = null,
    @Optional val query: String? = null,
    @Optional val params: String? = null,
    @Optional @Serializable(KSerializerFacetStats::class) @SerialName("facets_stats") val facetStats: Map<Attribute, FacetStats>? = null,
    @Optional val cursor: Cursor? = null,
    @Optional val hitsPerPage: Int? = null,
    @Optional val page: Int? = null,
    @Optional val nbPages: Int? = null,
    @Optional val offset: Int? = null,
    @Optional val length: Int? = null,
    @Optional @Serializable(KSerializerFacets::class) val facets: Map<Attribute, List<Facet>>? = null,
    @Optional val exhaustiveFacetsCount: Boolean? = null
)