package com.algolia.search.model.response

import com.algolia.search.helper.toABTestID
import com.algolia.search.model.ClientDate
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.serialize.internal.Json
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.JsonNonStrict
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

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(ResponseABTest::class)
    public companion object : KSerializer<ResponseABTest> {

        override fun serialize(encoder: Encoder, value: ResponseABTest) {
            val json = buildJsonObject {
                put(Key.ABTestID, value.abTestID.raw)
                put(Key.CreatedAt, value.createdAt)
                put(Key.EndAt, value.endAt.raw)
                put(Key.Name, value.name)
                put(Key.Status, value.status.raw)
                value.conversionSignificanceOrNull?.let { put(Key.ConversionSignificance, it) }
                value.clickSignificanceOrNull?.let { put(Key.ClickSignificance, it) }
                put(
                    Key.Variants,
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
            val variants = element.getValue(Key.Variants).jsonArray

            return ResponseABTest(
                abTestID = element.getValue(Key.ABTestID).jsonPrimitive.long.toABTestID(),
                createdAt = element.getValue(Key.CreatedAt).jsonPrimitive.content,
                endAt = ClientDate(element.getValue(Key.EndAt).jsonPrimitive.content),
                name = element.getValue(Key.Name).jsonPrimitive.content,
                status = JsonNonStrict.decodeFromString(
                    ABTestStatus,
                    element.getValue(Key.Status).jsonPrimitive.content
                ),
                conversionSignificanceOrNull = element.getValue(Key.ConversionSignificance).jsonPrimitive.floatOrNull,
                clickSignificanceOrNull = element.getValue(Key.ClickSignificance).jsonPrimitive.floatOrNull,
                variantA = Json.decodeFromJsonElement(ResponseVariant.serializer(), variants[0]),
                variantB = Json.decodeFromJsonElement(ResponseVariant.serializer(), variants[1])
            )
        }
    }
}
