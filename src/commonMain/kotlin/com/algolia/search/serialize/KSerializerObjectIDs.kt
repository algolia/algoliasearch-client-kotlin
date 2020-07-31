package com.algolia.search.serialize

import com.algolia.search.helper.toObjectID
import com.algolia.search.model.ObjectID
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

public object KSerializerObjectIDs : KSerializer<List<ObjectID>> {

    override val descriptor: SerialDescriptor = ObjectID.descriptor

    override fun serialize(encoder: Encoder, value: List<ObjectID>) {
        val json = buildJsonArray {
            value.forEach {
                add(buildJsonObject { put(KeyObjectID, it.raw) })
            }
        }
        encoder.asJsonOutput().encodeJsonElement(json)
    }

    override fun deserialize(decoder: Decoder): List<ObjectID> {
        return decoder.asJsonInput().jsonArray.map { it.jsonObject.getValue(KeyObjectID).jsonPrimitive.content.toObjectID() }
    }
}
