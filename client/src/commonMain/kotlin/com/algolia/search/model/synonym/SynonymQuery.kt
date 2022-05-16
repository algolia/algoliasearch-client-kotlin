package com.algolia.search.model.synonym

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonOutput
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

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

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(SynonymQuery::class)
    public companion object : SerializationStrategy<SynonymQuery> {

        override fun serialize(encoder: Encoder, value: SynonymQuery) {
            val json = buildJsonObject {
                value.query?.let { put(Key.Query, it) }
                value.page?.let { put(Key.Page, it) }
                value.hitsPerPage?.let { put(Key.HitsPerPage, it) }
                value.synonymTypes?.let { types -> put(Key.Type, types.joinToString(",") { it.raw }) }
            }

            encoder.asJsonOutput().encodeJsonElement(json)
        }
    }
}
