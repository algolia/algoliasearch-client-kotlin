package com.algolia.search.endpoint

import com.algolia.search.client.APIWrapper
import com.algolia.search.client.RequestOptions
import com.algolia.search.client.setConfiguration
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
        return read.retry(requestOptions.computedReadTimeout, route) { url ->
            httpClient.get<ResponseListClusters>(url) {
                setConfiguration(api)
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

        return write.retry(requestOptions.computedWriteTimeout, "$route/mapping") { url ->
            httpClient.post<Creation>(url) {
                setConfiguration(api)
                body = bodyString
                header(KeyAlgoliaUserID, userID.raw)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getUserID(userID: UserID, requestOptions: RequestOptions?): ResponseUserID {
        return read.retry(requestOptions.computedReadTimeout, "$route/mapping/$userID") { url ->
            httpClient.get<ResponseUserID>(url) {
                setConfiguration(api)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getTopUserID(requestOptions: RequestOptions?): ResponseTopUserID {
        return read.retry(requestOptions.computedReadTimeout, "$route/mapping/top") { url ->
            httpClient.get<ResponseTopUserID>(url) {
                setConfiguration(api)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun listUserIDs(
        page: Int?,
        hitsPerPage: Int?,
        requestOptions: RequestOptions?
    ): ResponseListUserIDs {
        return read.retry(requestOptions.computedReadTimeout, "$route/mapping") { url ->
            httpClient.get<ResponseListUserIDs>(url) {
                setConfiguration(api)
                parameter(KeyPage, page)
                parameter(KeyHitsPerPage, hitsPerPage)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun removeUserID(userID: UserID, requestOptions: RequestOptions?): Deletion {
        return write.retry(requestOptions.computedWriteTimeout, "$route/mapping") { url ->
            httpClient.delete<Deletion>(url) {
                setConfiguration(api)
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

        return read.retry(requestOptions.computedReadTimeout, "$route/mapping/search") { url ->
            httpClient.post<ResponseSearchUserID>(url) {
                setConfiguration(api)
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }
}