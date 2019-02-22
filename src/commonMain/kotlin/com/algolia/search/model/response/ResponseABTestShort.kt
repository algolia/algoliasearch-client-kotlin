package com.algolia.search.model.response

import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.analytics.Variant
import com.algolia.search.serialize.*
import com.algolia.search.toABTestID
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.long

@Serializable(ResponseABTestShort.Companion::class)
data class ResponseABTestShort(
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
                    +Json.noDefaults.toJson(
                        KSerializerVariant,
                        obj.variantA
                    )
                    +Json.noDefaults.toJson(
                        KSerializerVariant,
                        obj.variantB
                    )
                }
            }

            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): ResponseABTestShort {
            val json = decoder.asJsonInput().jsonObject
            val variants = json[KeyVariants].jsonArray

            return ResponseABTestShort(
                abTestId = json[KeyId].long.toABTestID(),
                variantA = Json.noDefaults.fromJson(
                    KSerializerVariant,
                    variants[0]
                ),
                variantB = Json.noDefaults.fromJson(
                    KSerializerVariant,
                    variants[1]
                )
            )
        }
    }
}