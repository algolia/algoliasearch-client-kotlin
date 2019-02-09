package com.algolia.search.model.queryrule

import com.algolia.search.model.ObjectID
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.serialize.KeyPosition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Promotion(
    @SerialName(KeyObjectID) val objectID: ObjectID,
    @SerialName(KeyPosition) val position: Int
)