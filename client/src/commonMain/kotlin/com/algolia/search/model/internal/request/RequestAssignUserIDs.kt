package com.algolia.search.model.internal.request

import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.model.multicluster.UserID
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RequestAssignUserIDs(
    @SerialName(Key.Cluster) val clusterName: ClusterName,
    @SerialName(Key.Users) val userIDs: List<UserID>
)
