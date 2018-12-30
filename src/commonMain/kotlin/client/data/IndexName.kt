package client.data

import client.StringUTF8
import client.serialize.Deserializer
import client.serialize.RawStringSerializer
import client.toIndex
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive


data class IndexName(
    override val raw: String
) : RawString {

    fun encode(): StringUTF8 {
        return StringUTF8.encode(raw)
    }

    override fun toString(): String {
        return raw
    }

    companion object : RawStringSerializer<IndexName>, Deserializer<IndexName> {

        override fun deserialize(element: JsonElement): IndexName? {
            return when (element) {
                is JsonPrimitive -> element.contentOrNull?.toIndex()
                else -> null
            }
        }
    }
}