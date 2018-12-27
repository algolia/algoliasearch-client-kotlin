package client.serialize

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.jsonArray


internal interface Serializer<T> {

    fun serialize(input: T?): JsonElement
    fun serializes(input: List<T>?): JsonElement {
        return if (input != null) jsonArray { input.forEach { +serialize(it) } } else JsonNull
    }

    fun deserialize(element: JsonElement): T?
    fun deserializes(element: JsonElement): List<T>? {
        return if (element.isNull) null else element.jsonArray.map { deserialize(it)!! }
    }
}