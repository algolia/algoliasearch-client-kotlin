package com.algolia.search.model.analytics

import com.algolia.search.model.ClientDate
import com.algolia.search.serialize.KeyEndAt
import com.algolia.search.serialize.KeyName
import com.algolia.search.serialize.KeyVariants
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

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
    @OptIn(ExperimentalSerializationApi::class)
    public companion object : KSerializer<ABTest> {

        override fun serialize(encoder: Encoder, value: ABTest) {
            val json = buildJsonObject {
                put(KeyName, value.name)
                put(KeyEndAt, value.endAt.raw)
                put(
                    KeyVariants,
                    buildJsonArray {
                        add(JsonNoDefaults.encodeToJsonElement(Variant.serializer(), value.variantA))
                        add(JsonNoDefaults.encodeToJsonElement(Variant.serializer(), value.variantB))
                    }
                )
            }

            encoder.asJsonOutput().encodeJsonElement(json)
        }

        override fun deserialize(decoder: Decoder): ABTest {
            val json = decoder.asJsonInput().jsonObject
            val variants = json.getValue(KeyVariants).jsonArray

            return ABTest(
                name = json.getValue(KeyName).jsonPrimitive.content,
                endAt = ClientDate(json.getValue(KeyEndAt).jsonPrimitive.content),
                variantA = JsonNoDefaults.decodeFromJsonElement(Variant.serializer(), variants[0]),
                variantB = JsonNoDefaults.decodeFromJsonElement(Variant.serializer(), variants[1])
            )
        }
    }
}
