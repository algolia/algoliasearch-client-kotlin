package com.algolia.search.model.cluster

import com.algolia.search.serialize.KeyUserIDs
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ListUserIDsResponse(
    @SerialName(KeyUserIDs) val userIDs: List<Cluster>
)