package com.algolia.search.model

import com.algolia.search.client.Index
import com.algolia.search.exception.EmptyStringException
import com.algolia.search.helper.internal.encodeUTF8
import com.algolia.search.helper.toIndexName
import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.internal.Route
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * [IndexName] of an [Index]. Can't be a blank or empty string.
 */
@Serializable(IndexName.Companion::class)
public data class IndexName(
    override val raw: String
) : Raw<String> {

    init {
        if (raw.isBlank()) throw EmptyStringException("IndexName")
    }

    internal fun encode(): String {
        return raw.encodeUTF8()
    }

    internal fun toPath(suffix: String? = null): String {
        return "${Route.IndexesV1}/${encode()}" + (suffix ?: "")
    }

    override fun toString(): String {
        return raw
    }

    public companion object : KSerializer<IndexName> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: IndexName) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): IndexName {
            return serializer.deserialize(decoder).toIndexName()
        }
    }
}
