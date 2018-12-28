package client.serialize

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive


internal interface RawSerializer<T : Raw> : Serializer<T> {

    override fun serialize(input: T): JsonElement {
        return JsonPrimitive(input.raw)
    }
}