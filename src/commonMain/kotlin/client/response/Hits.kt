package client.response

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Hits(
    @Optional val index: String? = null,
    @Optional val hits: List<Hit>? = null,
    @Optional val nbHits: Int? = null,
    @Optional val processingTimeMS: Long? = null,
    @Optional val exhaustiveNbHits: Boolean? = null,
    @Optional val query: String? = null,
    @Optional val params: String? = null,
    @SerialName("facets_stats") @Optional val facetStats: Map<String, Map<String, Int>>? = null,
    @Optional val cursor: String? = null,
    @Optional val hitsPerPage: Int? = null,
    @Optional val page: Int? = null,
    @Optional val nbPages: Int? = null,
    @Optional val offset: Int? = null,
    @Optional val length: Int? = null,
    @Optional val facets: Map<String, Map<String, Int>>? = null,
    @Optional val exhaustiveFacetsCount: Boolean? = null
)