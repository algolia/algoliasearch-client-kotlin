package client.serialize

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray


internal interface Serializer<T> {

    fun serialize(input: T): JsonElement
    fun serializes(input: List<T>): JsonArray {
        return jsonArray { input.forEach { +serialize(it) } }
    }
}