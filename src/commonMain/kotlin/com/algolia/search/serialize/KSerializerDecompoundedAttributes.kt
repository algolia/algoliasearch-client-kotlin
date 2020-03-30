package com.algolia.search.serialize

import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Language
import com.algolia.search.model.settings.DecompoundedAttributes
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.json

public object KSerializerDecompoundedAttributes : KSerializer<List<DecompoundedAttributes>> {

    private val serializer = MapSerializer(Language, Attribute.list)

    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun deserialize(decoder: Decoder): List<DecompoundedAttributes> {
        val json = decoder.asJsonInput().jsonObject

        return json.content.map {
            val language = Json.parse(Language.serializer(), it.key)
            val attributes = JsonNoDefaults.fromJson(Attribute.serializer().list, it.value.jsonArray)

            DecompoundedAttributes(language, attributes)
        }
    }

    override fun serialize(encoder: Encoder, value: List<DecompoundedAttributes>) {
        val json = json {
            value.forEach {
                it.language.raw to Json.toJson(Attribute.list, it.attributes)
            }
        }

        encoder.asJsonOutput().encodeJson(json)
    }
}
