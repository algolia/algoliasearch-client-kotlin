package com.algolia.search.model.cluster

import com.algolia.search.model.ClusterName
import com.algolia.search.serialize.KeyTopUsers
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ListTopUsersResponse(
    @SerialName(KeyTopUsers) val topUsers: Map<ClusterName, List<Cluster>>
)