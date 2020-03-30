package com.algolia.search.model.response

import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.serialize.KeyTopUsers
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseTopUserID(
    /**
     * Mapping of [ClusterName] to top users.
     */
    @SerialName(KeyTopUsers) val topUsers: Map<ClusterName, List<ResponseUserID>>
)
