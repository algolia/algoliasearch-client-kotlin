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
import com.algolia.search.serialize.jsonArrayOrNull
import com.algolia.search.serialize.jsonObjectOrNull
import com.algolia.search.serialize.jsonPrimitiveOrNull
import com.algolia.search.serialize.toJsonNoDefaults
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

@Serializable(Consequence.Companion::class)
public data class Consequence(
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
    public companion object : KSerializer<Consequence> {

        private val serializer = ListSerializer(AutomaticFacetFilters.serializer())

        private fun MutableMap<String, JsonElement>.putFilters(key: String, filters: List<AutomaticFacetFilters>?) {
            filters?.let { put(key, JsonNoDefaults.encodeToJsonElement(serializer, it)) }
        }

        private fun JsonObject.getFilters(key: String): List<AutomaticFacetFilters>? {
            return this[key]?.jsonArrayOrNull?.let { JsonNoDefaults.decodeFromJsonElement(serializer, it) }
        }

        private fun JsonObject.getPromotions(): List<Promotion>? {
            return this[KeyPromote]?.jsonArrayOrNull?.let {
                Json.decodeFromJsonElement(ListSerializer(Promotion.serializer()), it)
            }
        }

        private fun JsonObject.getObjectIDs(): List<ObjectID>? {
            return this[KeyHide]?.jsonArrayOrNull?.let { Json.decodeFromJsonElement(KSerializerObjectIDs, it) }
        }

        private fun JsonObject.getEdits(): List<Edit>? {
            return this[KeyQuery]?.jsonObjectOrNull?.let { local ->
                local[KeyEdits]?.jsonArrayOrNull?.let { Json.decodeFromJsonElement(ListSerializer(Edit), it) }
                    ?: local[KeyRemoveLowercase]?.jsonArrayOrNull?.map { Edit(it.jsonPrimitive.content) }
            }
        }

        private fun JsonObject.extractQuery(edits: List<Edit>?): Query? {
            val modified = toMutableMap().apply {
                if (edits != null) remove(KeyQuery)
                remove(KeyAutomaticFacetFilters)
                remove(KeyAutomaticOptionalFacetFilters)
            }
            return if (modified.isNotEmpty()) {
                JsonNoDefaults.decodeFromJsonElement(Query.serializer(), JsonObject(modified))
            } else null
        }

        override fun serialize(encoder: Encoder, value: Consequence) {
            val params = mutableMapOf<String, JsonElement>().apply {
                putFilters(KeyAutomaticFacetFilters, value.automaticFacetFilters)
                putFilters(KeyAutomaticOptionalFacetFilters, value.automaticOptionalFacetFilters)
                value.query?.toJsonNoDefaults()?.let { putAll(it) }
                if (value.edits != null) put(
                    KeyQuery,
                    buildJsonObject {
                        put(KeyEdits, JsonNoDefaults.encodeToJsonElement(ListSerializer(Edit), value.edits))
                    }
                )
            }
            val json = buildJsonObject {
                if (params.isNotEmpty()) put(KeyParams, JsonObject(params))
                value.promote?.let {
                    put(KeyPromote, JsonNoDefaults.encodeToJsonElement(ListSerializer(Promotion.serializer()), it))
                }
                value.hide?.let {
                    put(KeyHide, JsonNoDefaults.encodeToJsonElement(KSerializerObjectIDs, it))
                }
                value.userData?.let { put(KeyUserData, it) }
                value.filterPromotes?.let { put(KeyFilterPromotes, it) }
            }

            encoder.asJsonOutput().encodeJsonElement(json)
        }

        override fun deserialize(decoder: Decoder): Consequence {
            val json = decoder.asJsonInput().jsonObject
            val params = json[KeyParams]?.jsonObjectOrNull
            val automaticFacetFilters = params?.getFilters(KeyAutomaticFacetFilters)
            val automaticOptionalFacetFilters = params?.getFilters(KeyAutomaticOptionalFacetFilters)
            val promote = json.getPromotions()
            val hide = json.getObjectIDs()
            val userData = json[KeyUserData]?.jsonObjectOrNull
            val edits = params?.getEdits()
            val query = params?.extractQuery(edits)
            val filterPromotes = json[KeyFilterPromotes]?.jsonPrimitiveOrNull?.booleanOrNull

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
