package com.algolia.search.saas.model.apiKey

import com.algolia.search.saas.model.common.Datable
import com.algolia.search.saas.serialize.KeyDeletedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DeleteAPIKeyResponse(
    @SerialName(KeyDeletedAt) override val date: String
) : Datable