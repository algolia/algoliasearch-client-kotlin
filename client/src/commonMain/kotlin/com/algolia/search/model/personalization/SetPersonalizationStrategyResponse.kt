package com.algolia.search.model.personalization

import com.algolia.search.serialize.internal.Key
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
    @SerialName(Key.Status) val status: Int,
    /**
     * Status message
     */
    @SerialName(Key.Message) val message: String
)
