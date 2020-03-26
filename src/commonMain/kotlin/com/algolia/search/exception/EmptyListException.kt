package com.algolia.search.exception

/**
 * This exception is thrown when an illegal empty [List] is encountered.
 */
public class EmptyListException(name: String) : Exception("$name must not be an empty list.")
