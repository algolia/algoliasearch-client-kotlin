package com.algolia.search.model.analytics

import com.algolia.search.model.ClientDate
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray


@Serializable(ABTest.Companion::class)
public data class ABTest(
    @SerialName(KeyName) val name: String,
    @SerialName(KeyEndAt) val endAt: ClientDate,
    val variantA: Variant,
    val variantB: Variant
) {

    @Serializer(ABTest::class)
    companion object : KSerializer<ABTest> {

        override fun serialize(encoder: Encoder, obj: ABTest) {
            val json = json {
                KeyName to obj.name
                KeyEndAt to obj.endAt.raw
                KeyVariants to jsonArray {
                    +JsonNoDefaults.toJson(Variant.serializer(), obj.variantA)
                    +JsonNoDefaults.toJson(Variant.serializer(), obj.variantB)
                }
            }

            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): ABTest {
            val json = decoder.asJsonInput().jsonObject
            val variants = json.getArray(KeyVariants)

            return ABTest(
                name = json.getPrimitive(KeyName).content,
                endAt = ClientDate(json.getPrimitive(KeyEndAt).content),
                variantA = JsonNoDefaults.fromJson(Variant.serializer(), variants[0]),
                variantB = JsonNoDefaults.fromJson(Variant.serializer(), variants[1])
            )
        }
    }
}