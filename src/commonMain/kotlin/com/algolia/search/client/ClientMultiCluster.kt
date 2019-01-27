package com.algolia.search.client

import com.algolia.search.endpoint.EndpointMultiCluster
import com.algolia.search.model.ClusterName
import com.algolia.search.model.UserID
import com.algolia.search.response.*
import com.algolia.search.response.creation.Creation
import com.algolia.search.response.deletion.Deletion
import com.algolia.search.serialize.*
import io.ktor.client.request.*
import kotlinx.serialization.json.json


internal class ClientMultiCluster(
    val client: Client
) : EndpointMultiCluster,
    Client by client {

    override suspend fun listClusters(requestOptions: RequestOptions?): ResponseListClusters {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters") { path ->
            httpClient.get<ResponseListClusters>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun assignUserID(
        userID: UserID,
        clusterName: ClusterName,
        requestOptions: RequestOptions?
    ): Creation {
        return write.retry(requestOptions.computedWriteTimeout, "/1/clusters/mapping") { path ->
            httpClient.post<Creation>(path) {
                setRequestOptions(requestOptions)
                header(KeyAlgoliaUserID, userID.raw)
                body = json { KeyCluster to clusterName.raw }.toString()
            }
        }
    }

    override suspend fun getUserID(userID: UserID, requestOptions: RequestOptions?): ResponseUserID {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters/mapping/$userID") { path ->
            httpClient.get<ResponseUserID>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getTopUserID(requestOptions: RequestOptions?): ResponseTopUserID {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters/mapping/top") { path ->
            httpClient.get<ResponseTopUserID>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun listUserIDs(
        page: Int?,
        hitsPerPage: Int?,
        requestOptions: RequestOptions?
    ): ResponseListUserIDs {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters/mapping") { path ->
            httpClient.get<ResponseListUserIDs>(path) {
                setRequestOptions(requestOptions)
                parameter(KeyPage, page)
                parameter(KeyHitsPerPage, hitsPerPage)
            }
        }
    }

    override suspend fun removeUserID(userID: UserID, requestOptions: RequestOptions?): Deletion {
        return write.retry(requestOptions.computedWriteTimeout, "/1/clusters/mapping") { path ->
            httpClient.delete<Deletion>(path) {
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
    ): ResponseSearchUserID {
        return read.retry(requestOptions.computedReadTimeout, "/1/clusters/mapping/search") { path ->
            httpClient.post<ResponseSearchUserID>(path) {
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