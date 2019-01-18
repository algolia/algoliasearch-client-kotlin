package com.algolia.search.saas.exception


class EmptyStringException(name: String) : Exception("$name must not have an empty string value.")