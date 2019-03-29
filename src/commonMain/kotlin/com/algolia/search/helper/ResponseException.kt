package com.algolia.search.helper

import io.ktor.client.features.ResponseException
import io.ktor.client.response.readBytes
import kotlinx.io.core.String


suspend fun ResponseException.readContent(): String {
    return String(response.readBytes())
}