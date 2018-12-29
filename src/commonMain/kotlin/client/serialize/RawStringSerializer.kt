package client.serialize

import client.data.RawString
import kotlinx.serialization.json.JsonPrimitive


internal interface RawStringSerializer<T : RawString> : Serializer<T> {

    override fun serialize(input: T): JsonPrimitive {
        return JsonPrimitive(input.raw)
    }
}