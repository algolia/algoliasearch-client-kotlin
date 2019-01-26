package com.algolia.search.exception


class EmptyStringException(name: String) : Exception("$name must not have an empty string value.")