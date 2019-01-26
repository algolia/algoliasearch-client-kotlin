package com.algolia.search.model.cluster

import com.algolia.search.model.common.Datable
import com.algolia.search.serialize.KeyCreatedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CreateClusterResponse(
    @SerialName(KeyCreatedAt) override val date: String
) : Datable