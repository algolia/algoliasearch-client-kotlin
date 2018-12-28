package client.data

import client.serialize.Serializer
import client.serialize.unwrap
import client.toAttribute
import kotlinx.serialization.json.*


data class DecompoundedAttributes internal constructor(val language: QueryLanguage, val attributes: List<Attribute>) {

    constructor(language: QueryLanguage.Finnish, vararg attributes: Attribute) : this(language, attributes.toList())
    constructor(language: QueryLanguage.German, vararg attributes: Attribute) : this(language, attributes.toList())
    constructor(language: QueryLanguage.Dutch, vararg attributes: Attribute) : this(language, attributes.toList())

    internal companion object : Serializer<DecompoundedAttributes> {

        override fun serialize(input: DecompoundedAttributes?): JsonElement {
            return input.unwrap {
                json {
                    language.raw to jsonArray {
                        attributes.forEach { +it.name }
                    }
                }
            }
        }

        override fun deserialize(element: JsonElement): DecompoundedAttributes? {
            return when (element) {
                is JsonObject -> {
                    val key = element.keys.first()
                    val attributes = element.getArrayOrNull(key)?.map { it.content.toAttribute() }

                    return DecompoundedAttributes(
                        QueryLanguage.deserialize(JsonPrimitive(key))!!,
                        attributes ?: listOf()
                    )
                }
                else -> null
            }
        }
    }
}