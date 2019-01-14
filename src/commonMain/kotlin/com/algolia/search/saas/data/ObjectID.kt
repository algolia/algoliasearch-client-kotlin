package com.algolia.search.saas.data

import com.algolia.search.saas.toObjectID
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer


@Serializable(ObjectID.Companion::class)
data class ObjectID(@SerialName("objectID") override val raw: String) : Raw<String> {

    override fun toString(): String {
        return raw
    }

    internal companion object : KSerializer<ObjectID> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: ObjectID) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): ObjectID {
            val string = serializer.deserialize(decoder)

            return string.toObjectID()
        }
    }
}