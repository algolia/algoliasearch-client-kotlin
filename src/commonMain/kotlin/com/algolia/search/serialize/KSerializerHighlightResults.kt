package com.algolia.search.serialize

import com.algolia.search.model.Attribute
import com.algolia.search.model.search.HighlightResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.HashMapSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject


internal object KSerializerHighlightResults :
    KSerializer<Map<Attribute, HighlightResult>> by HashMapSerializer(Attribute, HighlightResult.serializer()) {

    fun fromJson(jsonObject: JsonObject): Map<Attribute, HighlightResult> {
        return Json.plain.fromJson(KSerializerHighlightResults, jsonObject)
    }
}