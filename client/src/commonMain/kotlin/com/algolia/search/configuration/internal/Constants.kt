package com.algolia.search.configuration.internal

import io.ktor.client.features.logging.LogLevel
import kotlin.native.concurrent.SharedImmutable

internal const val DEFAULT_WRITE_TIMEOUT: Long = 30000L
internal const val DEFAULT_READ_TIMEOUT: Long = 5000L

@SharedImmutable
internal val DEFAULT_LOG_LEVEL = LogLevel.NONE
