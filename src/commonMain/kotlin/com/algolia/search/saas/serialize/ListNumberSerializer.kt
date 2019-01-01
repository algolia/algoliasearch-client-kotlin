package com.algolia.search.saas.serialize

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray


internal object ListNumberSerializer : Serializer<List<Number>> {

    override fun serialize(input: List<Number>): JsonArray {
        return jsonArray { input.forEach { +it } }
    }
}