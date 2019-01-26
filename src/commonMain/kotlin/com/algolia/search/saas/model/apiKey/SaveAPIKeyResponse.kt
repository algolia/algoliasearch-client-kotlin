package com.algolia.search.saas.model.apiKey

import com.algolia.search.saas.model.APIKey
import com.algolia.search.saas.model.common.Datable
import com.algolia.search.saas.serialize.KeyCreatedAt
import com.algolia.search.saas.serialize.KeyKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SaveAPIKeyResponse(
    @SerialName(KeyKey) val apiKey: APIKey,
    @SerialName(KeyCreatedAt) override val date: String
) : Datable