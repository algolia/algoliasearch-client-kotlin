package com.algolia.search.serialize

import com.algolia.search.helper.toIndexName
import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.search.Query
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

public object KSerializerVariant : KSerializer<Variant> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("variant")

    override fun serialize(encoder: Encoder, value: Variant) {
        val json = buildJsonObject {
            put(KeyIndexName, value.indexName.raw)
            put(KeyPercentage, value.trafficPercentage)
            value.customSearchParameters?.let {
                put(
                    KeyCustomSearchParameters,
                    JsonNoDefaults.encodeToJsonElement(Query.serializer(), it)
                )
            }
        }
        encoder.asJsonOutput().encodeJsonElement(json)
    }

    override fun deserialize(decoder: Decoder): Variant {
        val json = decoder.asJsonInput().jsonObject
        val customSearchParameters = json[KeyCustomSearchParameters]?.jsonObjectOrNull

        return Variant(
            indexName = json.getValue(KeyIndexName).jsonPrimitive.content.toIndexName(),
            trafficPercentage = json.getValue(KeyPercentage).jsonPrimitive.int,
            customSearchParameters = customSearchParameters?.let {
                JsonNoDefaults.decodeFromJsonElement(Query.serializer(), it)
            }
        )
    }
}
