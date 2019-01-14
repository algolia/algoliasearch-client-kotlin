package com.algolia.search.saas.serialize

import com.algolia.search.saas.data.APIKeyCreate
import com.algolia.search.saas.data.Query
import com.algolia.search.saas.data.Settings
import io.ktor.http.Parameters
import io.ktor.http.formUrlEncode
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.*

internal val regexAsc = Regex("$KeyAsc\\((.*)\\)")
internal val regexDesc = Regex("$KeyDesc\\((.*)\\)")
internal val regexEqualOnly = Regex("$KeyEqualOnly\\((.*)\\)")
internal val regexSnippet = Regex("(.*):(\\d+)")

internal fun JsonObject.urlEncode(): String {
    return Parameters.build {
        entries.forEach { (key, element) ->
            when (element) {
                is JsonArray -> appendAll(key, element.content.map { it.content })
                else -> append(key, element.content)
            }
        }
    }.formUrlEncode()
}

internal fun Decoder.asJsonInput() = (this as JsonInput).decodeJson()
internal fun Encoder.asJsonOutput() = this as JsonOutput


internal fun <T> T.toJsonObject(serializer: SerializationStrategy<T>): JsonObject {
    return Json.nonstrict.toJson(this, serializer).jsonObject
}

internal fun JsonObject.encodeNoNulls(): JsonObject {
    return JsonObject(filter { it.value != JsonNull })
}

internal fun Query.encodeNoNulls(): JsonObject {
    return toJsonObject(Query.serializer()).encodeNoNulls()
}

internal fun Settings.encodeNoNulls(): JsonObject {
    return toJsonObject(Settings.serializer()).encodeNoNulls()
}

internal fun APIKeyCreate.encodeNoNulls(): JsonObject {
    return toJsonObject(APIKeyCreate.serializer()).encodeNoNulls()
}