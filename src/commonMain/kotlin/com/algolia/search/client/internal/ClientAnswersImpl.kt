package com.algolia.search.client.internal

import com.algolia.search.client.AnswersIndex
import com.algolia.search.client.ClientAnswers
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.Credentials
import com.algolia.search.model.IndexName
import com.algolia.search.transport.internal.Transport

/**
 * Answers client implementation.
 */
internal class ClientAnswersImpl(
    internal val transport: Transport,
) : ClientAnswers,
    Configuration by transport,
    Credentials by transport.credentials {

    override fun initIndex(indexName: IndexName): AnswersIndex {
        return AnswersIndexImpl(transport, indexName)
    }
}
