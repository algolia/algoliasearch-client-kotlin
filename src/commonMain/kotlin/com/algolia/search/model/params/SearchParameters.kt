package com.algolia.search.model.params

import com.algolia.search.model.Attribute

public interface SearchParameters : CommonSearch, CommonScope {

    /**
     * The text to search in the index.
     * Engine default: ""
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/query/?language=kotlin]
     */
    public var query: String?

    /**
     * Restricts a given query to look in only a subset of your searchable attributes.
     * Engine default: all attributes in [Settings.searchableAttributes].
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/restrictSearchableAttributes/?language=kotlin]
     */
    public var restrictSearchableAttributes: List<Attribute>?
}
