package client.serialize

import kotlinx.serialization.json.JsonElement


internal interface FloatsSerializer<T : Floats> : Serializer<T> {

    override fun serialize(input: T): JsonElement {
        return ListSerializer.serialize(input.asList)
    }
}