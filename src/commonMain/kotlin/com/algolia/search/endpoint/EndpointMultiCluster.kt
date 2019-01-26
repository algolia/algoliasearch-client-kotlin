package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.ClusterName
import com.algolia.search.model.Deleted
import com.algolia.search.model.UserID
import com.algolia.search.model.cluster.*


interface EndpointMultiCluster {

    suspend fun listClusters(requestOptions: RequestOptions? = null): ListClustersResponse

    suspend fun listUserIDs(
        page: Int? = null,
        hitsPerPage: Int? = null,
        requestOptions: RequestOptions? = null
    ): ListUserIDsResponse

    suspend fun assignUserID(
        userID: UserID,
        clusterName: ClusterName,
        requestOptions: RequestOptions? = null
    ): CreateClusterResponse

    suspend fun getUserID(userID: UserID, requestOptions: RequestOptions? = null): Cluster

    suspend fun getTopUserID(requestOptions: RequestOptions? = null): ListTopUsersResponse

    suspend fun removeUserID(userID: UserID, requestOptions: RequestOptions? = null): Deleted

    suspend fun searchUserID(
        query: String? = null,
        clusterName: ClusterName? = null,
        page: Int? = null,
        hitsPerPage: Int? = null,
        requestOptions: RequestOptions? = null
    ): SearchClusterResponse
}