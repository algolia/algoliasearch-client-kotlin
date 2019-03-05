package com.algolia.search.exception


public class MaxRequestAttemptsException(val attempts: Int, val exceptions: List<Exception>) : Exception()