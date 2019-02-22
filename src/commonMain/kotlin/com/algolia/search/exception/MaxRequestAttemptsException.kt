package com.algolia.search.exception


class MaxRequestAttemptsException(val attempts: Int, val exceptions: List<Exception>) : Exception()