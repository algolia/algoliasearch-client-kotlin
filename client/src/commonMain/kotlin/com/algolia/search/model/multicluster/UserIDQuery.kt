package com.algolia.search.model.multicluster

import com.algolia.search.endpoint.EndpointMultiCluster
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Query to use with [EndpointMultiCluster.searchUserID].
 */
@Serializable
public data class UserIDQuery(
    /**
     * Engine default: "empty string"
     * Query to search. The search is a prefix search with typoTolerance. Use empty query to retrieve all users.
     */
    @SerialName(Key.Query) var query: String? = null,
    /**
     * Engine default: null
     * If specified only clusters assigned to this cluster can be returned.
     */
    @SerialName(Key.Cluster) var clusterName: ClusterName? = null,
    /**
     * Engine default: 0
     * Page to fetch.
     */
    @SerialName(Key.Params) var page: Int? = null,
    /**
     * Engine default: 20
     * Number of users to return by page.
     */
    @SerialName(Key.HitsPerPage) var hitsPerPage: Int? = null
)
