package client.serialize

import client.data.BooleanOrQueryLanguages
import io.ktor.http.Parameters
import io.ktor.http.formUrlEncode
import kotlinx.serialization.json.*

internal val regexAsc = Regex("$KeyAsc\\((.*)\\)")
internal val regexDesc = Regex("$KeyDesc\\((.*)\\)")
internal val regexEqualOnly = Regex("$KeyEqualOnly\\((.*)\\)")

internal fun Map<String, Any>.serialize(): JsonObject {
    return json {
        entries.forEach { entry ->
            val value = entry.value
            when (value) {
                is String -> entry.key to value
                is Number -> entry.key to value
                is Boolean -> entry.key to value
                is JsonElement -> entry.key to value
                else -> throw Exception("Unsupported serialization type.")
            }
        }
    }
}

internal fun Map<String, Any>.urlEncode(): String {
    return Parameters.build {
        entries.forEach { entry ->
            val value = entry.value
            when (value) {
                is String -> append(entry.key, value)
                is Number -> append(entry.key, value.toString())
                is Boolean -> append(entry.key, value.toString())
                is JsonArray -> appendAll(entry.key, value.content.map { it.content })
                else -> throw Exception("Unsupported serialization type.")
            }
        }
    }.formUrlEncode()
}

internal fun <T> T?.unwrap(block: T.() -> JsonElement): JsonElement {
    return if (this != null) block(this) else JsonNull
}

internal fun List<Float>.toJsonArrayFromFloat() = jsonArray { forEach { (it as Number).unaryPlus() } }
internal fun List<String>.toJsonArrayFromString() = jsonArray { forEach { +it } }
internal fun List<List<String>>.toJsonArrayFromList() = jsonArray { forEach { +jsonArray { it.forEach { +it } } } }

internal fun BooleanOrQueryLanguages.toPrimitive(): Any = when (this) {
    is BooleanOrQueryLanguages.Boolean -> boolean
    is BooleanOrQueryLanguages.QueryLanguages -> queryLanguages.map { it.raw }.toJsonArrayFromString()
}