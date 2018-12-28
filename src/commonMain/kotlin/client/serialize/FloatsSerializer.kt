package client.serialize

import client.data.Floats
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray


internal interface FloatsSerializer<T : Floats> : Serializer<T> {

    override fun serialize(input: T): JsonElement {
        return jsonArray {
            input.asList.forEach {
                +(it as Number)
            }
        }
    }
}