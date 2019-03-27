package com.algolia.search.model.synonym

import com.algolia.search.serialize.*
import kotlinx.serialization.Encoder
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.json


@Serializable(SynonymQuery.Companion::class)
data class SynonymQuery(
    var query: String? = null,
    var page: Int? = null,
    var hitsPerPage: Int? = null,
    var types: List<SynonymType>? = null
) {

    @Serializer(SynonymQuery::class)
    internal companion object : SerializationStrategy<SynonymQuery> {

        override fun serialize(encoder: Encoder, obj: SynonymQuery) {
            val json = json {
                obj.query?.let { KeyQuery to it }
                obj.page?.let { KeyPage to it }
                obj.hitsPerPage?.let { KeyHitsPerPage to it }
                obj.types?.let { types -> KeyType to types.joinToString(",") { it.raw } }
            }

            encoder.asJsonOutput().encodeJson(json)
        }
    }
}