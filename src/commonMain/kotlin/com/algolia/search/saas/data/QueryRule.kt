package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.*
import com.algolia.search.saas.toAttribute
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.content
import kotlinx.serialization.json.json


data class QueryRule(
    val objectID: ObjectID,
    val condition: Condition,
    val consequence: Consequence,
    val description: String? = null,
    val enabled: Boolean? = null,
    val validity: List<TimeRange>? = null
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

    @Serializable
    data class Condition(
        val pattern: Pattern,
        val anchoring: Anchoring,
        @Optional val context: String? = null
    )

    @Serializable
    data class Consequence(
        val params: Query,
        val promote: List<Promotion>,
        val hide: List<ObjectID>,
        @Optional val userData: JsonObject? = null,
        @Optional val edits: List<Edit>? = null,
        @Optional val automaticFacetFilters: List<AutomaticFacetFilters>? = null,
        @Optional val automaticOptionalFacetFilters: List<AutomaticFacetFilters>? = null
    ) {

        @Serializable
        data class AutomaticFacetFilters(
            val attribute: Attribute,
            @Optional val score: Int? = null,
            @Optional val disjunctive: Boolean? = null
        )

        @Serializable
        data class Promotion(val objectID: ObjectID, val position: Int)
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
                val hasInsert = json.containsKey(KeyInsert)

                return Edit(
                    json[KeyDelete].content,
                    if (hasInsert) json[KeyInsert].content else null
                )
            }
        }
    }
}