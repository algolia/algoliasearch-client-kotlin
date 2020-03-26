package com.algolia.search.client

import com.algolia.search.endpoint.EndpointAdvanced
import com.algolia.search.endpoint.EndpointAdvancedImpl
import com.algolia.search.endpoint.EndpointIndex
import com.algolia.search.endpoint.EndpointIndexImpl
import com.algolia.search.endpoint.EndpointIndexing
import com.algolia.search.endpoint.EndpointIndexingImpl
import com.algolia.search.endpoint.EndpointRule
import com.algolia.search.endpoint.EndpointRuleImpl
import com.algolia.search.endpoint.EndpointSearch
import com.algolia.search.endpoint.EndpointSearchImpl
import com.algolia.search.endpoint.EndpointSettings
import com.algolia.search.endpoint.EndpointSettingsImpl
import com.algolia.search.endpoint.EndpointSynonym
import com.algolia.search.endpoint.EndpointSynonymImpl
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchRules
import com.algolia.search.model.response.ResponseSearchSynonyms
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.rule.RuleQuery
import com.algolia.search.model.search.Query
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymQuery
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport

/**
 * The main entry point for performing operations on a single index.
 */
public data class Index internal constructor(
    internal val transport: Transport,
    override val indexName: IndexName
) : EndpointSearch by EndpointSearchImpl(transport, indexName),
    EndpointSettings by EndpointSettingsImpl(transport, indexName),
    EndpointAdvanced by EndpointAdvancedImpl(transport, indexName),
    EndpointIndex by EndpointIndexImpl(transport, indexName),
    EndpointIndexing by EndpointIndexingImpl(transport, indexName),
    EndpointSynonym by EndpointSynonymImpl(transport, indexName),
    EndpointRule by EndpointRuleImpl(transport, indexName) {

    /**
     * Iterate over all [Rule] in the index.
     *
     * @see [searchRules]
     *
     * @param query The [RuleQuery] used to search.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun browseRules(
        query: RuleQuery = RuleQuery(),
        requestOptions: RequestOptions? = null
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

    /**
     * Iterate over all [Synonym] in the index.
     *
     * @see [searchSynonyms]
     *
     * @param query The [SynonymQuery] used to search.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun browseSynonyms(
        query: SynonymQuery = SynonymQuery(),
        requestOptions: RequestOptions? = null
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

    /**
     * Iterate over all objects in the index.
     *
     * @see [browse]
     *
     * @param query The [Query] used to search.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun browseObjects(
        query: Query = Query(),
        requestOptions: RequestOptions? = null
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
