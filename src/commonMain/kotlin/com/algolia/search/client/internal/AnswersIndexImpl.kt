package com.algolia.search.client.internal

import com.algolia.search.client.AnswersIndex
import com.algolia.search.endpoint.EndpointAnswers
import com.algolia.search.endpoint.internal.EndpointAnswers
import com.algolia.search.model.IndexName
import com.algolia.search.transport.internal.Transport

internal class AnswersIndexImpl(
    val transport: Transport,
    override val indexName: IndexName,
) : AnswersIndex,
    EndpointAnswers by EndpointAnswers(transport, indexName)
