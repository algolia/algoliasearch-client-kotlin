package client.serialize

import kotlinx.serialization.json.JsonElement

internal interface Deserializer<T> {

    fun deserialize(element: JsonElement): T?
    fun deserializes(element: JsonElement): List<T>? {
        return if (element.isNull) null else element.jsonArray.map { deserialize(it)!! }
    }
}