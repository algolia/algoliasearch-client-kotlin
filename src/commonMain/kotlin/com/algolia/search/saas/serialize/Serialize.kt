package com.algolia.search.saas.serialize

import com.algolia.search.saas.data.Query
import com.algolia.search.saas.data.Settings
import io.ktor.http.Parameters
import io.ktor.http.formUrlEncode
import kotlinx.serialization.Decoder
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

internal fun Decoder.readAsTree() = (this as JSON.JsonInput).readAsTree()

internal fun Query.toJsonObject(): JsonObject {
    return JsonTreeParser.parse(JSON.stringify(Query.serializer(), this))
}

internal fun Settings.toJsonObject(): JsonObject {
    return JsonTreeParser.parse(JSON.stringify(Settings.serializer(), this))
}

internal fun Query.encodeNoNulls(): JsonObject {
    return JsonObject(toJsonObject().filter { it.value != JsonNull })
}

internal fun Settings.encodeNoNulls(): JsonObject {
    return JsonObject(toJsonObject().filter { it.value != JsonNull })
}