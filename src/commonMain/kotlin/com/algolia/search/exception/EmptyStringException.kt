package com.algolia.search.exception


public class EmptyStringException(name: String) : IllegalArgumentException("$name must not have an empty string value.")