package com.algolia.search.logging.internal

import com.algolia.search.logging.Logger
import io.ktor.client.plugins.logging.Logger as KtorLogger

/**
 * Convert [Logger] to a Ktor's Logger.
 */
internal fun Logger.toKtorLogger() = object : KtorLogger {
    override fun log(message: String) {
        this@toKtorLogger.log(message)
    }
}
