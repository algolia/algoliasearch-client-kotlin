package com.algolia.search.model.response

import com.algolia.search.model.response.ResponseSearchSynonyms.Hit
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.serialize.JsonNonStrict
import com.algolia.search.serialize.KeyHits
import com.algolia.search.serialize.KeyNbHits
import com.algolia.search.serialize.Key_HighlightResult
import com.algolia.search.serialize.asJsonInput
import kotlinx.serialization.Decoder
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.JsonObject

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
        companion object : DeserializationStrategy<Hit> {

            override fun deserialize(decoder: Decoder): Hit {
                val json = decoder.asJsonInput().jsonObject
                val synonym = JsonNonStrict.fromJson(Synonym.serializer(), json)
                val highlightResult = json.getObjectOrNull(Key_HighlightResult)

                return Hit(synonym, highlightResult)
            }
        }
    }
}
