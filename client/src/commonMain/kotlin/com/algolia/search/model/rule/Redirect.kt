package com.algolia.search.model.rule

import kotlinx.serialization.Serializable

@Serializable
public data class Redirect(
    val url: String,
)
