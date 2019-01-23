package com.algolia.search.saas.endpoint

import com.algolia.search.saas.client.RequestOptions
import com.algolia.search.saas.data.Cluster


interface EndpointMultiCluster {

    suspend fun listClusters(requestOptions: RequestOptions? = null): List<Cluster>
}