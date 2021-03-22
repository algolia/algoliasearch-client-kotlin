package com.algolia.search.model.response

public interface ResponseSearchPlaces<T> {

    public val hits: List<T>
    public val nbHits: Int
    public val processingTimeMS: Long
    public val params: String
    public val queryOrNull: String?
    public val degradedQueryOrNull: String?
    public val parsedQueryOrNull: String?

    public val query: String get() = queryOrNull!!
    public val degradedQuery: String get() = degradedQueryOrNull!!
    public val parsedQuery: String get() = parsedQueryOrNull!!
}
