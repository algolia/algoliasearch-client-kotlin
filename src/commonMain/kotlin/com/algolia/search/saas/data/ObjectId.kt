package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.serialize.asJsonOutput
import com.algolia.search.saas.toObjectId
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable(ObjectId.Companion::class)
data class ObjectId(@SerialName("objectID") override val raw: String) : Raw<String> {

    override fun toString(): String {
        return raw
    }

    @Serializer(ObjectId::class)
    companion object : KSerializer<ObjectId> {

        override fun serialize(output: Encoder, obj: ObjectId) {
            output.asJsonOutput().writeTree(JsonPrimitive(obj.raw))
        }

        override fun deserialize(input: Decoder): ObjectId {
            val element = input.asJsonInput() as JsonLiteral

            return element.content.toObjectId()
        }
    }
}