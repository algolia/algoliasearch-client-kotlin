package com.algolia.search.model.filter.internal

internal fun interface Converter<I, O> {

    operator fun invoke(input: I): O
}
