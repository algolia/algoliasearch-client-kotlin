package com.algolia.search.model.response

import com.algolia.search.model.rule.Rule
import com.algolia.search.serialize.Key_HighlightResult
import com.algolia.search.serialize.asJsonInput
import kotlinx.serialization.Decoder
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject


@Serializable
data class ResponseRule(
    val rule: Rule,
    val highlight: JsonObject? = null
) {

    @Serializer(ResponseRule::class)
    companion object : DeserializationStrategy<ResponseRule> {

        override fun deserialize(decoder: Decoder): ResponseRule {
            val json = decoder.asJsonInput().jsonObject
            val rule = Json.nonstrict.fromJson(Rule.serializer(), json)
            val highlight = json.getObjectOrNull(Key_HighlightResult)

            return ResponseRule(rule, highlight)
        }
    }
}