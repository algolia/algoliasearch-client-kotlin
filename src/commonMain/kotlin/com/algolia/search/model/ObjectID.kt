package com.algolia.search.model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.toObjectID
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer


@Serializable(ObjectID.Companion::class)
data class ObjectID(@SerialName(KeyObjectID) override val raw: String) : Raw<String> {

    init {
        if (raw.isBlank()) {
            throw EmptyStringException(KeyObjectID)
        }
    }

    override fun toString(): String {
        return raw
    }

    companion object : KSerializer<ObjectID> {

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