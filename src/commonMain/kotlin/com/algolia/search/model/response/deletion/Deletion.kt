package com.algolia.search.model.response.deletion

import com.algolia.search.model.ClientDate
import com.algolia.search.serialize.KeyDeletedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Deletion(
    /**
     * The date at which the deletion happened.
     */
    @SerialName(KeyDeletedAt) val deletedAt: ClientDate
)
