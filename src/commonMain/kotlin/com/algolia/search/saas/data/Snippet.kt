package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.serialize.regexSnippet
import com.algolia.search.saas.toAttribute
import kotlinx.serialization.*
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable(Snippet.Companion::class)
data class Snippet(
    val attribute: Attribute,
    val count: Int? = null
) : RawString {

    override val raw = attribute.raw + if (count != null) ":$count" else ""

    override fun toString(): String {
        return raw
    }

    @Serializer(Snippet::class)
    internal companion object : KSerializer<Snippet> {

        override fun serialize(output: Encoder, obj: Snippet) {
            val json = output as JSON.JsonOutput

            json.writeTree(JsonPrimitive(obj.raw))
        }

        override fun deserialize(input: Decoder): Snippet {
            val element = input.asJsonInput() as JsonLiteral

            val findSnippet = regexSnippet.find(element.content)

            return when {
                findSnippet != null -> Snippet(
                    findSnippet.groupValues[1].toAttribute(),
                    findSnippet.groupValues[2].toInt()
                )
                else -> Snippet(element.content.toAttribute())
            }
        }
    }
}