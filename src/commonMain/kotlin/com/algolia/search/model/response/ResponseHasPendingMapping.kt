package com.algolia.search.model.response

import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.model.multicluster.UserID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.algolia.search.serialize.KeyPending
import com.algolia.search.serialize.KeyClusters


@Serializable
public data class ResponseHasPendingMapping(
    @SerialName(KeyPending) val isPending: Boolean,
    @SerialName(KeyClusters) val clusters: Map<ClusterName, List<UserID>>? = null
)