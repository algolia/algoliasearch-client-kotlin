package com.algolia.search.exception

/**
 * Exception thrown when an error occurs during the Serialization/Deserialization process.
 */
public sealed class AlgoliaRuntimeException(message: String? = null, cause: Throwable? = null) :
    RuntimeException(message, cause)

/**
 * Exception thrown when all hosts are unreachable.
 * When several errors occurred, use the last one as the cause for the returned exception.
 *
 * @param exceptions list of thrown exceptions
 */
public class UnreachableHostsException(
    public val exceptions: List<Throwable>,
) : AlgoliaRuntimeException("Unreachable Hosts", exceptions.last()) {

    public constructor(vararg exceptions: Throwable) : this(exceptions.toList())
}
