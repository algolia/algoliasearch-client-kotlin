package com.algolia.search.model.cluster

import com.algolia.search.model.common.Datable
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SearchClusterResponse(
    @SerialName(KeyHits) val hits: List<Cluster>,
    @SerialName(KeyNbHits) val nbHits: Int,
    @SerialName(KeyPage) val page: Int,
    @SerialName(KeyHitsPerPage) val hitsPerPage: Int,
    @SerialName(KeyUpdatedAt) override val date: String
) : Datable