package com.algolia.search.saas.model.api_key

import kotlinx.serialization.Serializable


@Serializable
internal data class GetAPIKeys(
    val keys: List<GetAPIKey>
)