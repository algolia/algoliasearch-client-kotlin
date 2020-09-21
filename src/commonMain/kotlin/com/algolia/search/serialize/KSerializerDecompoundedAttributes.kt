package com.algolia.search.serialize

import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Language
import com.algolia.search.model.settings.DecompoundedAttributes
import com.algolia.search.serialize.internal.Json
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.JsonNonStrict
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

public object KSerializerDecompoundedAttributes : KSerializer<List<DecompoundedAttributes>> {

    private val serializer = MapSerializer(Language, ListSerializer(Attribute))

    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun deserialize(decoder: Decoder): List<DecompoundedAttributes> {
        val json = decoder.asJsonInput().jsonObject

        return json.map {
            val language = JsonNonStrict.decodeFromString(Language.serializer(), it.key)
            val attributes =
                JsonNoDefaults.decodeFromJsonElement(ListSerializer(Attribute.serializer()), it.value.jsonArray)

            DecompoundedAttributes(language, attributes)
        }
    }

    override fun serialize(encoder: Encoder, value: List<DecompoundedAttributes>) {
        val json = buildJsonObject {
            value.forEach {
                put(it.language.raw, Json.encodeToJsonElement(ListSerializer(Attribute), it.attributes))
            }
        }

        encoder.asJsonOutput().encodeJsonElement(json)
    }
}
