package com.algolia.search.model.multicluster

import com.algolia.search.endpoint.EndpointMultiCluster
import com.algolia.search.serialize.KeyCluster
import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyParams
import com.algolia.search.serialize.KeyQuery
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmOverloads


/**
 * Query to use with [EndpointMultiCluster.searchUserID].
 */
@Serializable
public data class UserIDQuery @JvmOverloads constructor(
    /**
     * Engine default: "empty string"
     * Query to search. The search is a prefix search with typoTolerance. Use empty query to retrieve all users.
     */
    @SerialName(KeyQuery) var query: String? = null,
    /**
     * Engine default: null
     * If specified only clusters assigned to this cluster can be returned.
     */
    @SerialName(KeyCluster) var clusterName: ClusterName? = null,
    /**
     * Engine default: 0
     * Page to fetch.
     */
    @SerialName(KeyParams) var page: Int? = null,
    /**
     * Engine default: 20
     * Number of users to return by page.
     */
    @SerialName(KeyHitsPerPage) var hitsPerPage: Int? = null
)