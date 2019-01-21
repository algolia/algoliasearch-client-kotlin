package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.*
import com.algolia.search.saas.toAttribute
import com.algolia.search.saas.toObjectID
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.*


@Serializable
data class QueryRule(
    val objectID: ObjectID,
    val condition: Condition,
    val consequence: Consequence,
    @Optional val description: String? = null,
    @Optional val enabled: Boolean? = null,
    @Optional val validity: List<QueryRule.TimeRange>? = null
) {

    @Serializable(Pattern.Companion::class)
    sealed class Pattern(override val raw: String) : Raw<String> {

        data class Facet(val attribute: Attribute) : Pattern("{facet:$attribute}")

        data class Literal(override val raw: String) : Pattern(raw)

        companion object : KSerializer<Pattern> {

            private val serializer = StringSerializer

            override val descriptor = serializer.descriptor

            override fun serialize(encoder: Encoder, obj: Pattern) {
                serializer.serialize(encoder, obj.raw)
            }

            override fun deserialize(decoder: Decoder): Pattern {
                val string = serializer.deserialize(decoder)
                val regex = Regex("\\{facet:(.*)}")
                val match = regex.find(string)

                return when {
                    match != null -> Facet(match.groupValues[1].toAttribute())
                    else -> Literal(string)
                }
            }
        }
    }

    @Serializable(Anchoring.Companion::class)
    sealed class Anchoring(override val raw: String) : Raw<String> {

        object Is : Anchoring(KeyIs)

        object StartsWith : Anchoring(KeyStartsWith)

        object EndsWith : Anchoring(KeyEndsWith)

        object Contains : Anchoring(KeyContains)

        data class Other(override val raw: String) : Anchoring(raw)

        companion object : KSerializer<Anchoring> {

            private val serializer = StringSerializer

            override val descriptor = StringSerializer.descriptor

            override fun serialize(encoder: Encoder, obj: Anchoring) {
                serializer.serialize(encoder, obj.raw)
            }

            override fun deserialize(decoder: Decoder): Anchoring {
                val string = serializer.deserialize(decoder)

                return when (string) {
                    KeyIs -> Is
                    KeyEndsWith -> EndsWith
                    KeyStartsWith -> StartsWith
                    KeyContains -> Contains
                    else -> Other(string)
                }
            }
        }
    }

    @Serializable(Condition.Companion::class)
    data class Condition(
        val pattern: Pattern,
        val anchoring: Anchoring,
        @Optional val context: String? = null
    ) {

        @Serializer(Condition::class)
        companion object : KSerializer<Condition> {

            override fun serialize(encoder: Encoder, obj: Condition) {
                val json = json {
                    KeyPattern to obj.pattern.raw
                    KeyAnchoring to obj.anchoring.raw
                    obj.context?.let { KeyContext to it }
                }

                encoder.asJsonOutput().encodeJson(json)
            }

            override fun deserialize(decoder: Decoder): Condition {
                val json = decoder.asJsonInput().jsonObject

                return Condition(
                    Json.plain.fromJson(json[KeyPattern], Pattern),
                    Json.plain.fromJson(json[KeyAnchoring], Anchoring),
                    json.getPrimitiveOrNull(KeyContext)?.contentOrNull
                )
            }
        }
    }

    @Serializable(Consequence.Companion::class)
    data class Consequence(
        val params: Query = Query(),
        val promote: List<Promotion>? = null,
        val hide: List<ObjectID>? = null,
        val userData: JsonObject? = null,
        val edits: List<Edit>? = null,
        val automaticFacetFilters: List<AutomaticFacetFilters>? = null,
        val automaticOptionalFacetFilters: List<AutomaticFacetFilters>? = null
    ) {

        @Serializable
        data class AutomaticFacetFilters(
            val attribute: Attribute,
            @Optional val score: Int? = null,
            @Optional val disjunctive: Boolean? = null
        )

        @Serializable
        data class Promotion(val objectID: ObjectID, val position: Int)


        @Serializer(Consequence::class)
        companion object : KSerializer<Consequence> {

            private fun List<ObjectID>.toJsonObject(): JsonArray {
                return JsonArray(map { json { KeyObjectID to JsonLiteral(it.raw) } })
            }

            override fun serialize(encoder: Encoder, obj: Consequence) {
                val serializer = AutomaticFacetFilters.serializer().list
                val map = obj.params.encodeNoNulls().toMutableMap().apply {
                    obj.edits?.let { put(KeyEdit, Json.plain.toJson(it, Edit.list)) }
                    obj.automaticFacetFilters?.let {
                        put(KeyAutomaticFacetFilters, Json.plain.toJson(it, serializer))
                    }
                    obj.automaticOptionalFacetFilters?.let {
                        put(KeyAutomaticOptionalFacetFilters, Json.plain.toJson(it, serializer))
                    }
                }

                val json = json {
                    KeyParams to JsonObject(map)
                    obj.promote?.let { KeyPromote to Json.plain.toJson(it, Promotion.serializer().list) }
                    obj.hide?.let { KeyHide to it.toJsonObject() }
                    obj.userData?.let { KeyUserData to it }
                }

                encoder.asJsonOutput().encodeJson(json)
            }

            override fun deserialize(decoder: Decoder): Consequence {
                val json = decoder.asJsonInput().jsonObject
                val params = json.getObject(KeyParams)
                val promote = json.getArrayOrNull(KeyPromote)
                val hide = json.getArrayOrNull(KeyHide)
                val edits = params.getOrNull(KeyEdit)
                val filters = params.getOrNull(KeyAutomaticFacetFilters)
                val optionalFilters = params.getOrNull(KeyAutomaticOptionalFacetFilters)
                val facetSerializer = AutomaticFacetFilters.serializer().list

                return Consequence(
                    params = Json.nonstrict.fromJson(params, Query.serializer()),
                    promote = promote?.let { Json.plain.fromJson(it, Promotion.serializer().list) },
                    hide = hide?.let { it.map { it.jsonObject[KeyObjectID].content.toObjectID() } },
                    edits = edits?.let { Json.plain.fromJson(it, Edit.list) },
                    userData = json.getObjectOrNull(KeyUserData),
                    automaticFacetFilters = filters?.let { Json.plain.fromJson(it, facetSerializer) },
                    automaticOptionalFacetFilters = optionalFilters?.let { Json.plain.fromJson(it, facetSerializer) }
                )
            }
        }
    }

    @Serializable
    data class TimeRange(val from: Long, val until: Long)

    @Serializable(Edit.Companion::class)
    data class Edit(val delete: String, val insert: String? = null) {

        @Serializer(Edit::class)
        companion object : KSerializer<Edit> {

            override fun serialize(encoder: Encoder, obj: Edit) {
                val type = if (obj.insert != null) KeyReplace else KeyRemove
                val json = json {
                    KeyType to type
                    KeyDelete to obj.delete
                    obj.insert?.let { KeyInsert to it }
                }

                encoder.asJsonOutput().encodeJson(json)
            }

            override fun deserialize(decoder: Decoder): Edit {
                val json = decoder.asJsonInput().jsonObject

                return Edit(
                    json[KeyDelete].content,
                    json.getOrNull(KeyInsert)?.content
                )
            }
        }
    }
}