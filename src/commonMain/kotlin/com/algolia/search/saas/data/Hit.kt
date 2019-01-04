package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KSerializerHighlights
import com.algolia.search.saas.serialize.asJsonInput
import kotlinx.serialization.*
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.json


@Serializable(Hit.Companion::class)
data class Hit(
    @Transient val json: JsonElement = json { },
    @Optional
    @SerialName("_highlightResult")
    @Serializable(KSerializerHighlights::class)
    val highlightResult: Map<Attribute, HighlightResult>? = null
) {

    @Serializer(Hit::class)
    companion object : KSerializer<Hit> {

        override fun deserialize(input: Decoder): Hit {
            val element = input.asJsonInput()
            val highlightResult = element.jsonObject.getObjectOrNull("_highlightResult")
            val parse = highlightResult?.let { JSON.nonstrict.parse(KSerializerHighlights, it.toString()) }

            return Hit(element, parse)
        }
    }
}