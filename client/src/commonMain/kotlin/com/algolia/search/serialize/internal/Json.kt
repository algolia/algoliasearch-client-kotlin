package com.algolia.search.serialize.internal

import com.algolia.search.model.indexing.DeleteByQuery
import com.algolia.search.model.internal.request.RequestAPIKey
import com.algolia.search.model.internal.request.RequestMultipleQueries
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.model.search.Query
import com.algolia.search.model.settings.Settings
import io.ktor.http.Parameters
import io.ktor.http.formUrlEncode
import io.ktor.util.InternalAPI
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject

internal val Json = Json {
    encodeDefaults = true
}
internal val JsonNoDefaults = Json.Default
internal val JsonNonStrict = Json {
    ignoreUnknownKeys = true
    isLenient = true
    allowSpecialFloatingPointValues = true
    encodeDefaults = true
}

@OptIn(ExperimentalSerializationApi::class)
internal val JsonDebug = Json {
    prettyPrint = true
    prettyPrintIndent = "  "
    encodeDefaults = false
}

internal fun JsonObject.merge(jsonObject: JsonObject): JsonObject {
    return toMutableMap().run {
        putAll(jsonObject)
        JsonObject(this)
    }
}

@OptIn(InternalAPI::class) // https://youtrack.jetbrains.com/issue/KT-48127
internal fun JsonObject.urlEncode(): String? {
    return if (isNotEmpty()) {
        Parameters.build {
            entries.forEach { (key, element) ->
                when (element) {
                    is JsonPrimitive -> append(key, element.content)
                    else -> append(key, Json.encodeToString(JsonElement.serializer(), element))
                }
            }
        }.formUrlEncode()
    } else null
}

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

internal fun List<IndexQuery>.toBody(strategy: MultipleQueriesStrategy?): String {
    return JsonNoDefaults.encodeToString(
        RequestMultipleQueries,
        RequestMultipleQueries(this, strategy)
    )
}

internal fun Query.toBody(): String {
    return JsonNoDefaults.encodeToString(Query.serializer(), this)
}

internal fun Decoder.asJsonDecoder() = this as JsonDecoder
internal fun Decoder.asJsonInput() = asJsonDecoder().decodeJsonElement()
internal fun Encoder.asJsonOutput() = this as JsonEncoder

/**
 * Convenience method to get current element as [JsonObject] or null.
 */
internal val JsonElement.jsonObjectOrNull: JsonObject?
    get() = this as? JsonObject

/**
 * Convenience method to get current element as [JsonArray] or null.
 */
internal val JsonElement.jsonArrayOrNull: JsonArray?
    get() = this as? JsonArray

/**
 * Convenience method to get current element as [JsonPrimitive] or null.
 */
internal val JsonElement.jsonPrimitiveOrNull: JsonPrimitive?
    get() = this as? JsonPrimitive
