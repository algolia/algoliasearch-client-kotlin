package com.algolia.search.client

import com.algolia.search.endpoint.*
import com.algolia.search.model.IndexName


data class Index internal constructor(
    private val api: APIWrapper,
    override val indexName: IndexName
) : EndpointSearch by EndpointSearchImpl(api, indexName),
    EndpointSettings by EndpointSettingsImpl(api, indexName),
    EndpointAdvanced by EndpointAdvancedImpl(api, indexName),
    EndpointIndex by EndpointIndexImpl(api, indexName),
    EndpointIndexing by EndpointIndexingImpl(api, indexName),
    EndpointSynonym by EndpointSynonymImpl(api, indexName),
    EndpointQueryRule by EndpointQueryRuleImpl(api, indexName),
    EndpointAPIKeyIndex by EndpointAPIKeyIndexImpl(api, indexName)