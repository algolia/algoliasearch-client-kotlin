package client.serialize

import client.data.RawFloats
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray


internal interface RawFloatsSerializer<T : RawFloats> : Serializer<T> {

    override fun serialize(input: T): JsonArray {
        return jsonArray { input.raw.forEach { +(it as Number) } }
    }
}