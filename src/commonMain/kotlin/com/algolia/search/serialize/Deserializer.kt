package com.algolia.search.serialize

import com.algolia.search.model.Attribute
import com.algolia.search.model.search.HighlightResult
import com.algolia.search.model.search.SnippetResult
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.list

public fun JsonObject.toHighlight(key: String): HighlightResult? {
    return getObjectOrNull(key)?.let { Json.fromJson(HighlightResult.serializer(), it) }
}

public fun JsonObject.toHighlight(attribute: Attribute): HighlightResult? {
    return toHighlight(attribute.raw)
}

public fun JsonObject.toHighlights(key: String): List<HighlightResult>? {
    return getArrayOrNull(key)?.let { Json.fromJson(HighlightResult.serializer().list, it) }
}

public fun JsonObject.toHighlights(attribute: Attribute): List<HighlightResult>? {
    return toHighlights(attribute.raw)
}

public fun JsonObject.toSnippet(key: String): SnippetResult? {
    return getObjectOrNull(key)?.let { Json.fromJson(SnippetResult.serializer(), it) }
}

public fun JsonObject.toSnippet(attribute: Attribute): SnippetResult? {
    return toSnippet(attribute.raw)
}

public fun JsonObject.toSnippets(key: String): List<SnippetResult>? {
    return getArrayOrNull(key)?.let { Json.fromJson(SnippetResult.serializer().list, it) }
}

public fun JsonObject.toSnippets(attribute: Attribute): List<SnippetResult>? {
    return toSnippets(attribute.raw)
}
