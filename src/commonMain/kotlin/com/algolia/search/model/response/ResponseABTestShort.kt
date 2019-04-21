package com.algolia.search.model.response

import com.algolia.search.helper.toABTestID
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.analytics.Variant
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray

@Serializable(ResponseABTestShort.Companion::class)
public data class ResponseABTestShort(
    val abTestId: ABTestID,
    val variantA: Variant,
    val variantB: Variant
) {

    @Serializer(ResponseABTestShort::class)
    companion object :
        KSerializer<ResponseABTestShort> {

        override fun serialize(encoder: Encoder, obj: ResponseABTestShort) {
            val json = json {
                KeyId to obj.abTestId
                KeyVariants to jsonArray {
                    +JsonNoDefaults.toJson(
                        KSerializerVariant,
                        obj.variantA
                    )
                    +JsonNoDefaults.toJson(
                        KSerializerVariant,
                        obj.variantB
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