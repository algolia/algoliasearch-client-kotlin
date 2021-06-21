package com.algolia.search.endpoint

import com.algolia.search.model.ApplicationID
import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.model.multicluster.UserID
import com.algolia.search.model.multicluster.UserIDQuery
import com.algolia.search.model.response.ResponseHasPendingMapping
import com.algolia.search.model.response.ResponseListClusters
import com.algolia.search.model.response.ResponseListClusters.Cluster
import com.algolia.search.model.response.ResponseListUserIDs
import com.algolia.search.model.response.ResponseSearchUserID
import com.algolia.search.model.response.ResponseTopUserID
import com.algolia.search.model.response.ResponseUserID
import com.algolia.search.model.response.creation.Creation
import com.algolia.search.model.response.deletion.Deletion
import com.algolia.search.transport.RequestOptions

/**
 * [Documentation][https://www.algolia.com/doc/api-client/methods/multi-cluster/?language=kotlin]
 */
public interface EndpointMultiCluster {

    /**
     * List the [Cluster] available in a multi-clusters setup for a single [ApplicationID].
     *
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun listClusters(requestOptions: RequestOptions? = null): ResponseListClusters

    /**
     * List the [UserID] assigned to a multi-clusters [ApplicationID].
     * The data returned will usually be a few seconds behind real-time, because [UserID] usage may take up to a few
     * seconds to propagate to the different clusters.
     *
     * @param page Page to fetch.
     * @param hitsPerPage Number of users to retrieve per page.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun listUserIDs(
        page: Int? = null,
        hitsPerPage: Int? = null,
        requestOptions: RequestOptions? = null
    ): ResponseListUserIDs

    /**
     * Assign or Move a [UserID] to a cluster.
     * The time it takes to migrate (move) a user is proportional to the amount of data linked to the [UserID].
     *
     * @param userID [UserID] to assign. If [UserID] is unknown, we will assign the [UserID] to the cluster, otherwise
     * the operation will move the [UserID] and its associated data from its current cluster to the new one specified by
     * [ClusterName].
     * @param clusterName The [ClusterName] destination.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun assignUserID(
        userID: UserID,
        clusterName: ClusterName,
        requestOptions: RequestOptions? = null
    ): Creation

    /**
     * Returns the [UserID] data stored in the mapping. The data returned will usually be a few seconds behind
     * real-time, because [UserID] usage may take up to a few seconds to propagate to the different clusters.
     *
     * @param userID [UserID] to fetch.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun getUserID(
        userID: UserID,
        requestOptions: RequestOptions? = null
    ): ResponseUserID

    /**
     * Get the top 10 [ResponseUserID] with the highest number of records per cluster.
     * The data returned will usually be a few seconds behind real-time, because userID usage may take up to a few
     * seconds to propagate to the different clusters.
     *
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun getTopUserID(requestOptions: RequestOptions? = null): ResponseTopUserID

    /**
     * Remove a [UserID] and its associated data from the multi-clusters. Even if the [UserID] doesn’t exist,
     * the request to remove the [UserID] will still succeed because of the asynchronous handling of this request.
     *
     * @param userID [UserID] to remove.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun removeUserID(
        userID: UserID,
        requestOptions: RequestOptions? = null
    ): Deletion

    /**
     * Search for [UserID]. The data returned will usually be a few seconds behind real-time, because userID usage may
     * take up to a few seconds propagate to the different clusters.
     *
     * @param query The [UserIDQuery].
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun searchUserID(
        query: UserIDQuery = UserIDQuery(),
        requestOptions: RequestOptions? = null
    ): ResponseSearchUserID

    /**
     * Assign or move [userIDs] to a [clusterName].
     * The time it takes to migrate (move) a user is proportional to the amount of data linked to each [UserID].
     *
     * @param userIDs List of [UserID] to save
     * @param clusterName The [ClusterName]
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun assignUserIds(
        userIDs: List<UserID>,
        clusterName: ClusterName,
        requestOptions: RequestOptions? = null
    ): Creation

    /**
     * @param retrieveMapping If set to true, retrieves [ResponseHasPendingMapping.clusters].
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun hasPendingMapping(
        retrieveMapping: Boolean = false,
        requestOptions: RequestOptions? = null
    ): ResponseHasPendingMapping
}
