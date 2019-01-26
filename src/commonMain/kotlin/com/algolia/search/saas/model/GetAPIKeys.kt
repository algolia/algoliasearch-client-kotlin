package com.algolia.search.saas.model

import kotlinx.serialization.Serializable


@Serializable
internal data class GetAPIKeys(
    val keys: List<GetAPIKey>
)