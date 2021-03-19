package com.algolia.search.model.params

public interface AnswersParameters : CommonSearchParameters {

    /**
     * The query for which to retrieve results. Cannot be empty or spaces only.
     * [Documentation][https://www.algolia.com/doc/rest-api/answers/#method-param-params]
     */
    public var query: String
}
