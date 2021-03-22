package com.algolia.search.model.response

import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.model.multicluster.UserID
import com.algolia.search.serialize.KeyClusters
import com.algolia.search.serialize.KeyPending
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseHasPendingMapping(
    @SerialName(KeyPending) val isPending: Boolean,
    @SerialName(KeyClusters) val clusters: Map<ClusterName, List<UserID>>? = null
)
