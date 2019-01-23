package com.algolia.search.saas.client

import com.algolia.search.saas.data.Cluster
import com.algolia.search.saas.data.Clusters
import com.algolia.search.saas.endpoint.EndpointMultiCluster
import io.ktor.client.request.get


internal class ClientMultiCluster(
    val client: Client
) : EndpointMultiCluster,
    Client by client {

    override suspend fun listClusters(requestOptions: RequestOptions?): List<Cluster> {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters") { path ->
            httpClient.get<Clusters>(path) {
                setRequestOptions(requestOptions)
            }.clusters
        }
    }
}