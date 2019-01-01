package com.algolia.search.saas.serialize

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.content
import kotlinx.serialization.json.jsonArray

internal object ListStringSerializer : Serializer<List<String>>, Deserializer<List<String>> {

    override fun serialize(input: List<String>): JsonArray {
        return jsonArray { input.forEach { +it } }
    }

    override fun deserialize(element: JsonElement): List<String>? {
        return when (element) {
            is JsonArray -> element.map { it.content }
            else -> null
        }
    }
}