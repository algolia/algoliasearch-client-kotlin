package com.algolia.search.helper

import io.ktor.client.features.ResponseException
import io.ktor.client.response.readBytes
import kotlinx.io.core.String


/**
 * Convenience method to convert [ResponseException.response] body bytes to a [String].
 */
suspend fun ResponseException.readContent(): String {
    return String(response.readBytes())
}