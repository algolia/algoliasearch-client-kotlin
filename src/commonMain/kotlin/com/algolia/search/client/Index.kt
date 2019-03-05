package com.algolia.search.client

import com.algolia.search.endpoint.*
import com.algolia.search.model.IndexName


public data class Index internal constructor(
    internal val api: APIWrapper,
    override val indexName: IndexName
) : EndpointSearch by EndpointSearchImpl(api, indexName),
    EndpointSettings by EndpointSettingsImpl(api, indexName),
    EndpointAdvanced by EndpointAdvancedImpl(api, indexName),
    EndpointIndex by EndpointIndexImpl(api, indexName),
    EndpointIndexing by EndpointIndexingImpl(api, indexName),
    EndpointSynonym by EndpointSynonymImpl(api, indexName),
    EndpointRule by EndpointRuleImpl(api, indexName)