package client.data

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable


@Serializable
data class MultipleHits(
    @Optional val results: List<Hits>? = null
)