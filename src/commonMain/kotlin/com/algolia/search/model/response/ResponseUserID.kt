package com.algolia.search.model.response

import com.algolia.search.model.ClusterName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.UserID
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseUserID(
    @Optional @SerialName(KeyClusterName) val clusterName: ClusterName? = null,
    @Optional @SerialName(KeyObjectID) val objectID: ObjectID? = null,
    @SerialName(KeyUserID) val userID: UserID,
    @SerialName(KeyNbRecords) val nbRecords: Long,
    @SerialName(KeyDataSize) val dataSize: Long
)