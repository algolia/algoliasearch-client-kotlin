package com.algolia.search.model.request

import com.algolia.search.model.ClusterName
import com.algolia.search.serialize.KeyCluster
import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyParams
import com.algolia.search.serialize.KeyQuery
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class RequestSearchUserID(
    @SerialName(KeyQuery) val query: String? = null,
    @SerialName(KeyCluster) val clusterName: ClusterName? = null,
    @SerialName(KeyParams) val page: Int? = null,
    @SerialName(KeyHitsPerPage) val hitsPerPage: Int? = null
)