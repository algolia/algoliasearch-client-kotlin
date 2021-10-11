package com.algolia.search.model.rule

import com.algolia.search.serialize.KeyUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Redirect data container.
 */
@Serializable
public data class Redirect(
    /**
     * Redirect URL.
     */
    @SerialName(KeyUrl) val url: String? = null
)
