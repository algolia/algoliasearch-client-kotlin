package com.algolia.search.model.settings

import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonLiteral

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

    companion object : KSerializer<Distinct> {

        override val descriptor = Int.serializer().descriptor

        override fun serialize(encoder: Encoder, value: Distinct) {
            encoder.asJsonOutput().encodeJson(JsonLiteral(value.count))
        }

        override fun deserialize(decoder: Decoder): Distinct {
            val json = decoder.asJsonInput()
            val int = json.primitive.intOrNull

            if (int != null) {
                return Distinct(int)
            }
            return json.primitive.boolean.let { if (it) Distinct(1) else Distinct(0) }
        }
    }
}
