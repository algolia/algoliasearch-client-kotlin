package com.algolia.search.model.params

import com.algolia.search.model.Attribute

public interface SearchParameters : CommonSearchParameters, CommonParameters {

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

    /**
     * You need to turn on Dynamic Re-Ranking on your index for it to have an effect on your search results.
     * You can do this through the Re-Ranking page on the dashboard. This parameter is only used to turn off Dynamic
     * Re-Ranking (with false) at search time.
     */
    public var enableReRanking: Boolean?
}
