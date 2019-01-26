package com.algolia.search.model.apiKey

import com.algolia.search.model.common.Datable
import com.algolia.search.serialize.KeyDeletedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DeleteAPIKeyResponse(
    @SerialName(KeyDeletedAt) override val date: String
) : Datable