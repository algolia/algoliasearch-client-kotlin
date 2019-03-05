package com.algolia.search.model.response.creation

import com.algolia.search.model.ClientDate
import com.algolia.search.serialize.KeyCreatedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class Creation(
    @SerialName(KeyCreatedAt) val createdAt: ClientDate
)