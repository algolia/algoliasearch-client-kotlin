package com.algolia.search.endpoint

import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.model.multicluster.UserID
import com.algolia.search.model.multicluster.UserIDQuery
import com.algolia.search.model.response.*
import com.algolia.search.model.response.creation.Creation
import com.algolia.search.model.response.deletion.Deletion
import com.algolia.search.transport.RequestOptions


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
        query: UserIDQuery = UserIDQuery(),
        requestOptions: RequestOptions? = null
    ): ResponseSearchUserID
}