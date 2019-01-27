package com.algolia.search.response.creation

import com.algolia.search.model.common.Datable
import com.algolia.search.serialize.KeyCreatedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Creation(
    @SerialName(KeyCreatedAt) override val date: String
) : Datable