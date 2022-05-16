package com.algolia.search.model.response.revision

import com.algolia.search.model.ClientDate
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Revision(
    /**
     * The date at which the revision was created.
     */
    @SerialName(Key.UpdatedAt) val updatedAt: ClientDate
)
