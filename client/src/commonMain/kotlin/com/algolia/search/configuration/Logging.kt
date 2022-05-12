package com.algolia.search.configuration

/**
 * Http client logging log level.
 */
public enum class LogLevel {
    All, Headers, Body, Info, None
}

/**
 * Http client logger.
 */
public enum class Logger {

    /**
     * Default logger to use.
     */
    Default,

    /**
     * Logger using println.
     */
    Simple,

    /**
     * Empty Logger for test purpose.
     */
    Empty
}
