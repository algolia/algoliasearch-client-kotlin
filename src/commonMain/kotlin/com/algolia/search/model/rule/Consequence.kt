package com.algolia.search.model.rule

import com.algolia.search.model.ObjectID
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.Json
import com.algolia.search.serialize.JsonNoDefaults
import com.algolia.search.serialize.KSerializerObjectIDs
import com.algolia.search.serialize.KeyAutomaticFacetFilters
import com.algolia.search.serialize.KeyAutomaticOptionalFacetFilters
import com.algolia.search.serialize.KeyEdits
import com.algolia.search.serialize.KeyFilterPromotes
import com.algolia.search.serialize.KeyHide
import com.algolia.search.serialize.KeyParams
import com.algolia.search.serialize.KeyPromote
import com.algolia.search.serialize.KeyQuery
import com.algolia.search.serialize.KeyRemoveLowercase
import com.algolia.search.serialize.KeyUserData
import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import com.algolia.search.serialize.toJsonNoDefaults
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.content
import kotlinx.serialization.json.json
import kotlinx.serialization.list

@Serializable(Consequence.Companion::class)
data class Consequence(
    /**
     * Names of facets to which automatic filtering must be applied; they must match the facet name of a facet value
     * placeholder in the query pattern.
     * Ex. facetName1, facetName2. You can specify a score: facetName1<score=5>, facetName2<score=1>.
     */
    val automaticFacetFilters: List<AutomaticFacetFilters>? = null,
    /**
     * Same as [automaticFacetFilters], but the engine treats the filters as optional.
     * Behaves like [Query.optionalFilters].
     */
    val automaticOptionalFacetFilters: List<AutomaticFacetFilters>? = null,
    /**
     * Describes incremental edits to be made to the query string. You can't do both this and [edits] at the same time.
     */
    val edits: List<Edit>? = null,
    /**
     * When providing a [Query.query], it replaces the entire query string. You can't do both this and [edits] at the
     * same time.
     */
    val query: Query? = null,
    /**
     * Objects to promote as hits.
     */
    val promote: List<Promotion>? = null,
    /**
     * Whether promoted results should match the filters of the current search.
     */
    val filterPromotes: Boolean? = null,
    /**
     * Objects to hide from hits.
     */
    val hide: List<ObjectID>? = null,
    /**
     * Custom JSON object that will be appended to the [ResponseSearch.userData].
     * This object is not interpreted by the API. It is limited to 1kB of minified JSON.
     */
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
            return if (modified.isNotEmpty()) JsonNoDefaults.fromJson(
                Query.serializer(),
                JsonObject(modified)
            ) else null
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
                obj.filterPromotes?.let { KeyFilterPromotes to it }
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
            val filterPromotes = json.getPrimitiveOrNull(KeyFilterPromotes)?.booleanOrNull

            return Consequence(
                automaticFacetFilters = automaticFacetFilters,
                automaticOptionalFacetFilters = automaticOptionalFacetFilters,
                query = query,
                promote = promote,
                hide = hide,
                userData = userData,
                edits = edits,
                filterPromotes = filterPromotes
            )
        }
    }
}
