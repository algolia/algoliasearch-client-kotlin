package com.algolia.search.helper

import io.ktor.client.features.BadResponseStatusException
import io.ktor.client.response.readBytes
import kotlinx.io.core.String


suspend fun BadResponseStatusException.readContent(): String {
    return String(response.readBytes())
}