package com.algolia.search.helper

import com.algolia.search.model.response.ResponseSearch
import kotlinx.serialization.DeserializationStrategy

/**
 * Convenience method to transform all [ResponseSearch.Hit.json] to a typed object [T] with [serializer].
 */
public fun <T> List<ResponseSearch.Hit>.deserialize(deserializer: DeserializationStrategy<T>): List<T> {
    return map { it.deserialize(deserializer) }
}
