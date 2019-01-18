package com.algolia.search.saas.data

import com.algolia.search.saas.StringUTF8
import com.algolia.search.saas.toIndexName
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer


@Serializable(IndexName.Companion::class)
data class IndexName(
    override val raw: String
) : Raw<String> {

    private fun encode(): StringUTF8 {
        return StringUTF8.encode(raw)
    }

    internal fun pathIndexes(suffix: String? = null): String {
        return "/1/indexes/${encode().string}" + (suffix ?: "")
    }

    override fun toString(): String {
        return raw
    }

    companion object : KSerializer<IndexName> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: IndexName) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): IndexName {
            return serializer.deserialize(decoder).toIndexName()
        }
    }
}