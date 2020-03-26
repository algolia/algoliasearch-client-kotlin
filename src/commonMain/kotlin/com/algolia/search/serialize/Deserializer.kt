package com.algolia.search.serialize

import com.algolia.search.model.Attribute
import com.algolia.search.model.search.HighlightResult
import com.algolia.search.model.search.SnippetResult
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.list

/**
 * Convenience method.
 */
public fun JsonObject.toHighlight(key: String): HighlightResult? {
    return getObjectOrNull(key)?.let { Json.fromJson(HighlightResult.serializer(), it) }
}

/**
 * Use [attribute] as key in [JsonObject] and convert its value to a [HighlightResult].
 */
public fun JsonObject.toHighlight(attribute: Attribute): HighlightResult? {
    return toHighlight(attribute.raw)
}

/**
 * Convenience method.
 */
public fun JsonObject.toHighlights(key: String): List<HighlightResult>? {
    return getArrayOrNull(key)?.let { Json.fromJson(HighlightResult.serializer().list, it) }
}

/**
 * Use [attribute] as key in [JsonObject] and convert its value to a [List] of [HighlightResult].
 */
public fun JsonObject.toHighlights(attribute: Attribute): List<HighlightResult>? {
    return toHighlights(attribute.raw)
}

/**
 * Convenience method.
 */
public fun JsonObject.toSnippet(key: String): SnippetResult? {
    return getObjectOrNull(key)?.let { Json.fromJson(SnippetResult.serializer(), it) }
}

/**
 * Use [attribute] as key in [JsonObject] and convert its value to a [SnippetResult].
 */
public fun JsonObject.toSnippet(attribute: Attribute): SnippetResult? {
    return toSnippet(attribute.raw)
}

/**
 * Convenience method.
 */
public fun JsonObject.toSnippets(key: String): List<SnippetResult>? {
    return getArrayOrNull(key)?.let { Json.fromJson(SnippetResult.serializer().list, it) }
}

/**
 * Use [attribute] as key in [JsonObject] and convert its value to a [List] of [SnippetResult].
 */
public fun JsonObject.toSnippets(attribute: Attribute): List<SnippetResult>? {
    return toSnippets(attribute.raw)
}
