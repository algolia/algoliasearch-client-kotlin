package client

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Serializable
data class SearchParams(
    @Optional val query: String? = null,
    @Optional val facets: List<String>? = null
)