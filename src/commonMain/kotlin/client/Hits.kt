package client

import kotlinx.serialization.*
import kotlinx.serialization.json.JSON


@Serializable
data class Hits(
    val hits: List<Hit>,
    val nbHits: Int,
    val nbPages: Int,
    val page: Int,
    val hitsPerPage: Int,
    val processingTimeMS: Long,
    val exhaustiveNbHits: Boolean,
    val query: String,
    val params: String,
    @Optional val facets: Map<String, Map<String, Int>>? = null,
    @Optional val exhaustiveFacetsCount: Boolean? = null
) {

    @Serializable
    data class Hit(val serialized: String) {

        @Serializer(forClass = Hit::class)
        companion object : KSerializer<Hit> {
            override fun deserialize(input: Decoder): Hit {
                val json = (input as JSON.JsonInput).readAsTree()
                return Hit(json.toString())
            }
        }
    }
}
