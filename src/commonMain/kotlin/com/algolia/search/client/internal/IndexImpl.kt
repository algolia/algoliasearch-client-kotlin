@file:Suppress("FunctionName")

package com.algolia.search.client.internal

import com.algolia.search.client.Index
import com.algolia.search.endpoint.EndpointAdvanced
import com.algolia.search.endpoint.EndpointAnswers
import com.algolia.search.endpoint.EndpointIndex
import com.algolia.search.endpoint.EndpointIndexing
import com.algolia.search.endpoint.EndpointRule
import com.algolia.search.endpoint.EndpointSearch
import com.algolia.search.endpoint.EndpointSettings
import com.algolia.search.endpoint.EndpointSynonym
import com.algolia.search.endpoint.internal.EndpointAdvanced
import com.algolia.search.endpoint.internal.EndpointAnswers
import com.algolia.search.endpoint.internal.EndpointIndex
import com.algolia.search.endpoint.internal.EndpointIndexing
import com.algolia.search.endpoint.internal.EndpointRule
import com.algolia.search.endpoint.internal.EndpointSearch
import com.algolia.search.endpoint.internal.EndpointSettings
import com.algolia.search.endpoint.internal.EndpointSynonym
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchRules
import com.algolia.search.model.response.ResponseSearchSynonyms
import com.algolia.search.model.rule.RuleQuery
import com.algolia.search.model.search.Query
import com.algolia.search.model.synonym.SynonymQuery
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport

internal class IndexImpl internal constructor(
    internal val transport: Transport,
    override val indexName: IndexName,
) : Index,
    EndpointSearch by EndpointSearch(transport, indexName),
    EndpointSettings by EndpointSettings(transport, indexName),
    EndpointAdvanced by EndpointAdvanced(transport, indexName),
    EndpointIndex by EndpointIndex(transport, indexName),
    EndpointIndexing by EndpointIndexing(transport, indexName),
    EndpointSynonym by EndpointSynonym(transport, indexName),
    EndpointRule by EndpointRule(transport, indexName),
    EndpointAnswers by EndpointAnswers(transport, indexName) {

    override suspend fun browseRules(
        query: RuleQuery,
        requestOptions: RequestOptions?,
    ): List<ResponseSearchRules> {
        val responses = mutableListOf<ResponseSearchRules>()
        var page = 0

        while (true) {
            val response = searchRules(query.copy(page = page++), requestOptions)

            if (response.hits.isEmpty()) break
            responses += response
        }
        return responses
    }

    override suspend fun browseSynonyms(
        query: SynonymQuery,
        requestOptions: RequestOptions?,
    ): List<ResponseSearchSynonyms> {
        val responses = mutableListOf<ResponseSearchSynonyms>()
        var page = 0

        while (true) {
            val response = searchSynonyms(query.copy(page = page++), requestOptions)

            if (response.hits.isEmpty()) break
            responses += response
        }
        return responses
    }

    override suspend fun browseObjects(
        query: Query,
        requestOptions: RequestOptions?,
    ): List<ResponseSearch> {
        val responses = mutableListOf<ResponseSearch>()
        val initial = browse(query, requestOptions)
        var cursor = initial.cursorOrNull

        responses += initial
        while (cursor != null) {
            val response = browse(cursor)

            responses += response
            cursor = response.cursorOrNull
        }
        return responses
    }
}

/**
 * Create an [Index] instance.
 *
 * @param transport transport operations
 * @param indexName index name
 */
internal fun Index(
    transport: Transport,
    indexName: IndexName,
): Index = IndexImpl(transport, indexName)
