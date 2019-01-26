package com.algolia.search.model.cluster

import com.algolia.search.model.ObjectID
import com.algolia.search.model.UserID
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable


@Serializable
data class Cluster(
    @Optional val clusterName: ClusterName? = null,
    val userID: UserID,
    val nbRecords: Long,
    val dataSize: Long,
    @Optional val objectID: ObjectID? = null
)