package com.algolia.search.saas.model

import com.algolia.search.saas.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json


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
                Json.plain.fromJson(Pattern, json[KeyPattern]),
                Json.plain.fromJson(Anchoring, json[KeyAnchoring]),
                json.getPrimitiveOrNull(KeyContext)?.contentOrNull
            )
        }
    }
}