@file:Suppress("FunctionName")

package com.algolia.search.endpoint.internal

import com.algolia.search.ExperimentalAlgoliaClientAPI
import com.algolia.search.configuration.CallType
import com.algolia.search.endpoint.EndpointAnswers
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.search.AnswersQuery
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.http.HttpMethod

internal class EndpointAnswersImpl(
    private val transport: Transport,
    override val indexName: IndexName,
) : EndpointAnswers {

    @ExperimentalAlgoliaClientAPI
    override suspend fun findAnswers(answersQuery: AnswersQuery, requestOptions: RequestOptions?): ResponseSearch {
        val body = JsonNoDefaults.encodeToString(AnswersQuery.serializer(), answersQuery)
        return transport.request(
            httpMethod = HttpMethod.Post,
            callType = CallType.Read,
            path = "1/answers/${indexName.encode()}/prediction",
            requestOptions = requestOptions,
            body = body
        )
    }
}

/**
 * Create an [EndpointAnswers] instance.
 */
internal fun EndpointAnswers(
    transport: Transport,
    indexName: IndexName,
): EndpointAnswers = EndpointAnswersImpl(transport, indexName)
