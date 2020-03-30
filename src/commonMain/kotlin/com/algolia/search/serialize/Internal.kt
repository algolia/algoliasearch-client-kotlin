@file:Suppress("RegExpRedundantEscape")

package com.algolia.search.serialize

import com.algolia.search.model.indexing.DeleteByQuery
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.model.request.RequestAPIKey
import com.algolia.search.model.request.RequestMultipleQueries
import com.algolia.search.model.search.Query
import com.algolia.search.model.settings.Settings
import io.ktor.http.Parameters
import io.ktor.http.formUrlEncode
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.json.JsonElementSerializer
import kotlinx.serialization.json.JsonInput
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonOutput
import kotlinx.serialization.json.JsonPrimitive

internal val regexAsc = Regex("^$KeyAsc\\((.*)\\)$")
internal val regexDesc = Regex("^$KeyDesc\\((.*)\\)$")
internal val regexEqualOnly = Regex("^$KeyEqualOnly\\((.*)\\)$")
internal val regexSnippet = Regex("^(.*):(\\d+)$")
internal val regexOrdered = Regex("^$KeyOrdered\\((.*)\\)$")
internal val regexUnordered = Regex("^$KeyUnordered\\((.*)\\)$")
internal val regexFilterOnly = Regex("^$KeyFilterOnly\\((.*)\\)$")
internal val regexSearchable = Regex("^$KeySearchable\\((.*)\\)$")
internal val regexFacet = Regex("^\\{facet:(.*)\\}$")
internal val regexPlaceholder = Regex("^<(.*)>$")
internal val regexPoint = Regex("^(.*),(.*)$")
internal val regexUserToken = Regex("^[a-zA-Z0-9_-]*$")

internal fun JsonObject.merge(jsonObject: JsonObject): JsonObject {
    return toMutableMap().run {
        putAll(jsonObject.content)
        JsonObject(this)
    }
}

internal fun JsonObject.urlEncode(): String? {
    return if (isNotEmpty()) {
        Parameters.build {
            entries.forEach { (key, element) ->
                when (element) {
                    is JsonPrimitive -> append(key, element.content)
                    else -> append(key, Json.stringify(JsonElementSerializer, element))
                }
            }
        }.formUrlEncode()
    } else null
}

internal fun Decoder.asJsonInput() = (this as JsonInput).decodeJson()
internal fun Encoder.asJsonOutput() = this as JsonOutput

internal fun Query.toJsonNoDefaults(): JsonObject {
    return JsonNoDefaults.toJson(Query.serializer(), this).jsonObject
}

internal fun DeleteByQuery.toJsonNoDefaults(): JsonObject {
    return JsonNoDefaults.toJson(DeleteByQuery.serializer(), this).jsonObject
}

internal fun Settings.toJsonNoDefaults(): JsonObject {
    return JsonNoDefaults.toJson(Settings.serializer(), this).jsonObject
}

internal fun RequestAPIKey.stringify(): String {
    return JsonNoDefaults.stringify(RequestAPIKey.serializer(), this)
}

internal val Json = Json(JsonConfiguration.Stable.copy())
internal val JsonNoDefaults = Json(JsonConfiguration.Stable.copy(encodeDefaults = false))
internal val JsonNonStrict = Json(
    JsonConfiguration.Stable.copy(
        ignoreUnknownKeys = true,
        isLenient = true,
        serializeSpecialFloatingPointValues = true
    )
)
internal val JsonDebug = Json(JsonConfiguration.Stable.copy(prettyPrint = true, indent = "  ", encodeDefaults = false))

internal fun List<IndexQuery>.toBody(strategy: MultipleQueriesStrategy?): String {
    return JsonNoDefaults.stringify(
        RequestMultipleQueries,
        RequestMultipleQueries(this, strategy)
    )
}

internal fun Query.toBody(): String {
    return JsonNoDefaults.stringify(Query.serializer(), this)
}
