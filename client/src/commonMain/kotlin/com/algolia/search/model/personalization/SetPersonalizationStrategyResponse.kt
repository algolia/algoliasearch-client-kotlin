package com.algolia.search.model.personalization

import com.algolia.search.serialize.KeyMessage
import com.algolia.search.serialize.KeyStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *  Set strategy response
 */
@Serializable
public data class SetPersonalizationStrategyResponse(
    /**
     * Status code
     */
    @SerialName(KeyStatus) val status: Int,
    /**
     * Status message
     */
    @SerialName(KeyMessage) val message: String
)
