package com.algolia.search.model.response.creation

import com.algolia.search.ClientDate
import com.algolia.search.serialize.KeyCreatedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Creation(
    @SerialName(KeyCreatedAt) val createdAt: ClientDate
)