package com.algolia.search.model.rule

import com.algolia.search.model.ObjectID
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.serialize.KeyPosition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class Promotion(
    /**
     * Unique identifier of the object to promote.
     */
    @SerialName(KeyObjectID) val objectID: ObjectID,
    /**
     * Promoted rank for the object.
     */
    @SerialName(KeyPosition) val position: Int
)