package com.algolia.search.model.response

import com.algolia.search.model.response.ResponseSearchRules.Hit
import com.algolia.search.model.rule.Rule
import com.algolia.search.serialize.KeyHits
import com.algolia.search.serialize.KeyNbHits
import com.algolia.search.serialize.KeyNbPages
import com.algolia.search.serialize.KeyPage
import com.algolia.search.serialize.Key_HighlightResult
import com.algolia.search.serialize.internal.JsonNonStrict
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.jsonObjectOrNull
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

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

        @OptIn(ExperimentalSerializationApi::class)
        @Serializer(Hit::class)
        public companion object : DeserializationStrategy<Hit> {

            override fun deserialize(decoder: Decoder): Hit {
                val json = decoder.asJsonInput().jsonObject
                val rule = JsonNonStrict.decodeFromJsonElement(Rule.serializer(), json)
                val highlightResult = json[Key_HighlightResult]?.jsonObjectOrNull

                return Hit(rule, highlightResult)
            }
        }
    }
}
