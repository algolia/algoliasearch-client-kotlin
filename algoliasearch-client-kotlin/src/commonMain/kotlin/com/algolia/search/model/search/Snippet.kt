package com.algolia.search.model.search

import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Attribute
import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.internal.regexSnippet
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(Snippet.Companion::class)
public data class Snippet(
    /**
     * Attribute to snippet.
     * Use "*" to snippet all attributes.
     */
    val attribute: Attribute,
    /**
     * Optional word count.
     * Engine default: 10
     */
    val count: Int? = null
) : Raw<String> {

    override val raw: String = attribute.raw + if (count != null) ":$count" else ""

    override fun toString(): String {
        return raw
    }

    public companion object : KSerializer<Snippet> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: Snippet) {
            serializer.serialize(encoder, value.raw)
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
