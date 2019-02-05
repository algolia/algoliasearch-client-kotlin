package com.algolia.search.serialize

import com.algolia.search.model.search.Query
import com.algolia.search.model.search.RankingInfo
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.request.RequestAPIKey
import io.ktor.http.Parameters
import io.ktor.http.formUrlEncode
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.json.*

internal val regexAsc = Regex("$KeyAsc\\((.*)\\)")
internal val regexDesc = Regex("$KeyDesc\\((.*)\\)")
internal val regexEqualOnly = Regex("$KeyEqualOnly\\((.*)\\)")
internal val regexSnippet = Regex("(.*):(\\d+)")

internal fun JsonObject.merge(jsonObject: JsonObject): JsonObject {
    return toMutableMap().run {
        putAll(jsonObject.content)
        JsonObject(this)
    }
}

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


internal fun Query.toJsonNoDefaults(): JsonObject {
    return JsonNoNulls.toJson(Query.serializer(), this).jsonObject
}

internal fun Settings.toJsonNoDefaults(): JsonObject {
    return JsonNoNulls.toJson(Settings.serializer(), this).jsonObject
}

internal fun RequestAPIKey.stringify(): String {
    return JsonNoNulls.stringify(RequestAPIKey.serializer(), this)
}

internal fun JsonObject.toHighlights() = Json.plain.fromJson(KSerializerHighlights, this)

internal fun JsonObject.toSnippets() = Json.plain.fromJson(KSerializerSnippets, this)

internal fun JsonObject.toRankingInfo() = Json.plain.fromJson(RankingInfo.serializer(), this)

internal val JsonNoNulls = Json(encodeDefaults = false)