package com.algolia.search.model.response


public interface ResponseSearchPlaces<T> {

    val hits: List<T>
    val nbHits: Int
    val processingTimeMS: Long
    val params: String
    val queryOrNull: String?
    val degradedQueryOrNull: String?
    val parsedQueryOrNull: String?

    val query: String get() = queryOrNull!!
    val degradedQuery: String get() = degradedQueryOrNull!!
    val parsedQuery: String get() = parsedQueryOrNull!!
}