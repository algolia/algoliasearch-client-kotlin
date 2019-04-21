package com.algolia.search.model.rule

import com.algolia.search.model.ObjectID
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.content
import kotlinx.serialization.json.json


@Serializable(Consequence.Companion::class)
public data class Consequence(
    val automaticFacetFilters: List<AutomaticFacetFilters>? = null,
    val automaticOptionalFacetFilters: List<AutomaticFacetFilters>? = null,
    val edits: List<Edit>? = null,
    val query: Query? = null,
    val promote: List<Promotion>? = null,
    val hide: List<ObjectID>? = null,
    val userData: JsonObject? = null
) {

    @Serializer(Consequence::class)
    companion object : KSerializer<Consequence> {

        private val serializer = AutomaticFacetFilters.serializer().list

        private fun MutableMap<String, JsonElement>.putFilters(key: String, filters: List<AutomaticFacetFilters>?) {
            filters?.let { put(key, JsonNoDefaults.toJson(serializer, it)) }
        }

        private fun JsonObject.getFilters(key: String): List<AutomaticFacetFilters>? {
            return getArrayOrNull(key)?.let { JsonNoDefaults.fromJson(serializer, it) }
        }

        private fun JsonObject.getPromotions(): List<Promotion>? {
            return getArrayOrNull(KeyPromote)?.let { Json.fromJson(Promotion.serializer().list, it) }
        }

        private fun JsonObject.getObjectIDs(): List<ObjectID>? {
            return getArrayOrNull(KeyHide)?.let { Json.fromJson(KSerializerObjectIDs, it) }
        }

        private fun JsonObject.getEdits(): List<Edit>? {
            return getObjectOrNull(KeyQuery)?.let { local ->
                local.getArrayOrNull(KeyEdits)?.let { Json.fromJson(Edit.list, it) }
                    ?: local.getArrayOrNull(KeyRemoveLowercase)?.map { Edit(it.content) }
            }
        }

        private fun JsonObject.extractQuery(edits: List<Edit>?): Query? {
            val modified = toMutableMap().apply {
                if (edits != null) remove(KeyQuery)
                remove(KeyAutomaticFacetFilters)
                remove(KeyAutomaticOptionalFacetFilters)
            }
            return if (modified.isNotEmpty()) JsonNoDefaults.fromJson(Query.serializer(), JsonObject(modified)) else null
        }

        override fun serialize(encoder: Encoder, obj: Consequence) {
            val params = mutableMapOf<String, JsonElement>().apply {
                putFilters(KeyAutomaticFacetFilters, obj.automaticFacetFilters)
                putFilters(KeyAutomaticOptionalFacetFilters, obj.automaticOptionalFacetFilters)
                obj.query?.toJsonNoDefaults()?.let { putAll(it.content) }
                if (obj.edits != null) put(KeyQuery, json { KeyEdits to JsonNoDefaults.toJson(Edit.list, obj.edits) })
            }
            val json = json {
                if (params.isNotEmpty()) KeyParams to JsonObject(params)
                obj.promote?.let { KeyPromote to JsonNoDefaults.toJson(Promotion.serializer().list, it) }
                obj.hide?.let { KeyHide to JsonNoDefaults.toJson(KSerializerObjectIDs, it) }
                obj.userData?.let { KeyUserData to it }
            }

            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): Consequence {
            val json = decoder.asJsonInput().jsonObject
            val params = json.getObjectOrNull(KeyParams)
            val automaticFacetFilters = params?.getFilters(KeyAutomaticFacetFilters)
            val automaticOptionalFacetFilters = params?.getFilters(KeyAutomaticOptionalFacetFilters)
            val promote = json.getPromotions()
            val hide = json.getObjectIDs()
            val userData = json.getObjectOrNull(KeyUserData)
            val edits = params?.getEdits()
            val query = params?.extractQuery(edits)

            return Consequence(
                automaticFacetFilters = automaticFacetFilters,
                automaticOptionalFacetFilters = automaticOptionalFacetFilters,
                query = query,
                promote = promote,
                hide = hide,
                userData = userData,
                edits = edits
            )
        }
    }
}