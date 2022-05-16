package com.algolia.search.model.response.deletion

import com.algolia.search.model.ClientDate
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Deletion(
    /**
     * The date at which the deletion happened.
     */
    @SerialName(Key.DeletedAt) val deletedAt: ClientDate
)
