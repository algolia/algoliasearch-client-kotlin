package com.algolia.search.model.synonym

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.serialize.*
import kotlinx.serialization.Encoder
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.json
import kotlin.jvm.JvmOverloads


@DSLParameters
@Serializable(SynonymQuery.Companion::class)
public data class SynonymQuery @JvmOverloads constructor(
    /**
     * Engine default: ""
     * The search query to find synonyms. Use an empty query to browse all the [Synonym] of an index.
     */
    var query: String? = null,
    /**
     * Engine default: 0
     * The page to fetch when browsing through several pages of results. This value is zero-based.
     */
    var page: Int? = null,
    /**
     * Engine default: 100
     * The number of synonyms to return for each call.
     */
    var hitsPerPage: Int? = null,
    /**
     * Engine default: []
     * Restrict the search to a specific [SynonymType].
     */
    var synonymTypes: List<SynonymType>? = null
) {

    @Serializer(SynonymQuery::class)
    companion object : SerializationStrategy<SynonymQuery> {

        override fun serialize(encoder: Encoder, obj: SynonymQuery) {
            val json = json {
                obj.query?.let { KeyQuery to it }
                obj.page?.let { KeyPage to it }
                obj.hitsPerPage?.let { KeyHitsPerPage to it }
                obj.synonymTypes?.let { types -> KeyType to types.joinToString(",") { it.raw } }
            }

            encoder.asJsonOutput().encodeJson(json)
        }
    }
}