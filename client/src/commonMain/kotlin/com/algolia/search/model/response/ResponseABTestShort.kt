package com.algolia.search.model.response

import com.algolia.search.helper.toABTestID
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.analytics.Variant
import com.algolia.search.serialize.KSerializerVariant
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long

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

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(ResponseABTestShort::class)
    public companion object :
        KSerializer<ResponseABTestShort> {

        override fun serialize(encoder: Encoder, value: ResponseABTestShort) {
            val json = buildJsonObject {
                Key.Id to value.abTestId
                put(
                    Key.Variants,
                    buildJsonArray {
                        add(JsonNoDefaults.encodeToJsonElement(KSerializerVariant, value.variantA))
                        add(JsonNoDefaults.encodeToJsonElement(KSerializerVariant, value.variantB))
                    }
                )
            }

            encoder.asJsonOutput().encodeJsonElement(json)
        }

        override fun deserialize(decoder: Decoder): ResponseABTestShort {
            val json = decoder.asJsonInput().jsonObject
            val variants = json.getValue(Key.Variants).jsonArray

            return ResponseABTestShort(
                abTestId = json.getValue(Key.Id).jsonPrimitive.long.toABTestID(),
                variantA = JsonNoDefaults.decodeFromJsonElement(
                    KSerializerVariant,
                    variants[0]
                ),
                variantB = JsonNoDefaults.decodeFromJsonElement(
                    KSerializerVariant,
                    variants[1]
                )
            )
        }
    }
}
