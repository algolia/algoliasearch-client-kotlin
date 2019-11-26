package com.algolia.search.serialize

import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Language
import com.algolia.search.model.settings.DecompoundedAttributes
import kotlinx.serialization.*
import kotlinx.serialization.internal.HashMapSerializer
import kotlinx.serialization.json.json


public object KSerializerDecompoundedAttributes : KSerializer<List<DecompoundedAttributes>> {

    private val serializer = HashMapSerializer(Language, Attribute.list)

    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun deserialize(decoder: Decoder): List<DecompoundedAttributes> {
        val json = decoder.asJsonInput().jsonObject

        return json.content.map {
            val language = Json.parse(Language.serializer(), it.key)
            val attributes = JsonNoDefaults.fromJson(Attribute.serializer().list, it.value.jsonArray)

            DecompoundedAttributes(language, attributes)
        }
    }

    override fun serialize(encoder: Encoder, obj: List<DecompoundedAttributes>) {
        val json = json {
            obj.forEach {
                it.language.raw to Json.toJson(Attribute.list, it.attributes)
            }
        }

        encoder.asJsonOutput().encodeJson(json)
    }
}