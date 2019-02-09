package com.algolia.search.endpoint

import com.algolia.search.client.APIWrapper
import com.algolia.search.client.RequestOptions
import com.algolia.search.client.setRequestOptions
import com.algolia.search.model.ClusterName
import com.algolia.search.model.UserID
import com.algolia.search.model.request.RequestSearchUserID
import com.algolia.search.model.response.*
import com.algolia.search.model.response.creation.Creation
import com.algolia.search.model.response.deletion.Deletion
import com.algolia.search.serialize.*
import io.ktor.client.request.*
import kotlinx.serialization.json.json


internal class EndpointMulticlusterImpl(
    val api: APIWrapper
) : EndpointMultiCluster,
    APIWrapper by api {

    private val route = "/1/clusters"

    override suspend fun listClusters(requestOptions: RequestOptions?): ResponseListClusters {
        return read.retry(requestOptions.computedReadTimeout, route) { path ->
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
        val bodyString = json { KeyCluster to clusterName.raw }.toString()

        return write.retry(requestOptions.computedWriteTimeout, "$route/mapping") { path ->
            httpClient.post<Creation>(path) {
                body = bodyString
                header(KeyAlgoliaUserID, userID.raw)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getUserID(userID: UserID, requestOptions: RequestOptions?): ResponseUserID {
        return read.retry(requestOptions.computedReadTimeout, "$route/mapping/$userID") { path ->
            httpClient.get<ResponseUserID>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getTopUserID(requestOptions: RequestOptions?): ResponseTopUserID {
        return read.retry(requestOptions.computedReadTimeout, "$route/mapping/top") { path ->
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
        return read.retry(requestOptions.computedReadTimeout, "$route/mapping") { path ->
            httpClient.get<ResponseListUserIDs>(path) {
                parameter(KeyPage, page)
                parameter(KeyHitsPerPage, hitsPerPage)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun removeUserID(userID: UserID, requestOptions: RequestOptions?): Deletion {
        return write.retry(requestOptions.computedWriteTimeout, "$route/mapping") { path ->
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
        val request = RequestSearchUserID(query, clusterName, page, hitsPerPage)
        val bodyString = JsonNoNulls.stringify(RequestSearchUserID.serializer(), request)

        return read.retry(requestOptions.computedReadTimeout, "$route/mapping/search") { path ->
            httpClient.post<ResponseSearchUserID>(path) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }
}