package com.algolia.search.model.response

import com.algolia.search.model.response.ResponseSearchRules.Hit
import com.algolia.search.model.rule.Rule
import com.algolia.search.serialize.JsonNonStrict
import com.algolia.search.serialize.KeyHits
import com.algolia.search.serialize.KeyNbHits
import com.algolia.search.serialize.KeyNbPages
import com.algolia.search.serialize.KeyPage
import com.algolia.search.serialize.Key_HighlightResult
import com.algolia.search.serialize.asJsonInput
import kotlinx.serialization.Decoder
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.JsonObject

@Serializable
public data class ResponseSearchRules(
    /**
     * A list of [Hit].
     */
    @SerialName(KeyHits) val hits: List<Hit>,
    /**
     *  Number of hits.
     */
    @SerialName(KeyNbHits) val nbHits: Int,
    /**
     * Returned page number.
     */
    @SerialName(KeyPage) val page: Int,
    /**
     * Total number of pages.
     */
    @SerialName(KeyNbPages) val nbPages: Int
) {

    @Serializable(Hit.Companion::class)
    public data class Hit(
        val rule: Rule,
        val highlightResultOrNull: JsonObject? = null
    ) {

        public val highlightResult: JsonObject
            get() = highlightResultOrNull!!

        @Serializer(Hit::class)
        companion object : DeserializationStrategy<Hit> {

            override fun deserialize(decoder: Decoder): Hit {
                val json = decoder.asJsonInput().jsonObject
                val rule = JsonNonStrict.fromJson(Rule.serializer(), json)
                val highlightResult = json.getObjectOrNull(Key_HighlightResult)

                return Hit(rule, highlightResult)
            }
        }
    }
}
