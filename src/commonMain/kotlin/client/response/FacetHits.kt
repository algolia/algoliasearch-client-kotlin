package client.response

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable


@Serializable
data class FacetHits(
    @Optional val facetHits: List<Item>? = null,
    @Optional val exhaustiveFacetsCount: Boolean? = null,
    @Optional val processingTimeMS: Long? = null
) {

    @Serializable
    data class Item(
        @Optional val value: String? = null,
        @Optional val highlighted: String? = null,
        @Optional val count: Int? = null
    )
}