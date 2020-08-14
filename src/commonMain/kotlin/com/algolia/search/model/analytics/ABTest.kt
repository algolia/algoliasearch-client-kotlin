package com.algolia.search.model.analytics

import com.algolia.search.model.ClientDate
import com.algolia.search.serialize.JsonNoDefaults
import com.algolia.search.serialize.KeyEndAt
import com.algolia.search.serialize.KeyName
import com.algolia.search.serialize.KeyVariants
import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray

/**
 * [ABTest] applied to compare analytics performance between two indices.
 */
@Serializable(ABTest.Companion::class)
public data class ABTest(
    /**
     * Name of the [ABTest].
     */
    @SerialName(KeyName) val name: String,
    /**
     * A date to automatically end an [ABTest] at a specific time.
     */
    @SerialName(KeyEndAt) val endAt: ClientDate,
    /**
     * The base index [Variant].
     */
    val variantA: Variant,
    /**
     * The index [Variant] to test against.
     */
    val variantB: Variant
) {

    @Serializer(ABTest::class)
    public companion object : KSerializer<ABTest> {

        override fun serialize(encoder: Encoder, value: ABTest) {
            val json = json {
                KeyName to value.name
                KeyEndAt to value.endAt.raw
                KeyVariants to jsonArray {
                    +JsonNoDefaults.toJson(Variant.serializer(), value.variantA)
                    +JsonNoDefaults.toJson(Variant.serializer(), value.variantB)
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
