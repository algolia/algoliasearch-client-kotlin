package com.algolia.search.model.response.deletion

import com.algolia.search.ClientDate
import com.algolia.search.serialize.KeyDeletedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Deletion(
    @SerialName(KeyDeletedAt) val deletedAt: ClientDate
)