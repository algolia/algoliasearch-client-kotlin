package com.algolia.search.model.response

import com.algolia.search.model.response.ResponseSearchRules.Hit
import com.algolia.search.model.rule.Rule
import com.algolia.search.serialize.internal.JsonNonStrict
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.jsonObjectOrNull
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

@Serializable
public data class ResponseSearchRules(

    /**
     * A list of [Hit].
     */
    @SerialName(Key.Hits) val hits: List<Hit>,

    /**
     *  Number of hits or null.
     */
    @SerialName(Key.NbHits) val nbHitsOrNull: Int? = null,

    /**
     * Returned page number or null.
     */
    @SerialName(Key.Page) val pageOrNull: Int? = null,

    /**
     * Total number of pages or null.
     */
    @SerialName(Key.NbPages) val nbPagesOrNull: Int? = null
) {

    /**
     *  Number of hits.
     */
    val nbHits: Int get() = requireNotNull(nbHitsOrNull)

    /**
     * Returned page number.
     */
    val page: Int get() = requireNotNull(pageOrNull)

    /**
     * Total number of pages.
     */
    val nbPages: Int get() = requireNotNull(nbPagesOrNull)

    @Serializable(Hit.Companion::class)
    public data class Hit(
        val rule: Rule,
        val highlightResultOrNull: JsonObject? = null
    ) {

        public val highlightResult: JsonObject
            get() = highlightResultOrNull!!

        public companion object : KSerializer<Hit> {

            override val descriptor: SerialDescriptor = Rule.serializer().descriptor

            override fun deserialize(decoder: Decoder): Hit {
                val json = decoder.asJsonInput().jsonObject
                val rule = JsonNonStrict.decodeFromJsonElement(Rule.serializer(), json)
                val highlightResult = json[Key._HighlightResult]?.jsonObjectOrNull
                return Hit(rule, highlightResult)
            }

            override fun serialize(encoder: Encoder, value: Hit) {
                throw UnsupportedOperationException("ResponseSearchRules.Hit serialization is not an expected operation")
            }
        }
    }
}
