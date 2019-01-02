package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.toAttribute
import kotlinx.serialization.*
import kotlinx.serialization.json.*


@Serializable(DecompoundedAttributes.Companion::class)
data class DecompoundedAttributes internal constructor(val language: QueryLanguage, val attributes: List<Attribute>) {

    constructor(language: QueryLanguage.Finnish, vararg attributes: Attribute) : this(language, attributes.toList())
    constructor(language: QueryLanguage.German, vararg attributes: Attribute) : this(language, attributes.toList())
    constructor(language: QueryLanguage.Dutch, vararg attributes: Attribute) : this(language, attributes.toList())

    @Serializer(DecompoundedAttributes::class)
    internal companion object : KSerializer<DecompoundedAttributes> {

        override fun serialize(output: Encoder, obj: DecompoundedAttributes) {
            val json = output as JSON.JsonOutput
            val element = json {
                obj.language.raw to jsonArray {
                    obj.attributes.forEach { +it.raw }
                }
            }

            json.writeTree(element)
        }

        override fun deserialize(input: Decoder): DecompoundedAttributes {
            val element = input.asJsonInput() as JsonObject
            val key = element.keys.first()
            val attributes = element.getArrayOrNull(key)?.map { it.content.toAttribute() }

            return DecompoundedAttributes(
                QueryLanguage.convert(key),
                attributes ?: listOf()
            )
        }
    }
}