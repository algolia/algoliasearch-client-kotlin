package client.data

import client.serialize.Deserializer
import client.serialize.Raw
import client.serialize.RawSerializer
import client.serialize.regexSnippet
import client.toAttribute
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.content
import kotlinx.serialization.json.contentOrNull


data class Snippet(
    val attribute: Attribute,
    val count: Int? = null
) : Raw {

    override val raw = attribute.raw + if (count != null) ":$count" else ""

    override fun toString(): String {
        return raw
    }

    internal companion object : RawSerializer<Snippet>, Deserializer<Snippet> {

        override fun deserialize(element: JsonElement): Snippet? {
            return when (val content = element.contentOrNull) {
                null -> null
                else -> {
                    val findSnippet = regexSnippet.find(content)

                    when {
                        findSnippet != null -> Snippet(
                            findSnippet.groupValues[1].toAttribute(),
                            findSnippet.groupValues[2].toInt()
                        )
                        else -> Snippet(element.content.toAttribute())
                    }
                }
            }
        }
    }
}