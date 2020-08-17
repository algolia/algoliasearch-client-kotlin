package com.algolia.search.model.response

import com.algolia.search.helper.toABTestID
import com.algolia.search.model.ClientDate
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.serialize.KeyABTestID
import com.algolia.search.serialize.KeyClickSignificance
import com.algolia.search.serialize.KeyConversionSignificance
import com.algolia.search.serialize.KeyCreatedAt
import com.algolia.search.serialize.KeyEndAt
import com.algolia.search.serialize.KeyName
import com.algolia.search.serialize.KeyStatus
import com.algolia.search.serialize.KeyVariants
import com.algolia.search.serialize.internal.Json
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.JsonNonStrict
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.floatOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long
import kotlinx.serialization.json.put

@Serializable(ResponseABTest.Companion::class)
public data class ResponseABTest(
    /**
     * [ABTestID] of the [ABTest] test.
     */
    val abTestID: ABTestID,
    /**
     * [ABTest] significance based on click data.
     * Should be > 0.95 to be considered significant (no matter which variant is winning).
     */
    val clickSignificanceOrNull: Float? = null,
    /**
     * [ABTest] significance based on conversion data.
     * Should be > 0.95 to be considered significant (no matter which variant is winning)
     */
    val conversionSignificanceOrNull: Float? = null,
    /**
     * Time at which the [ABTest] has been created.
     */
    val createdAt: String,
    /**
     * Time at which the [ABTest] will automatically stop.
     */
    val endAt: ClientDate,
    /**
     * Name of the [ABTest].
     */
    val name: String,
    /**
     * Current [ABTestStatus] of the [ABTest].
     */
    val status: ABTestStatus,
    /**
     * The base index [ResponseVariant].
     */
    val variantA: ResponseVariant,
    /**
     * The index [ResponseVariant] to test against.
     */
    val variantB: ResponseVariant
) {

    public val clickSignificance: Float
        get() = clickSignificanceOrNull!!

    public val conversionSignificance: Float
        get() = conversionSignificanceOrNull!!

    @Serializer(ResponseABTest::class)
    public companion object : KSerializer<ResponseABTest> {

        override fun serialize(encoder: Encoder, value: ResponseABTest) {
            val json = buildJsonObject {
                put(KeyABTestID, value.abTestID.raw)
                put(KeyCreatedAt, value.createdAt)
                put(KeyEndAt, value.endAt.raw)
                put(KeyName, value.name)
                put(KeyStatus, value.status.raw)
                value.conversionSignificanceOrNull?.let { put(KeyConversionSignificance, it) }
                value.clickSignificanceOrNull?.let { put(KeyClickSignificance, it) }
                put(
                    KeyVariants,
                    buildJsonArray {
                        add(JsonNoDefaults.encodeToJsonElement(ResponseVariant.serializer(), value.variantA))
                        add(JsonNoDefaults.encodeToJsonElement(ResponseVariant.serializer(), value.variantB))
                    }
                )
            }

            encoder.asJsonOutput().encodeJsonElement(json)
        }

        override fun deserialize(decoder: Decoder): ResponseABTest {
            val element = decoder.asJsonInput().jsonObject
            val variants = element.getValue(KeyVariants).jsonArray

            return ResponseABTest(
                abTestID = element.getValue(KeyABTestID).jsonPrimitive.long.toABTestID(),
                createdAt = element.getValue(KeyCreatedAt).jsonPrimitive.content,
                endAt = ClientDate(element.getValue(KeyEndAt).jsonPrimitive.content),
                name = element.getValue(KeyName).jsonPrimitive.content,
                status = JsonNonStrict.decodeFromString(
                    ABTestStatus,
                    element.getValue(KeyStatus).jsonPrimitive.content
                ),
                conversionSignificanceOrNull = element.getValue(KeyConversionSignificance).jsonPrimitive.floatOrNull,
                clickSignificanceOrNull = element.getValue(KeyClickSignificance).jsonPrimitive.floatOrNull,
                variantA = Json.decodeFromJsonElement(ResponseVariant.serializer(), variants[0]),
                variantB = Json.decodeFromJsonElement(ResponseVariant.serializer(), variants[1])
            )
        }
    }
}
