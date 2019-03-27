package com.algolia.search.model.multicluster

import com.algolia.search.serialize.KeyCluster
import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyParams
import com.algolia.search.serialize.KeyQuery
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class UserIDQuery(
    @SerialName(KeyQuery) var query: String? = null,
    @SerialName(KeyCluster) var clusterName: ClusterName? = null,
    @SerialName(KeyParams) var page: Int? = null,
    @SerialName(KeyHitsPerPage) var hitsPerPage: Int? = null
)