package com.algolia.search.serialize

import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.search.Query
import com.algolia.search.toIndexName
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.SerialClassDescImpl
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.content
import kotlinx.serialization.json.int
import kotlinx.serialization.json.json


internal object KSerializerVariant : KSerializer<Variant> {

    override val descriptor = SerialClassDescImpl("variant")

    override fun serialize(encoder: Encoder, obj: Variant) {
        val json = json {
            KeyIndexName to obj.indexName.raw
            KeyPercentage to obj.trafficPercentage
            obj.customSearchParameters?.let {
                KeyCustomSearchParameters to Json.noDefaults.toJson(Query.serializer(), it)
            }
        }
        encoder.asJsonOutput().encodeJson(json)
    }

    override fun deserialize(decoder: Decoder): Variant {
        val json = decoder.asJsonInput().jsonObject
        val customSearchParameters = json.getObjectOrNull(KeyCustomSearchParameters)

        return Variant(
            indexName = json[KeyIndexName].content.toIndexName(),
            trafficPercentage = json[KeyPercentage].int,
            customSearchParameters = customSearchParameters?.let { Json.noDefaults.fromJson(Query.serializer(), it) }
        )
    }
}