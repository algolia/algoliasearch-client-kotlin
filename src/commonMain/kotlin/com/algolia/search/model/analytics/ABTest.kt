package com.algolia.search.model.analytics

import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray


@Serializable(ABTest.Companion::class)
public data class ABTest(
    @SerialName(KeyName) val name: String,
    @SerialName(KeyEndAt) val endAt: String,
    val variantA: Variant,
    val variantB: Variant
) {

    @Serializer(ABTest::class)
    internal companion object : KSerializer<ABTest> {

        override fun serialize(encoder: Encoder, obj: ABTest) {
            val json = json {
                KeyName to obj.name
                KeyEndAt to obj.endAt
                KeyVariants to jsonArray {
                    +Json.noDefaults.toJson(Variant.serializer(), obj.variantA)
                    +Json.noDefaults.toJson(Variant.serializer(), obj.variantB)
                }
            }

            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): ABTest {
            val json = decoder.asJsonInput().jsonObject
            val variants = json.getArray(KeyVariants)

            return ABTest(
                name = json.getPrimitive(KeyName).content,
                endAt = json.getPrimitive(KeyEndAt).content,
                variantA = Json.noDefaults.fromJson(Variant.serializer(), variants[0]),
                variantB = Json.noDefaults.fromJson(Variant.serializer(), variants[1])
            )
        }
    }
}