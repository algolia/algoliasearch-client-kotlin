package com.algolia.search.logging

import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.Logger as KLogger

/**
 * Client Logger.
 */
public fun interface Logger {

    /**
     * Add [message] to log.
     */
    public fun log(message: String)

    public companion object {

        /**
         * [Logger] using [println].
         */
        public val Simple: Logger = Logger { println("HttpClient: $it") }

        /**
         * Empty [Logger.
         */
        public val Empty: Logger = Logger { /* No-op */ }

        /**
         * Default logger to use.
         */
        public val Default: Logger = Logger { KLogger.DEFAULT.log(it) }
    }
}
