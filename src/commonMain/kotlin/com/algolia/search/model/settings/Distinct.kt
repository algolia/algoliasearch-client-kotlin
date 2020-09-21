package com.algolia.search.model.settings

import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonPrimitive

/**
 * Enables de-duplication or grouping of results.
 * Distinct functionality is based on one attribute, as defined in [Settings.attributeForDistinct].
 * Using this attribute, you can limit the number of returned records that contain the same value in that attribute.
 */
@Serializable(Distinct.Companion::class)
public data class Distinct(val count: Int) {

    init {
        if (count < 0) throw IllegalArgumentException("Distinct must be a positive integer")
    }

    public companion object : KSerializer<Distinct> {

        override val descriptor: SerialDescriptor = Int.serializer().descriptor

        override fun serialize(encoder: Encoder, value: Distinct) {
            encoder.asJsonOutput().encodeJsonElement(JsonPrimitive(value.count))
        }

        override fun deserialize(decoder: Decoder): Distinct {
            val json = decoder.asJsonInput()
            val int = json.jsonPrimitive.intOrNull

            if (int != null) {
                return Distinct(int)
            }
            return json.jsonPrimitive.boolean.let { if (it) Distinct(1) else Distinct(0) }
        }
    }
}
