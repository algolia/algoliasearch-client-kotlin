package com.algolia.search.helper

import io.ktor.client.features.BadResponseStatusException
import io.ktor.client.response.readBytes
import kotlinx.io.core.String


/**
 * Convenience method to convert [BadResponseStatusException.response] body bytes to a [String].
 */
suspend fun BadResponseStatusException.readContent(): String {
    return String(response.readBytes())
}