package client.data

import client.StringUTF8
import client.serialize.Deserializer
import client.serialize.RawStringSerializer
import client.toIndex
import kotlinx.serialization.Decoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive


data class IndexName(
    override val raw: String
) : RawString {

    private fun encode(): StringUTF8 {
        return StringUTF8.encode(raw)
    }

    internal fun pathIndexes(suffix: String = ""): String {
        return "/1/indexes/${encode().string}" + suffix
    }

    override fun toString(): String {
        return raw
    }

    @kotlinx.serialization.Serializer(forClass = IndexName::class)
    object Deserializable : KSerializer<IndexName> {

        override fun deserialize(input: Decoder): IndexName {
            val json = input as JSON.JsonInput

            return IndexName(json.readAsTree().toString())
        }
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