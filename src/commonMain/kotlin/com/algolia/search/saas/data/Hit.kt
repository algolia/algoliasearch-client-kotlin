package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.asJsonInput
import kotlinx.serialization.Decoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.JsonElement


@Serializable
data class Hit(
    val serialized: String,
    val element: JsonElement
) {

    @Serializer(Hit::class)
    companion object : KSerializer<Hit> {
        override fun deserialize(input: Decoder): Hit {
            val json = input.asJsonInput()

            return Hit(json.toString(), json)
        }
    }
}