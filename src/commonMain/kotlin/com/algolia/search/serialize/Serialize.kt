package com.algolia.search.serialize

import com.algolia.search.model.request.RequestAPIKey
import com.algolia.search.model.search.Query
import com.algolia.search.model.search.RankingInfo
import com.algolia.search.model.settings.Settings
import io.ktor.http.Parameters
import io.ktor.http.formUrlEncode
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.*
import kotlinx.serialization.list

internal val regexAsc = Regex("^$KeyAsc\\((.*)\\)$")
internal val regexDesc = Regex("^$KeyDesc\\((.*)\\)$")
internal val regexEqualOnly = Regex("^$KeyEqualOnly\\((.*)\\)$")
internal val regexSnippet = Regex("^(.*):(\\d+)$")
internal val regexOrdered = Regex("^$KeyOrdered\\((.*)\\)$")
internal val regexUnordered = Regex("^$KeyUnordered\\((.*)\\)$")
internal val regexFilterOnly = Regex("^$KeyFilterOnly\\((.*)\\)$")
internal val regexSearchable = Regex("^$KeySearchable\\((.*)\\)$")
internal val regexFacet = Regex("^\\{facet:(.*)}$")
internal val regexPlaceholder = Regex("^<(.*)>$")
internal val regexPoint = Regex("^(.*),(.*)$")
internal val regexUserToken = Regex("^[a-zA-Z0-9_-]*$")

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
                is JsonArray -> append(key, Json.stringify(StringSerializer.list, element.content.map { it.content }))
                else -> append(key, element.content)
            }
        }
    }.formUrlEncode()
}

internal fun Decoder.asJsonInput() = (this as JsonInput).decodeJson()
internal fun Encoder.asJsonOutput() = this as JsonOutput


internal fun Query.toJsonNoDefaults(): JsonObject {
    return Json.noDefaults.toJson(Query.serializer(), this).jsonObject
}

internal fun Settings.toJsonNoDefaults(): JsonObject {
    return Json.noDefaults.toJson(Settings.serializer(), this).jsonObject
}

internal fun RequestAPIKey.stringify(): String {
    return Json.noDefaults.stringify(RequestAPIKey.serializer(), this)
}

internal fun JsonObject.toHighlightResults() = Json.plain.fromJson(KSerializerHighlightResults, this)

internal fun JsonObject.toSnippetResults() = Json.plain.fromJson(KSerializerSnippetResults, this)

internal fun JsonObject.toRankingInfo() = Json.plain.fromJson(RankingInfo.serializer(), this)

internal val Json.Companion.noDefaults get() = Json(encodeDefaults = false)