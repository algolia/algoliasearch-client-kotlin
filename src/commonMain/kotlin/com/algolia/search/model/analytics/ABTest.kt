package com.algolia.search.model.analytics

import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.content
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray


@Serializable(ABTest.Companion::class)
data class ABTest(
    @SerialName(KeyName) val name: String,
    @SerialName(KeyEndAt) val endAt: String,
    val variantA: Variant,
    val variantB: Variant
) {

    @Serializer(ABTest::class)
    companion object : KSerializer<ABTest> {

        override fun serialize(encoder: Encoder, obj: ABTest) {
            val json = json {
                KeyName to obj.name
                KeyEndAt to obj.endAt
                KeyVariants to jsonArray {
                    +Json.plain.toJson(Variant.serializer(), obj.variantA)
                    +Json.plain.toJson(Variant.serializer(), obj.variantB)
                }
            }

            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): ABTest {
            val json = decoder.asJsonInput().jsonObject
            val variants = json[KeyVariants].jsonArray

            return ABTest(
                name = json[KeyName].content,
                endAt = json[KeyEndAt].content,
                variantA = Json.plain.fromJson(Variant.serializer(), variants[0]),
                variantB = Json.plain.fromJson(Variant.serializer(), variants[1])
            )
        }
    }
}