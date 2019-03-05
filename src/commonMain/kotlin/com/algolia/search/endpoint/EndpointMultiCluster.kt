package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.ClusterName
import com.algolia.search.model.UserID
import com.algolia.search.model.response.*
import com.algolia.search.model.response.creation.Creation
import com.algolia.search.model.response.deletion.Deletion


public interface EndpointMultiCluster {

    suspend fun listClusters(requestOptions: RequestOptions? = null): ResponseListClusters

    suspend fun listUserIDs(
        page: Int? = null,
        hitsPerPage: Int? = null,
        requestOptions: RequestOptions? = null
    ): ResponseListUserIDs

    suspend fun assignUserID(
        userID: UserID,
        clusterName: ClusterName,
        requestOptions: RequestOptions? = null
    ): Creation

    suspend fun getUserID(userID: UserID, requestOptions: RequestOptions? = null): ResponseUserID

    suspend fun getTopUserID(requestOptions: RequestOptions? = null): ResponseTopUserID

    suspend fun removeUserID(userID: UserID, requestOptions: RequestOptions? = null): Deletion

    suspend fun searchUserID(
        query: String? = null,
        clusterName: ClusterName? = null,
        page: Int? = null,
        hitsPerPage: Int? = null,
        requestOptions: RequestOptions? = null
    ): ResponseSearchUserID
}