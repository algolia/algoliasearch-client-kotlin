package com.algolia.search.model.response

import com.algolia.search.model.queryrule.QueryRule
import com.algolia.search.serialize.Key_HighlightResult
import com.algolia.search.serialize.asJsonInput
import kotlinx.serialization.Decoder
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject


@Serializable
data class ResponseQueryRule(
    val queryRule: QueryRule,
    val highlight: JsonObject? = null
) {

    @Serializer(ResponseQueryRule::class)
    companion object : DeserializationStrategy<ResponseQueryRule> {

        override fun deserialize(decoder: Decoder): ResponseQueryRule {
            val json = decoder.asJsonInput().jsonObject
            val queryRule = Json.nonstrict.fromJson(QueryRule.serializer(), json)
            val highlight = json.getObjectOrNull(Key_HighlightResult)

            return ResponseQueryRule(queryRule, highlight)
        }
    }
}