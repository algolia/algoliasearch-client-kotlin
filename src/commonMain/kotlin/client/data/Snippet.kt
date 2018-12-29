package client.data

import client.serialize.Deserializer
import client.serialize.RawStringSerializer
import client.serialize.regexSnippet
import client.toAttribute
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive


data class Snippet(
    val attribute: Attribute,
    val count: Int? = null
) : RawString {

    override val raw = attribute.raw + if (count != null) ":$count" else ""

    override fun toString(): String {
        return raw
    }

    internal companion object : RawStringSerializer<Snippet>, Deserializer<Snippet> {

        override fun deserialize(element: JsonElement): Snippet? {
            return when (element) {
                is JsonPrimitive -> {
                    element.contentOrNull?.let {
                        val findSnippet = regexSnippet.find(it)

                        when {
                            findSnippet != null -> Snippet(
                                findSnippet.groupValues[1].toAttribute(),
                                findSnippet.groupValues[2].toInt()
                            )
                            else -> Snippet(it.toAttribute())
                        }
                    }
                }
                else -> null
            }
        }
    }
}