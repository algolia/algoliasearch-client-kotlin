package client.data

import client.serialize.Deserializer
import client.serialize.Raw
import client.serialize.RawSerializer
import client.toAttribute
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.contentOrNull


data class Attribute(override val raw: String) : Raw {

    override fun toString(): String {
        return raw
    }

    internal companion object : RawSerializer<Attribute>, Deserializer<Attribute> {

        override fun deserialize(element: JsonElement): Attribute? {
            return element.contentOrNull?.toAttribute()
        }
    }
}