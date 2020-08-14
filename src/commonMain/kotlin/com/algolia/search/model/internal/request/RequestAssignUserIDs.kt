package com.algolia.search.model.internal.request

import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.model.multicluster.UserID
import com.algolia.search.serialize.KeyCluster
import com.algolia.search.serialize.KeyUsers
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RequestAssignUserIDs(
    @SerialName(KeyCluster) val clusterName: ClusterName,
    @SerialName(KeyUsers) val userIDs: List<UserID>
)
