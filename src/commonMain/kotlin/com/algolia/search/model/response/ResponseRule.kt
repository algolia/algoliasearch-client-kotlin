package com.algolia.search.model.response

import com.algolia.search.model.rule.Rule
import com.algolia.search.serialize.JsonNonStrict
import com.algolia.search.serialize.Key_HighlightResult
import com.algolia.search.serialize.asJsonInput
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonObject


@Serializable
public data class ResponseRule(
    val rule: Rule,
    val highlightsOrNull: JsonObject? = null
) {

    @Transient
    public val highlights: JsonObject
        get() = highlightsOrNull!!

    @Serializer(ResponseRule::class)
    companion object : DeserializationStrategy<ResponseRule> {

        override fun deserialize(decoder: Decoder): ResponseRule {
            val json = decoder.asJsonInput().jsonObject
            val rule = JsonNonStrict.fromJson(Rule.serializer(), json)
            val highlights = json.getObjectOrNull(Key_HighlightResult)

            return ResponseRule(rule, highlights)
        }
    }
}