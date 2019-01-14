package com.algolia.search.saas.endpoint

import com.algolia.search.saas.data.IndexName


interface EndpointSynonym {

    val indexName: IndexName

    suspend fun addSynonym()
}