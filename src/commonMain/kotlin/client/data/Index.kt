package client.data

import client.StringUTF8
import client.serialize.Deserializer
import client.serialize.RawStringSerializer
import client.toIndex
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive


data class Index(
    override val raw: String
) : RawString {

    fun encode(): StringUTF8 {
        return StringUTF8.encode(raw)
    }

    override fun toString(): String {
        return raw
    }

    companion object : RawStringSerializer<Index>, Deserializer<Index> {

        override fun deserialize(element: JsonElement): Index? {
            return when (element) {
                is JsonPrimitive -> element.contentOrNull?.toIndex()
                else -> null
            }
        }
    }
}