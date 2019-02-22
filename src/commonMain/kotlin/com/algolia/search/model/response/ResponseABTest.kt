package com.algolia.search.model.response

import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.serialize.*
import com.algolia.search.toABTestID
import kotlinx.serialization.*
import kotlinx.serialization.json.*


@Serializable(ResponseABTest.Companion::class)
data class ResponseABTest(
    val abTestID: ABTestID,
    val createdAt: String,
    val endAt: String,
    val name: String,
    val status: ABTestStatus,
    val variantA: ResponseVariant,
    val variantB: ResponseVariant,
    @Optional val clickSignificanceOrNull: Float? = null,
    @Optional val conversionSignificanceOrNull: Float? = null
) {

    @Transient
    val clickSignificance: Float
        get() = clickSignificanceOrNull!!

    @Transient
    val conversionSignificance: Float
        get() = conversionSignificanceOrNull!!

    @Serializer(ResponseABTest::class)
    companion object : KSerializer<ResponseABTest> {

        override fun serialize(encoder: Encoder, obj: ResponseABTest) {
            val json = json {
                KeyABTestID to obj.abTestID.raw
                KeyCreatedAt to obj.createdAt
                KeyEndAt to obj.endAt
                KeyName to obj.name
                KeyStatus to obj.status.raw
                obj.conversionSignificanceOrNull?.let { KeyConversionSignificance to it }
                obj.clickSignificanceOrNull?.let { KeyClickSignificance to it }
                KeyVariants to jsonArray {
                    +Json.noDefaults.toJson(ResponseVariant.serializer(), obj.variantA)
                    +Json.noDefaults.toJson(ResponseVariant.serializer(), obj.variantB)
                }
            }

            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): ResponseABTest {
            val element = decoder.asJsonInput().jsonObject
            val variants = element[KeyVariants].jsonArray

            return ResponseABTest(
                abTestID = element[KeyABTestID].long.toABTestID(),
                createdAt = element[KeyCreatedAt].content,
                endAt = element[KeyEndAt].content,
                name = element[KeyName].content,
                status = Json.parse(ABTestStatus, element[KeyStatus].content),
                conversionSignificanceOrNull = element[KeyConversionSignificance].floatOrNull,
                clickSignificanceOrNull = element[KeyClickSignificance].floatOrNull,
                variantA = Json.plain.fromJson(ResponseVariant.serializer(), variants[0]),
                variantB = Json.plain.fromJson(ResponseVariant.serializer(), variants[1])
            )
        }
    }
}