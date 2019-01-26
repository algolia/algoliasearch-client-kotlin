package com.algolia.search.serialize

import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Highlight
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.HashMapSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject


object KSerializerHighlights :
    KSerializer<Map<Attribute, Highlight>> by HashMapSerializer(Attribute, Highlight.serializer()) {

    fun fromJson(jsonObject: JsonObject): Map<Attribute, Highlight> {
        return Json.plain.fromJson(KSerializerHighlights, jsonObject)
    }
}