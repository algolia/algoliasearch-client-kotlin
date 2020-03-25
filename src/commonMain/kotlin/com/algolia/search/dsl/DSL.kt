package com.algolia.search.dsl

import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.transport.RequestOptions

val all = Attribute("*")

@DslMarker
annotation class DSLParameters

interface DSL<T, S> : (T.() -> Unit) -> S

/**
 * Create a [RequestOptions] with [block]. Can take an optional [requestOptions] to be modified.
 */
fun requestOptions(
    requestOptions: RequestOptions? = null,
    block: RequestOptions.() -> Unit
): RequestOptions {
    return (requestOptions ?: RequestOptions()).apply(block)
}

/**
 * Create a [List] of [ObjectID] with [DSLObjectIDs].
 */
fun objectIDs(block: DSLObjectIDs.() -> Unit): List<ObjectID> {
    return DSLObjectIDs(block)
}

internal fun requestOptionsBuilder(
    requestOptions: RequestOptions? = null,
    block: RequestOptions.() -> Unit
) = requestOptions(requestOptions, block)
