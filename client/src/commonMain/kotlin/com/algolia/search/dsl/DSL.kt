package com.algolia.search.dsl

import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.transport.RequestOptions

public val all: Attribute = Attribute("*")

@DslMarker
public annotation class DSLParameters

public interface DSL<T, S> {
    public operator fun invoke(block: (T.() -> Unit)): S
}

/**
 * Create a [RequestOptions] with [block]. Can take an optional [requestOptions] to be modified.
 */
public fun requestOptions(
    requestOptions: RequestOptions? = null,
    block: RequestOptions.() -> Unit,
): RequestOptions {
    return (requestOptions ?: RequestOptions()).apply(block)
}

/**
 * Create a [List] of [ObjectID] with [DSLObjectIDs].
 */
public fun objectIDs(block: DSLObjectIDs.() -> Unit): List<ObjectID> {
    return DSLObjectIDs(block)
}
