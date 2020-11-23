package com.algolia.search.endpoint

import com.algolia.search.ExperimentalAlgoliaClientAPI
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.search.AnswersQuery
import com.algolia.search.transport.RequestOptions

/**
 * [Documentation](https://www.algolia.com/doc/rest-api/answers/)
 */
public interface EndpointAnswers {

    /**
     * Index name.
     */
    public val indexName: IndexName

    /**
     * Find answers.
     * [Documentation](https://www.algolia.com/doc/rest-api/answers/#find-answers)
     *
     * @param answersQuery answers query used to search
     * @param requestOptions configure request locally
     * @return answers that match the query.
     */
    @ExperimentalAlgoliaClientAPI
    public suspend fun findAnswers(
        answersQuery: AnswersQuery,
        requestOptions: RequestOptions? = null,
    ): ResponseSearch
}
