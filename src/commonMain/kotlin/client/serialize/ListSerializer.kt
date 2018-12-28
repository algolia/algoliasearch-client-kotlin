package client.serialize

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray

internal object ListSerializer : Serializer<List<Any>> {

    override fun serialize(input: List<Any>): JsonArray {
        return jsonArray {
            input.forEach {
                when (it) {
                    is Number -> +it
                    is String -> +it
                    is Boolean -> +it
                    is JsonElement -> +it
                }
            }
        }
    }
}