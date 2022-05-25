package com.algolia.search.logging.internal

import com.algolia.search.logging.Logger

/**
 * A [Logger] that breaks up log messages into multiple logs no longer than [maxLength].
 *
 * @property maxLength max length allowed for a log message
 * @property minLength if log message is longer than [maxLength], attempt to break the log
 * message at a new line between [minLength] and [maxLength] if one exists
 */
internal class MessageLengthLimitingLogger(
    private val maxLength: Int = 4000,
    private val minLength: Int = 3000,
    private val delegate: Logger = Logger.Simple
) : Logger {
    override fun log(message: String) {
        logLong(message)
    }

    private tailrec fun logLong(message: String) {
        // String to be logged is longer than the max...
        if (message.length > maxLength) {
            var msgSubstring = message.substring(0, maxLength)
            var msgSubstringEndIndex = maxLength

            // Try to find a substring break at a newline char.
            msgSubstring.lastIndexOf('\n').let { lastIndex ->
                if (lastIndex >= minLength) {
                    msgSubstring = msgSubstring.substring(0, lastIndex)
                    // skip over new line char
                    msgSubstringEndIndex = lastIndex + 1
                }
            }

            // Log the substring.
            delegate.log(msgSubstring)

            // Recursively log the remainder.
            logLong(message.substring(msgSubstringEndIndex))
        } else {
            delegate.log(message)
        } // String to be logged is shorter than the max...
    }
}
