package com.algolia.search.endpoint

import com.algolia.search.client.*
import com.algolia.search.model.ClusterName
import com.algolia.search.model.UserID
import com.algolia.search.model.request.RequestSearchUserID
import com.algolia.search.model.response.*
import com.algolia.search.model.response.creation.Creation
import com.algolia.search.model.response.deletion.Deletion
import com.algolia.search.serialize.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json


internal class EndpointMulticlusterImpl(
    val api: APIWrapper
) : EndpointMultiCluster,
    APIWrapper by api {
    
    override suspend fun listClusters(requestOptions: RequestOptions?): ResponseListClusters {
        return retryRead(requestOptions, RouteClustersV1) { url ->
            httpClient.get<ResponseListClusters>(url) {
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

        return retryWrite(requestOptions, "$RouteClustersV1/mapping") { url ->
            httpClient.post<Creation>(url) {
                body = bodyString
                header(KeyAlgoliaUserID, userID.raw)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getUserID(userID: UserID, requestOptions: RequestOptions?): ResponseUserID {
        return retryRead(requestOptions, "$RouteClustersV1/mapping/$userID") { url ->
            httpClient.get<ResponseUserID>(url) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getTopUserID(requestOptions: RequestOptions?): ResponseTopUserID {
        return retryRead(requestOptions, "$RouteClustersV1/mapping/top") { url ->
            httpClient.get<ResponseTopUserID>(url) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun listUserIDs(
        page: Int?,
        hitsPerPage: Int?,
        requestOptions: RequestOptions?
    ): ResponseListUserIDs {
        return retryRead(requestOptions, "$RouteClustersV1/mapping") { url ->
            httpClient.get<ResponseListUserIDs>(url) {
                parameter(KeyPage, page)
                parameter(KeyHitsPerPage, hitsPerPage)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun removeUserID(userID: UserID, requestOptions: RequestOptions?): Deletion {
        return retryWrite(requestOptions, "$RouteClustersV1/mapping") { url ->
            httpClient.delete<Deletion>(url) {
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
        val bodyString = Json.noDefaults.stringify(RequestSearchUserID.serializer(), request)

        return retryRead(requestOptions, "$RouteClustersV1/mapping/search") { url ->
            httpClient.post<ResponseSearchUserID>(url) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }
}