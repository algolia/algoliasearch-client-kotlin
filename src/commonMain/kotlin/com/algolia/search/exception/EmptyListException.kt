package com.algolia.search.exception


public class EmptyListException(name: String) : Exception("$name must not be an empty list.")