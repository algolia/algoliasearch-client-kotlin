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
import kotlinx.serialization.json.*

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
                    else -> append(key, Json.plain.stringify(JsonElementSerializer, element))
                }
            }
        }.formUrlEncode()
    } else null
}

internal fun Decoder.asJsonInput() = (this as JsonInput).decodeJson()
internal fun Encoder.asJsonOutput() = this as JsonOutput


internal fun Query.toJsonNoDefaults(): JsonObject {
    return Json.noDefaults.toJson(Query.serializer(), this).jsonObject
}

internal fun DeleteByQuery.toJsonNoDefaults(): JsonObject {
    return Json.noDefaults.toJson(DeleteByQuery.serializer(), this).jsonObject
}

internal fun Settings.toJsonNoDefaults(): JsonObject {
    return Json.noDefaults.toJson(Settings.serializer(), this).jsonObject
}

internal fun RequestAPIKey.stringify(): String {
    return Json.noDefaults.stringify(RequestAPIKey.serializer(), this)
}

internal val Json.Companion.noDefaults get() = Json(encodeDefaults = false)

internal fun List<IndexQuery>.toBody(strategy: MultipleQueriesStrategy?): String {
    return Json.noDefaults.stringify(
        RequestMultipleQueries,
        RequestMultipleQueries(this, strategy)
    )
}

internal fun Query?.toBody(): String {
    return this?.let { Json.noDefaults.stringify(Query.serializer(), it) } ?: "{}"
}