@file:Suppress("FunctionName")

package com.algolia.search.model.rule

import com.algolia.search.model.ObjectID
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.serialize.KeyObjectIDs
import com.algolia.search.serialize.KeyPosition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public sealed class Promotion {

    abstract val position: Int

    @Serializable
    data class Single(
        /**
         * Unique identifier of the object to promote.
         */
        @SerialName(KeyObjectID) val objectID: ObjectID,

        /**
         * Promoted rank for the object.
         */
        @SerialName(KeyPosition) override val position: Int
    ) : Promotion()

    @Serializable
    data class Multiple(
        /**
         * Unique identifier of the object to promote.
         */
        @SerialName(KeyObjectIDs) val objectIDs: List<ObjectID>,
        /**
         * Promoted rank for the object.
         */
        @SerialName(KeyPosition) override val position: Int
    ) : Promotion()
}

fun Promotion(objectID: ObjectID, position: Int): Promotion.Single = Promotion.Single(objectID, position)

fun Promotion(objectIDs: List<ObjectID>, position: Int): Promotion.Multiple = Promotion.Multiple(objectIDs, position)
