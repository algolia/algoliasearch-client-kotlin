package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
internal data class GetAPIKeys(
    val keys: List<GetAPIKey>
)