package com.algolia.search.logging

import com.algolia.search.logging.internal.MessageLengthLimitingLogger
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
        public val Simple: Logger get() = KLogger.SIMPLE.toLogger()

        /**
         * Empty [Logger.
         */
        public val Empty: Logger get() = KLogger.EMPTY.toLogger()

        /**
         * Default logger to use.
         */
        public val Default: Logger get() = KLogger.DEFAULT.toLogger()

        /**
         * A [Logger] that breaks up log messages into multiple logs no longer than [maxLength].
         * Useful for platforms such as Android, which has a log length limited to 4068 characters.
         *
         * @property maxLength max length allowed for a log message
         * @property minLength if log message is longer than [maxLength], attempt to break the log
         * message at a new line between [minLength] and [maxLength] if one exists
         */
        public fun messageLengthLimiting(
            maxLength: Int = 4000,
            minLength: Int = 3000,
            delegate: Logger = Simple
        ): Logger = MessageLengthLimitingLogger(
            maxLength = maxLength,
            minLength = minLength,
            delegate = delegate
        )
    }
}
