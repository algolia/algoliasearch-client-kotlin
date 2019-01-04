package com.algolia.search.saas.data

import com.algolia.search.saas.StringUTF8
import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.serialize.asJsonOutput
import com.algolia.search.saas.toIndex
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable(IndexName.Companion::class)
data class IndexName(
    override val raw: String
) : Raw<String> {

    private fun encode(): StringUTF8 {
        return StringUTF8.encode(raw)
    }

    internal fun pathIndexes(suffix: String? = null): String {
        return "/1/indexes/${encode().string}" + (suffix ?: "")
    }

    override fun toString(): String {
        return raw
    }

    @Serializer(IndexName::class)
    companion object : KSerializer<IndexName> {

        override fun serialize(output: Encoder, obj: IndexName) {
            output.asJsonOutput().writeTree(JsonPrimitive(obj.raw))
        }

        override fun deserialize(input: Decoder): IndexName {
            val element = input.asJsonInput() as JsonLiteral

            return element.content.toIndex()
        }
    }
}