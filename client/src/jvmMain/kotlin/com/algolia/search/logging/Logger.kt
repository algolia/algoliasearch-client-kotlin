package com.algolia.search.logging

import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.Logger as KLogger

/**
 * Android [Logger]: breaks up long log messages that would be truncated by Android's max log
 * length of 4068 characters
 */
public val Logger.Companion.Android: Logger get() = Logger { KLogger.ANDROID.log(it) }
