package client.serialize

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray

internal object ListStringSerializer : Serializer<List<String>> {

    override fun serialize(input: List<String>?): JsonElement {
        return input.unwrap { jsonArray { forEach { +it } } }
    }
}