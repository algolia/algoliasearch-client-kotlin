package com.algolia.search.model.search

import com.algolia.search.model.Attribute
import com.algolia.search.model.Raw
import com.algolia.search.serialize.regexSnippet
import com.algolia.search.toAttribute
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(Snippet.Companion::class)
data class Snippet(
    val attribute: Attribute,
    val count: Int? = null
) : Raw<String> {

    override val raw = attribute.raw + if (count != null) ":$count" else ""

    override fun toString(): String {
        return raw
    }

    companion object : KSerializer<Snippet> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: Snippet) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): Snippet {
            val string = serializer.deserialize(decoder)

            val findSnippet = regexSnippet.find(string)

            return when {
                findSnippet != null -> Snippet(
                    findSnippet.groupValues[1].toAttribute(),
                    findSnippet.groupValues[2].toInt()
                )
                else -> Snippet(string.toAttribute())
            }
        }
    }
}