package com.algolia.search.client

import com.algolia.search.endpoint.EndpointMultiCluster
import com.algolia.search.model.ClusterName
import com.algolia.search.model.UserID
import com.algolia.search.model.cluster.ResponseCluster
import com.algolia.search.serialize.*
import io.ktor.client.request.*
import kotlinx.serialization.json.json


internal class ClientMultiCluster(
    val client: Client
) : EndpointMultiCluster,
    Client by client {

    override suspend fun listClusters(requestOptions: RequestOptions?): ResponseCluster.Infos {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters") { path ->
            httpClient.get<ResponseCluster.Infos>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun assignUserID(
        userID: UserID,
        clusterName: ClusterName,
        requestOptions: RequestOptions?
    ): ResponseCluster.Create {
        return write.retry(requestOptions.computedWriteTimeout, "/1/clusters/mapping") { path ->
            httpClient.post<ResponseCluster.Create>(path) {
                setRequestOptions(requestOptions)
                header(KeyAlgoliaUserID, userID.raw)
                body = json { KeyCluster to clusterName.raw }.toString()
            }
        }
    }

    override suspend fun getUserID(userID: UserID, requestOptions: RequestOptions?): ResponseCluster.Get {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters/mapping/$userID") { path ->
            httpClient.get<ResponseCluster.Get>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getTopUserID(requestOptions: RequestOptions?): ResponseCluster.TopUsers {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters/mapping/top") { path ->
            httpClient.get<ResponseCluster.TopUsers>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun listUserIDs(
        page: Int?,
        hitsPerPage: Int?,
        requestOptions: RequestOptions?
    ): ResponseCluster.GetList {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters/mapping") { path ->
            httpClient.get<ResponseCluster.GetList>(path) {
                setRequestOptions(requestOptions)
                parameter(KeyPage, page)
                parameter(KeyHitsPerPage, hitsPerPage)
            }
        }
    }

    override suspend fun removeUserID(userID: UserID, requestOptions: RequestOptions?): ResponseCluster.Delete {
        return write.retry(requestOptions.computedWriteTimeout, "/1/clusters/mapping") { path ->
            httpClient.delete<ResponseCluster.Delete>(path) {
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
    ): ResponseCluster.Search {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters/mapping/search") { path ->
            httpClient.post<ResponseCluster.Search>(path) {
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