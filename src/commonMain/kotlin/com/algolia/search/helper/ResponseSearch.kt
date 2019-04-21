package com.algolia.search.helper

import com.algolia.search.model.response.ResponseSearch
import kotlinx.serialization.KSerializer


fun <T> List<ResponseSearch.Hit>.deserialize(serializer: KSerializer<T>): List<T> {
    return map { it.deserialize(serializer) }
}