package com.algolia.search.model.response

import com.algolia.search.helper.toABTestID
import com.algolia.search.model.ClientDate
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.serialize.Json
import com.algolia.search.serialize.JsonNoDefaults
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
data class ResponseABTest(
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

    val clickSignificance: Float
        get() = clickSignificanceOrNull!!

    val conversionSignificance: Float
        get() = conversionSignificanceOrNull!!

    @Serializer(ResponseABTest::class)
    companion object : KSerializer<ResponseABTest> {

        override fun serialize(encoder: Encoder, obj: ResponseABTest) {
            val json = json {
                KeyABTestID to obj.abTestID.raw
                KeyCreatedAt to obj.createdAt
                KeyEndAt to obj.endAt.raw
                KeyName to obj.name
                KeyStatus to obj.status.raw
                obj.conversionSignificanceOrNull?.let { KeyConversionSignificance to it }
                obj.clickSignificanceOrNull?.let { KeyClickSignificance to it }
                KeyVariants to jsonArray {
                    +JsonNoDefaults.toJson(ResponseVariant.serializer(), obj.variantA)
                    +JsonNoDefaults.toJson(ResponseVariant.serializer(), obj.variantB)
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
                status = Json.parse(ABTestStatus, element.getPrimitive(KeyStatus).content),
                conversionSignificanceOrNull = element.getPrimitive(KeyConversionSignificance).floatOrNull,
                clickSignificanceOrNull = element.getPrimitive(KeyClickSignificance).floatOrNull,
                variantA = Json.fromJson(ResponseVariant.serializer(), variants[0]),
                variantB = Json.fromJson(ResponseVariant.serializer(), variants[1])
            )
        }
    }
}
