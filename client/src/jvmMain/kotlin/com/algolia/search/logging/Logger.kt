package com.algolia.search.logging

import io.ktor.client.HttpClient
import org.slf4j.LoggerFactory

/**
 * [Logger] implementation using Simple Logging Facade for Java (SLF4J).
 */
public val Logger.Companion.Slf4j: Logger
    get() = object : Logger {
        private val delegate = requireNotNull(LoggerFactory.getLogger(HttpClient::class.java))
        override fun log(message: String) {
            delegate.info(message)
        }
    }
