package com.algolia.search.logging

import com.algolia.search.logging.internal.toKtorLogger
import com.algolia.search.logging.internal.toLogger

import io.ktor.client.plugins.logging.MessageLengthLimitingLogger

/**
 * Android [Logger]: breaks up long log messages that would be truncated by Android's max log
 * length of 4068 characters
 */
public fun Logger.Companion.Android(delegate: Logger = Simple): Logger =
    MessageLengthLimitingLogger(delegate = delegate.toKtorLogger()).toLogger()
