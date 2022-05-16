package com.algolia.search.model.response

import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.model.multicluster.UserID
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseHasPendingMapping(
    @SerialName(Key.Pending) val isPending: Boolean,
    @SerialName(Key.Clusters) val clusters: Map<ClusterName, List<UserID>>? = null
)
