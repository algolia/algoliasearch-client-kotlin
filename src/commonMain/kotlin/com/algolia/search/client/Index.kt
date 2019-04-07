package com.algolia.search.client

import com.algolia.search.endpoint.*
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchRules
import com.algolia.search.model.response.ResponseSearchSynonyms
import com.algolia.search.model.rule.RuleQuery
import com.algolia.search.model.search.Query
import com.algolia.search.model.synonym.SynonymQuery
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport


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
     * @param requestOptions [RequestOptions] sent along with the query.
     *
     * @return [List<ResponseSearch>]
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