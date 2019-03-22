package com.algolia.search.client

import com.algolia.search.endpoint.*
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.ResponseRules
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchSynonyms
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.search.Query
import com.algolia.search.model.synonym.SynonymType
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

    public suspend fun browseAllRules(
        query: String? = null,
        anchoring: Anchoring? = null,
        context: String? = null,
        hitsPerPage: Int? = null,
        enabled: Boolean? = null,
        requestOptions: RequestOptions? = null,
        block: suspend (ResponseRules) -> Unit
    ) {
        var page = 0

        while (true) {
            val response = searchRules(query, anchoring, context, page++, hitsPerPage, enabled, requestOptions)

            if (response.hits.isEmpty()) break
            block(response)
        }
    }

    public suspend fun browseAllSynonyms(
        query: String? = null,
        hitsPerPage: Int? = null,
        synonymType: List<SynonymType>? = null,
        requestOptions: RequestOptions? = null,
        block: suspend (ResponseSearchSynonyms) -> Unit
    ) {
        var page = 0

        while (true) {
            val response = searchSynonyms(query, page++, hitsPerPage, synonymType, requestOptions)

            if (response.hits.isEmpty()) break
            block(response)
        }
    }

    public suspend fun browseAllObjects(
        query: Query? = null,
        requestOptions: RequestOptions? = null,
        block: suspend (ResponseSearch) -> Unit
    ) {
        val initial = browse(query, requestOptions)
        var cursor = initial.cursorOrNull

        block(initial)
        while (cursor != null) {
            val response = browse(cursor)

            block(response)
            cursor = response.cursorOrNull
        }
    }
}