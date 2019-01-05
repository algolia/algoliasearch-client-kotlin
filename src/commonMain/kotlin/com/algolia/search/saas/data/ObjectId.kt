package com.algolia.search.saas.data

import com.algolia.search.saas.toObjectId
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer


@Serializable(ObjectId.Companion::class)
data class ObjectId(@SerialName("objectID") override val raw: String) : Raw<String> {

    override fun toString(): String {
        return raw
    }

    companion object : KSerializer<ObjectId> {

        override val descriptor = StringSerializer.descriptor

        override fun serialize(encoder: Encoder, obj: ObjectId) {
            StringSerializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): ObjectId {
            val string = StringSerializer.deserialize(decoder)

            return string.toObjectId()
        }
    }
}