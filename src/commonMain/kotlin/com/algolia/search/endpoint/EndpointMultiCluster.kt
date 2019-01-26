package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.ClusterName
import com.algolia.search.model.UserID
import com.algolia.search.model.cluster.ResponseCluster


interface EndpointMultiCluster {

    suspend fun listClusters(requestOptions: RequestOptions? = null): ResponseCluster.Infos

    suspend fun listUserIDs(
        page: Int? = null,
        hitsPerPage: Int? = null,
        requestOptions: RequestOptions? = null
    ): ResponseCluster.GetList

    suspend fun assignUserID(
        userID: UserID,
        clusterName: ClusterName,
        requestOptions: RequestOptions? = null
    ): ResponseCluster.Create

    suspend fun getUserID(userID: UserID, requestOptions: RequestOptions? = null): ResponseCluster.Get

    suspend fun getTopUserID(requestOptions: RequestOptions? = null): ResponseCluster.TopUsers

    suspend fun removeUserID(userID: UserID, requestOptions: RequestOptions? = null): ResponseCluster.Delete

    suspend fun searchUserID(
        query: String? = null,
        clusterName: ClusterName? = null,
        page: Int? = null,
        hitsPerPage: Int? = null,
        requestOptions: RequestOptions? = null
    ): ResponseCluster.Search
}