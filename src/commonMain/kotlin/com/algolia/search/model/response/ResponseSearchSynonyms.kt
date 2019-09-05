package com.algolia.search.model.response

import com.algolia.search.model.response.ResponseSearchSynonyms.Hit
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.serialize.*
import kotlinx.serialization.*
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