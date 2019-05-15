package com.algolia.search.configuration

import io.ktor.client.features.logging.LogLevel


internal const val defaultWriteTimeout: Long = 30000L
internal const val defaultReadTimeout: Long = 2000L
internal val defaultLogLevel = LogLevel.NONE