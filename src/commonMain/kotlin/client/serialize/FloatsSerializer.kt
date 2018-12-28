package client.serialize

import kotlinx.serialization.json.JsonArray


internal interface FloatsSerializer<T : Floats> : Serializer<T> {

    override fun serialize(input: T): JsonArray {
        return ListSerializer.serialize(input.asList)
    }
}