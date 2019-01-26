package com.algolia.search.model.cluster

import com.algolia.search.model.common.Datable
import com.algolia.search.serialize.KeyDeletedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DeleteClusterResponse(
    @SerialName(KeyDeletedAt) override val date: String
) : Datable