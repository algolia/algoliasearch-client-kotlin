package com.algolia.search.model.recommendation

import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *  Set strategy response
 */
@Serializable
public data class SetStrategyResponse(
    /**
     * Status code
     */
    @SerialName(KeyStatus) val status: Int,
    /**
     * Status message
     */
    @SerialName(KeyMessage) val message: String
)