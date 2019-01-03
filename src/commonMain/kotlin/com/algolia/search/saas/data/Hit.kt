package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.serialize.asJsonOutput
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonLiteral


@Serializable(Hit.Companion::class)
data class Hit(
    val serialized: String,
    val element: JsonElement
) {

    @Serializer(Hit::class)
    companion object : KSerializer<Hit> {

        override fun serialize(output: Encoder, obj: Hit) {
            output.asJsonOutput().writeTree(JsonLiteral(obj.serialized))
        }

        override fun deserialize(input: Decoder): Hit {
            val json = input.asJsonInput()

            return Hit(json.toString(), json)
        }
    }
}