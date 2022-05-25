package com.algolia.search.logging

import com.algolia.search.logging.internal.toLogger
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.EMPTY
import io.ktor.client.plugins.logging.Logger as KLogger
import io.ktor.client.plugins.logging.SIMPLE

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
        public val Simple: Logger = KLogger.SIMPLE.toLogger()

        /**
         * Empty [Logger.
         */
        public val Empty: Logger = KLogger.EMPTY.toLogger()

        /**
         * Default logger to use.
         */
        public val Default: Logger = KLogger.DEFAULT.toLogger()
    }
}
