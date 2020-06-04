package com.algolia.search.model.response

import com.algolia.search.helper.toABTestID
import com.algolia.search.model.ClientDate
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.serialize.Json
import com.algolia.search.serialize.JsonNoDefaults
import com.algolia.search.serialize.JsonNonStrict
import com.algolia.search.serialize.KeyABTestID
import com.algolia.search.serialize.KeyClickSignificance
import com.algolia.search.serialize.KeyConversionSignificance
import com.algolia.search.serialize.KeyCreatedAt
import com.algolia.search.serialize.KeyEndAt
import com.algolia.search.serialize.KeyName
import com.algolia.search.serialize.KeyStatus
import com.algolia.search.serialize.KeyVariants
import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray

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
    companion object : KSerializer<ResponseABTest> {

        override fun serialize(encoder: Encoder, value: ResponseABTest) {
            val json = json {
                KeyABTestID to value.abTestID.raw
                KeyCreatedAt to value.createdAt
                KeyEndAt to value.endAt.raw
                KeyName to value.name
                KeyStatus to value.status.raw
                value.conversionSignificanceOrNull?.let { KeyConversionSignificance to it }
                value.clickSignificanceOrNull?.let { KeyClickSignificance to it }
                KeyVariants to jsonArray {
                    +JsonNoDefaults.toJson(ResponseVariant.serializer(), value.variantA)
                    +JsonNoDefaults.toJson(ResponseVariant.serializer(), value.variantB)
                }
            }

            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): ResponseABTest {
            val element = decoder.asJsonInput().jsonObject
            val variants = element.getArray(KeyVariants)

            return ResponseABTest(
                abTestID = element.getPrimitive(KeyABTestID).long.toABTestID(),
                createdAt = element.getPrimitive(KeyCreatedAt).content,
                endAt = ClientDate(element.getPrimitive(KeyEndAt).content),
                name = element.getPrimitive(KeyName).content,
                status = JsonNonStrict.parse(ABTestStatus, element.getPrimitive(KeyStatus).content),
                conversionSignificanceOrNull = element.getPrimitive(KeyConversionSignificance).floatOrNull,
                clickSignificanceOrNull = element.getPrimitive(KeyClickSignificance).floatOrNull,
                variantA = Json.fromJson(ResponseVariant.serializer(), variants[0]),
                variantB = Json.fromJson(ResponseVariant.serializer(), variants[1])
            )
        }
    }
}
