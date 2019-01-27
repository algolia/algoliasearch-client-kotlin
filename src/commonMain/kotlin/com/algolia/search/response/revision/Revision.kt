package com.algolia.search.response.revision

import com.algolia.search.model.Datable
import com.algolia.search.serialize.KeyUpdatedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Revision(
    @SerialName(KeyUpdatedAt) override val date: String
) : Datable