@file:Suppress("FunctionName")

package com.algolia.search.model.rule

import com.algolia.search.model.ObjectID
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonInput
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.jsonObject

@Serializable(Promotion.Companion::class)
public sealed class Promotion {

    /**
     * Promoted rank.
     */
    public abstract val position: Int

    @Serializable
    public data class Single(
        /**
         * Unique identifier of the object to promote.
         */
        @SerialName(Key.ObjectID) val objectID: ObjectID,
        @SerialName(Key.Position) override val position: Int
    ) : Promotion()

    @Serializable
    public data class Multiple(
        /**
         * List of unique identifiers of the objects to promote.
         */
        @SerialName(Key.ObjectIDs) val objectIDs: List<ObjectID>,
        @SerialName(Key.Position) override val position: Int
    ) : Promotion()

    public companion object : KSerializer<Promotion> {

        override val descriptor: SerialDescriptor = buildClassSerialDescriptor("promotion")

        override fun deserialize(decoder: Decoder): Promotion {
            val json = decoder.asJsonInput().jsonObject
            return when {
                json.containsKey(Key.ObjectID) -> Single.serializer().deserialize(decoder)
                json.containsKey(Key.ObjectIDs) -> Multiple.serializer().deserialize(decoder)
                else -> throw IllegalStateException("Unable to deserialize 'Promotion' object")
            }
        }

        override fun serialize(encoder: Encoder, value: Promotion) {
            when (value) {
                is Single -> Single.serializer().serialize(encoder, value)
                is Multiple -> Multiple.serializer().serialize(encoder, value)
            }
        }
    }
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
public fun Promotion(objectIDs: List<ObjectID>, position: Int): Promotion.Multiple =
    Promotion.Multiple(objectIDs, position)
