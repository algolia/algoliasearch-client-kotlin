package client.data

import kotlinx.serialization.Serializable


@Serializable
data class FacetHits(
    val facetHits: List<Item>,
    val exhaustiveFacetsCount: Boolean,
    val processingTimeMS: Long
) {

    @Serializable
    data class Item(
        val value: String,
        val highlighted: String,
        val count: Int
    )
}