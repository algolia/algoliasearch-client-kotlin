package com.algolia.search.model.params

import com.algolia.search.model.search.Snippet

public interface CommonParameters : BaseParameters {

    /**
     * List of attributes to snippet.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToSnippet/?language=kotlin]
     */
    public var attributesToSnippet: List<Snippet>?

    /**
     * Set the number of hits per page.
     * Engine default: 20
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/hitsPerPage/?language=kotlin]
     */
    public var hitsPerPage: Int?
}
