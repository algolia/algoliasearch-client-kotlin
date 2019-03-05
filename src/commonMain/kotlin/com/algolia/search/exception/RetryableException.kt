package com.algolia.search.exception


class RetryableException(val attempts: Int, val exceptions: List<Exception>) : Exception()