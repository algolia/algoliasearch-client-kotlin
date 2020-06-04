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

    /**
     * Promoted rank.
     */
    abstract val position: Int

    @Serializable
    data class Single(
        /**
         * Unique identifier of the object to promote.
         */
        @SerialName(KeyObjectID) val objectID: ObjectID,
        @SerialName(KeyPosition) override val position: Int
    ) : Promotion()

    @Serializable
    data class Multiple(
        /**
         * List of unique identifiers of the objects to promote.
         */
        @SerialName(KeyObjectIDs) val objectIDs: List<ObjectID>,
        @SerialName(KeyPosition) override val position: Int
    ) : Promotion()
}

/**
 * Creates an instance of [Promotion.Single].
 *
 * @param objectID unique identifier of the object to promote
 * @param position promoted rank for the object.
 */
public fun Promotion(objectID: ObjectID, position: Int): Promotion.Single = Promotion.Single(objectID, position)

/**
 * Creates an instance of [Promotion.Multiple].
 *
 * @param objectIDs list of unique identifiers of the objects to promote.
 * @param position promoted rank for the objects.
 */
public fun Promotion(objectIDs: List<ObjectID>, position: Int): Promotion.Multiple = Promotion.Multiple(objectIDs, position)
