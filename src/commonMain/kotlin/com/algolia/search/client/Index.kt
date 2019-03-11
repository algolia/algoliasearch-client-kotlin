package com.algolia.search.client

import com.algolia.search.endpoint.*
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.ResponseRules
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchSynonyms
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.search.Query
import com.algolia.search.model.synonym.SynonymType


public data class Index internal constructor(
    internal val api: APIWrapper,
    override val indexName: IndexName
) : EndpointSearch by EndpointSearchImpl(api, indexName),
    EndpointSettings by EndpointSettingsImpl(api, indexName),
    EndpointAdvanced by EndpointAdvancedImpl(api, indexName),
    EndpointIndex by EndpointIndexImpl(api, indexName),
    EndpointIndexing by EndpointIndexingImpl(api, indexName),
    EndpointSynonym by EndpointSynonymImpl(api, indexName),
    EndpointRule by EndpointRuleImpl(api, indexName) {

    public suspend fun browseAllRules(
        query: String? = null,
        anchoring: Anchoring? = null,
        context: String? = null,
        hitsPerPage: Int? = null,
        enabled: Boolean? = null,
        requestOptions: RequestOptions? = null,
        block: suspend ResponseRules.(Int) -> Unit
    ) {
        var page = 0

        while (true) {
            val response = searchRules(query, anchoring, context, page, hitsPerPage, enabled, requestOptions)

            if (response.hits.isEmpty()) break
            block(response, page++)
        }
    }

    public suspend fun browseAllSynonyms(
        query: String? = null,
        hitsPerPage: Int? = null,
        synonymType: List<SynonymType>? = null,
        requestOptions: RequestOptions? = null,
        block: suspend ResponseSearchSynonyms.(Int) -> Unit
    ) {
        var page = 0

        while (true) {
            val response = searchSynonyms(query, page, hitsPerPage, synonymType, requestOptions)

            if (response.hits.isEmpty()) break
            block(response, page++)
        }
    }

    public suspend fun browseAllObjects(
        query: Query? = null,
        requestOptions: RequestOptions? = null,
        block: suspend ResponseSearch.(Int) -> Unit
    ) {
        val initial = browse(query, requestOptions)
        var cursor = initial.cursorOrNull
        var page = 0

        block(initial, page++)
        while (cursor != null) {
            val response = browse(cursor)

            block(response, page++)
            cursor = response.cursorOrNull
        }
    }
}