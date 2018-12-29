package client.data

import client.serialize.Deserializer
import client.serialize.RawStringSerializer
import client.toAttribute
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive


data class Attribute(override val raw: String) : RawString {

    override fun toString(): String {
        return raw
    }

    internal companion object : RawStringSerializer<Attribute>, Deserializer<Attribute> {

        override fun deserialize(element: JsonElement): Attribute? {
            return when (element) {
                is JsonPrimitive -> element.contentOrNull?.toAttribute()
                else -> null
            }
        }
    }
}