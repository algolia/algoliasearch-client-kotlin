package com.algolia.search.serialize

import com.algolia.search.model.Attribute
import com.algolia.search.model.search.HighlightResult
import com.algolia.search.model.search.SnippetResult
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.list

/**
 * Convenience method.
 */
fun JsonObject.toHighlight(key: String): HighlightResult? {
    return getObjectOrNull(key)?.let { Json.fromJson(HighlightResult.serializer(), it) }
}

/**
 * Use [attribute] as key in [JsonObject] and convert its value to a [HighlightResult].
 */
fun JsonObject.toHighlight(attribute: Attribute): HighlightResult? {
    return toHighlight(attribute.raw)
}

/**
 * Convenience method.
 */
fun JsonObject.toHighlights(key: String): List<HighlightResult>? {
    return getArrayOrNull(key)?.let { Json.fromJson(HighlightResult.serializer().list, it) }
}

/**
 * Use [attribute] as key in [JsonObject] and convert its value to a [List] of [HighlightResult].
 */
fun JsonObject.toHighlights(attribute: Attribute): List<HighlightResult>? {
    return toHighlights(attribute.raw)
}

/**
 * Convenience method.
 */
fun JsonObject.toSnippet(key: String): SnippetResult? {
    return getObjectOrNull(key)?.let { Json.fromJson(SnippetResult.serializer(), it) }
}

/**
 * Use [attribute] as key in [JsonObject] and convert its value to a [SnippetResult].
 */
fun JsonObject.toSnippet(attribute: Attribute): SnippetResult? {
    return toSnippet(attribute.raw)
}

/**
 * Convenience method.
 */
fun JsonObject.toSnippets(key: String): List<SnippetResult>? {
    return getArrayOrNull(key)?.let { Json.fromJson(SnippetResult.serializer().list, it) }
}

/**
 * Use [attribute] as key in [JsonObject] and convert its value to a [List] of [SnippetResult].
 */
fun JsonObject.toSnippets(attribute: Attribute): List<SnippetResult>? {
    return toSnippets(attribute.raw)
}
