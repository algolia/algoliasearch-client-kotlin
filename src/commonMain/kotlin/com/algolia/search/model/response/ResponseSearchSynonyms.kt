package com.algolia.search.model.response

import com.algolia.search.model.Attribute
import com.algolia.search.model.response.ResponseSearchSynonyms.Hit
import com.algolia.search.model.search.HighlightResult
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.serialize.*
import kotlinx.serialization.*


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
    data class Hit(
        /**
         * The [Synonym].
         */
        val synonym: Synonym,
        /**
         *  Contains the highlighted [Synonym].
         */
        val highlightResultOrNull: Map<Attribute, List<HighlightResult>>? = null
    ) {

        @Transient
        public val highlights: Map<Attribute, List<HighlightResult>>
            get() = highlightResultOrNull!!

        @Serializer(Hit::class)
        companion object : DeserializationStrategy<Hit> {

            override fun deserialize(decoder: Decoder): Hit {
                val json = decoder.asJsonInput().jsonObject
                val synonym = Json.fromJson(Synonym.serializer(), json)
                val highlights = json.getObjectOrNull(Key_HighlightResult)?.let {
                    Json.fromJson(KSerializerHighlightResults, it)
                }

                return Hit(synonym, highlights)
            }
        }
    }
}