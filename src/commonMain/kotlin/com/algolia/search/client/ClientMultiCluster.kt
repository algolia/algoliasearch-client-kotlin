package com.algolia.search.client

import com.algolia.search.model.*
import com.algolia.search.endpoint.EndpointMultiCluster
import com.algolia.search.model.cluster.*
import com.algolia.search.serialize.*
import io.ktor.client.request.*
import kotlinx.serialization.json.json


internal class ClientMultiCluster(
    val client: Client
) : EndpointMultiCluster,
    Client by client {

    override suspend fun listClusters(requestOptions: RequestOptions?): List<ClusterInfo> {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters") { path ->
            httpClient.get<ClusterInfos>(path) {
                setRequestOptions(requestOptions)
            }.clusterInfos
        }
    }

    override suspend fun assignUserID(
        userID: UserID,
        clusterName: ClusterName,
        requestOptions: RequestOptions?
    ): ClusterCreated {
        return write.retry(requestOptions.computedWriteTimeout, "/1/clusters/mapping") { path ->
            httpClient.post<ClusterCreated>(path) {
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

    override suspend fun getTopUserID(requestOptions: RequestOptions?): List<Cluster> {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters/mapping/top") { path ->
            httpClient.get<TopUsers>(path) {
                setRequestOptions(requestOptions)
            }.topUsers.flatMap { entry -> entry.value.map { it.copy(clusterName = entry.key) } }
        }
    }

    override suspend fun listUserIDs(page: Int?, hitsPerPage: Int?, requestOptions: RequestOptions?): List<Cluster> {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters/mapping") { path ->
            httpClient.get<UserIDs>(path) {
                setRequestOptions(requestOptions)
                page?.let { parameter(KeyPage, it) }
                hitsPerPage?.let { parameter(KeyHitsPerPage, it) }
            }.userIDs
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
    ): ClusterHits {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters/mapping/search") { path ->
            httpClient.post<ClusterHits>(path) {
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