package com.algolia.search.dsl.internal

import com.algolia.search.dsl.requestOptions
import com.algolia.search.transport.RequestOptions

internal fun requestOptionsBuilder(
    requestOptions: RequestOptions? = null,
    block: RequestOptions.() -> Unit,
) = requestOptions(requestOptions, block)
