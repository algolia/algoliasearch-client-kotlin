package com.algolia.search.client

import com.algolia.search.endpoint.EndpointMultiCluster
import com.algolia.search.model.ClusterName
import com.algolia.search.model.Deleted
import com.algolia.search.model.UserID
import com.algolia.search.model.cluster.*
import com.algolia.search.serialize.*
import io.ktor.client.request.*
import kotlinx.serialization.json.json


internal class ClientMultiCluster(
    val client: Client
) : EndpointMultiCluster,
    Client by client {

    override suspend fun listClusters(requestOptions: RequestOptions?): ListClustersResponse {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters") { path ->
            httpClient.get<ListClustersResponse>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun assignUserID(
        userID: UserID,
        clusterName: ClusterName,
        requestOptions: RequestOptions?
    ): CreateClusterResponse {
        return write.retry(requestOptions.computedWriteTimeout, "/1/clusters/mapping") { path ->
            httpClient.post<CreateClusterResponse>(path) {
                setRequestOptions(requestOptions)
                header(KeyAlgoliaUserID, userID.raw)
                body = json { KeyCluster to clusterName.raw }.toString()
            }
        }
    }

    override suspend fun getUserID(userID: UserID, requestOptions: RequestOptions?): Cluster {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters/mapping/$userID") { path ->
            httpClient.get<Cluster>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getTopUserID(requestOptions: RequestOptions?): ListTopUsersResponse {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters/mapping/top") { path ->
            httpClient.get<ListTopUsersResponse>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun listUserIDs(
        page: Int?,
        hitsPerPage: Int?,
        requestOptions: RequestOptions?
    ): ListUserIDsResponse {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters/mapping") { path ->
            httpClient.get<ListUserIDsResponse>(path) {
                setRequestOptions(requestOptions)
                parameter(KeyPage, page)
                parameter(KeyHitsPerPage, hitsPerPage)
            }
        }
    }

    override suspend fun removeUserID(userID: UserID, requestOptions: RequestOptions?): Deleted {
        return write.retry(requestOptions.computedWriteTimeout, "/1/clusters/mapping") { path ->
            httpClient.delete<Deleted>(path) {
                header(KeyAlgoliaUserID, userID)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun searchUserID(
        query: String?,
        clusterName: ClusterName?,
        page: Int?,
        hitsPerPage: Int?,
        requestOptions: RequestOptions?
    ): SearchClusterResponse {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters/mapping/search") { path ->
            httpClient.post<SearchClusterResponse>(path) {
                setRequestOptions(requestOptions)
                body = json {
                    query?.let { KeyQuery to it }
                    clusterName?.let { KeyCluster to it }
                    page?.let { KeyParams to it }
                    hitsPerPage?.let { KeyHitsPerPage to it }
                }.toString()
            }
        }
    }
}