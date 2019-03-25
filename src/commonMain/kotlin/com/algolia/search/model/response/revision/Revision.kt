package com.algolia.search.model.response.revision

import com.algolia.search.model.ClientDate
import com.algolia.search.serialize.KeyUpdatedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class Revision(
    @SerialName(KeyUpdatedAt) val updatedAt: ClientDate
)