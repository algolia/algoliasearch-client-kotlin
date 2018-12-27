package client.data

import client.serialize.Serializer
import client.serialize.unwrap
import client.toAttribute
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull


data class Attribute(val name: String) {

    override fun toString(): String {
        return name
    }

    internal companion object : Serializer<Attribute> {

        override fun serialize(input: Attribute?): JsonElement {
            return input.unwrap { JsonPrimitive(name) }
        }

        override fun deserialize(element: JsonElement): Attribute? {
            return element.contentOrNull?.toAttribute()
        }
    }
}