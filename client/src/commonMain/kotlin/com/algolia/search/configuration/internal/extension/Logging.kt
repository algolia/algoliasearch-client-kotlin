package com.algolia.search.configuration.internal.extension

import com.algolia.search.logging.LogLevel
import io.ktor.client.plugins.logging.LogLevel as KLogLevel

/**
 * Convert LogLevel to a Ktor's LogLevel.
 */
internal fun LogLevel.toKtorLogLevel() = when (this) {
    LogLevel.All -> KLogLevel.ALL
    LogLevel.Headers -> KLogLevel.HEADERS
    LogLevel.Body -> KLogLevel.BODY
    LogLevel.Info -> KLogLevel.INFO
    LogLevel.None -> KLogLevel.NONE
}
