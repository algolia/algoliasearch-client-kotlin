package com.algolia.search.model.response.creation

import com.algolia.search.model.ClientDate
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Creation(
    /**
     * The date at which the creation happened.
     */
    @SerialName(Key.CreatedAt) val createdAt: ClientDate
)
