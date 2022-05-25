package com.algolia.search.logging.internal

import com.algolia.search.logging.Logger
import io.ktor.client.plugins.logging.Logger as KLogger

/**
 * Convert [Logger] to a Ktor's Logger.
 */
internal fun Logger.toKtorLogger() = object : KLogger {
    override fun log(message: String) {
        this@toKtorLogger.log(message)
    }
}
