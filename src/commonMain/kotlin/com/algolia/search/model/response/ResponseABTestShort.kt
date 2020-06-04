package com.algolia.search.model.response

import com.algolia.search.helper.toABTestID
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.analytics.Variant
import com.algolia.search.serialize.JsonNoDefaults
import com.algolia.search.serialize.KSerializerVariant
import com.algolia.search.serialize.KeyId
import com.algolia.search.serialize.KeyVariants
import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray

/**
 * Short version of [ResponseABTest].
 */
@Serializable(ResponseABTestShort.Companion::class)
public data class ResponseABTestShort(
    /**
     * [ABTestID] of the [ABTest] test.
     */
    val abTestId: ABTestID,
    /**
     * The base index [Variant].
     */
    val variantA: Variant,
    /**
     * The index [Variant] to test against.
     */
    val variantB: Variant
) {

    @Serializer(ResponseABTestShort::class)
    companion object :
        KSerializer<ResponseABTestShort> {

        override fun serialize(encoder: Encoder, value: ResponseABTestShort) {
            val json = json {
                KeyId to value.abTestId
                KeyVariants to jsonArray {
                    +JsonNoDefaults.toJson(
                        KSerializerVariant,
                        value.variantA
                    )
                    +JsonNoDefaults.toJson(
                        KSerializerVariant,
                        value.variantB
                    )
                }
            }

            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): ResponseABTestShort {
            val json = decoder.asJsonInput().jsonObject
            val variants = json.getArray(KeyVariants)

            return ResponseABTestShort(
                abTestId = json.getPrimitive(KeyId).long.toABTestID(),
                variantA = JsonNoDefaults.fromJson(
                    KSerializerVariant,
                    variants[0]
                ),
                variantB = JsonNoDefaults.fromJson(
                    KSerializerVariant,
                    variants[1]
                )
            )
        }
    }
}
