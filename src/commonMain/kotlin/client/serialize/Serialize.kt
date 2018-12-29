package client.serialize

import io.ktor.http.Parameters
import io.ktor.http.formUrlEncode
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.content

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