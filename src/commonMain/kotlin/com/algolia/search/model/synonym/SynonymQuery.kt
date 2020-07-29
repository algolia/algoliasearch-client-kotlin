package com.algolia.search.model.synonym

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyPage
import com.algolia.search.serialize.KeyQuery
import com.algolia.search.serialize.KeyType
import com.algolia.search.serialize.asJsonOutput
import kotlinx.serialization.Encoder
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.json

@DSLParameters
@Serializable(SynonymQuery.Companion::class)
public data class SynonymQuery(
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
    public companion object : SerializationStrategy<SynonymQuery> {

        override fun serialize(encoder: Encoder, value: SynonymQuery) {
            val json = json {
                value.query?.let { KeyQuery to it }
                value.page?.let { KeyPage to it }
                value.hitsPerPage?.let { KeyHitsPerPage to it }
                value.synonymTypes?.let { types -> KeyType to types.joinToString(",") { it.raw } }
            }

            encoder.asJsonOutput().encodeJson(json)
        }
    }
}
