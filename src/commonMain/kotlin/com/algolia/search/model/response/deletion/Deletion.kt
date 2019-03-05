package com.algolia.search.model.response.deletion

import com.algolia.search.model.ClientDate
import com.algolia.search.serialize.KeyDeletedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class Deletion(
    @SerialName(KeyDeletedAt) val deletedAt: ClientDate
)