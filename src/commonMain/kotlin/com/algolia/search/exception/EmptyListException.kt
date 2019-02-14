package com.algolia.search.exception

class EmptyListException(name: String) : Exception("$name must not be an empty list.")