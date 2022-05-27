package com.algolia.search.logging

import com.algolia.search.logging.internal.MessageLengthLimitingLogger

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
        public val Simple: Logger get() = Logger { println("HttpClient: $it") }

        /**
         * Empty [Logger].
         */
        public val Empty: Logger get() = Logger { /* No-op */ }

        /**
         * A [Logger] that breaks up log messages into multiple logs no longer than [maxLength].
         * Useful for platforms with limited log length (e.g. Android).
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
            maxLength = maxLength, minLength = minLength, delegate = delegate
        )
    }
}
