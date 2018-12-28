package client.serialize

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement

internal interface Deserializer<T> {

    fun deserialize(element: JsonElement): T?
    fun deserializeList(element: JsonElement): List<T>? {
        return when (element) {
            is JsonArray -> {
                if (element.isNotEmpty()) {
                    mutableListOf<T>().apply {
                        element.jsonArray.forEach { deserialize(it)?.let(::add) }
                    }
                } else null
            }
            else -> null
        }
    }
}