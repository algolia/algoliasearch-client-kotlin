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
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonElementSerializer
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject

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
internal val regexUserToken = Regex("^[a-zA-Z0-9_\\-\\.\\:]*\$") // alpha-numeric and/or IP address (IPv4/IPv6)

internal fun JsonObject.merge(jsonObject: JsonObject): JsonObject {
    return toMutableMap().run {
        putAll(jsonObject)
        JsonObject(this)
    }
}

internal fun JsonObject.urlEncode(): String? {
    return if (isNotEmpty()) {
        Parameters.build {
            entries.forEach { (key, element) ->
                when (element) {
                    is JsonPrimitive -> append(key, element.content)
                    else -> append(key, Json.encodeToString(JsonElementSerializer, element))
                }
            }
        }.formUrlEncode()
    } else null
}

internal fun Decoder.asJsonInput() = (this as JsonDecoder).decodeJsonElement()
internal fun Encoder.asJsonOutput() = this as JsonEncoder

internal fun Query.toJsonNoDefaults(): JsonObject {
    return JsonNoDefaults.encodeToJsonElement(Query.serializer(), this).jsonObject
}

internal fun DeleteByQuery.toJsonNoDefaults(): JsonObject {
    return JsonNoDefaults.encodeToJsonElement(DeleteByQuery.serializer(), this).jsonObject
}

internal fun Settings.toJsonNoDefaults(): JsonObject {
    return JsonNoDefaults.encodeToJsonElement(Settings.serializer(), this).jsonObject
}

internal fun RequestAPIKey.stringify(): String {
    return JsonNoDefaults.encodeToString(RequestAPIKey.serializer(), this)
}

internal val Json = Json.Default
internal val JsonNoDefaults = Json { encodeDefaults = false }
internal val JsonNonStrict = Json {
    ignoreUnknownKeys = true
    isLenient = true
    allowSpecialFloatingPointValues = true
}
internal val JsonDebug = Json {
    prettyPrint = true
    prettyPrintIndent = "  "
    encodeDefaults = false
}

internal fun List<IndexQuery>.toBody(strategy: MultipleQueriesStrategy?): String {
    return JsonNoDefaults.encodeToString(
        RequestMultipleQueries,
        RequestMultipleQueries(this, strategy)
    )
}

internal fun Query.toBody(): String {
    return JsonNoDefaults.encodeToString(Query.serializer(), this)
}

/**
 * Convenience method to get current element as [JsonObject] or null.
 */
internal val JsonElement.jsonObjectOrNull: JsonObject?
    get() = this as? JsonObject

/**
 * Convenience method to get current element as [JsonArray] or null.
 */
public val JsonElement.jsonArrayOrNull: JsonArray?
    get() = this as? JsonArray

/**
 * Convenience method to get current element as [JsonPrimitive] or null.
 */
public val JsonElement.jsonPrimitiveOrNull: JsonPrimitive?
    get() = this as? JsonPrimitive
