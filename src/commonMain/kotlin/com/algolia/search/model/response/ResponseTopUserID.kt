package com.algolia.search.model.response

import com.algolia.search.model.ClusterName
import com.algolia.search.serialize.KeyTopUsers
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class ResponseTopUserID(
    @SerialName(KeyTopUsers) val topUsers: Map<ClusterName, List<ResponseUserID>>
)