package com.algolia.search.dsl

import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.transport.RequestOptions

public val all = Attribute("*")

@DslMarker
public annotation class DSLParameters

interface DSL<T, S> : (T.() -> Unit) -> S

public fun requestOptions(
    requestOptions: RequestOptions? = null,
    block: RequestOptions.() -> Unit
): RequestOptions {
    return (requestOptions ?: RequestOptions()).apply(block)
}

internal fun requestOptionsBuilder(
    requestOptions: RequestOptions? = null,
    block: RequestOptions.() -> Unit
) = requestOptions(requestOptions, block)

internal fun objectIDs(block: DSLObjectIDs.() -> Unit): List<ObjectID> {
    return DSLObjectIDs(block)
}