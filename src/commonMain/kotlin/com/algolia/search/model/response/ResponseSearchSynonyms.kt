package com.algolia.search.model.response

import com.algolia.search.model.response.ResponseSearchSynonyms.Hit
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.serialize.KeyHits
import com.algolia.search.serialize.KeyNbHits
import com.algolia.search.serialize.Key_HighlightResult
import com.algolia.search.serialize.internal.JsonNonStrict
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.jsonObjectOrNull
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

@Serializable
public data class ResponseSearchSynonyms(
    /**
     * A list of [Hit].
     */
    @SerialName(KeyHits) val hits: List<Hit>,
    /**
     * Number of hits.
     */
    @SerialName(KeyNbHits) val nbHits: Int
) {

    @Serializable(Hit.Companion::class)
    public data class Hit(
        val synonym: Synonym,
        val highlightResultOrNull: JsonObject? = null
    ) {

        public val highlightResult: JsonObject
            get() = highlightResultOrNull!!

        @Serializer(Hit::class)
        public companion object : DeserializationStrategy<Hit> {

            override fun deserialize(decoder: Decoder): Hit {
                val json = decoder.asJsonInput().jsonObject
                val synonym = JsonNonStrict.decodeFromJsonElement(Synonym.serializer(), json)
                val highlightResult = json[Key_HighlightResult]?.jsonObjectOrNull

                return Hit(synonym, highlightResult)
            }
        }
    }
}
