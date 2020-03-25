package com.algolia.search.exception

/**
 * This exception is thrown when an illegal empty [String] is encountered.
 */
class EmptyStringException(name: String) : IllegalArgumentException("$name must not have an empty string value.")
