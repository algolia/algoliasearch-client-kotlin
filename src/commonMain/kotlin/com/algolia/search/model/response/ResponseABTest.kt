package com.algolia.search.model.response

import com.algolia.search.helper.toABTestID
import com.algolia.search.model.ClientDate
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray


@Serializable(ResponseABTest.Companion::class)
public data class ResponseABTest(
    val abTestID: ABTestID,
    val createdAt: String,
    val endAt: ClientDate,
    val name: String,
    val status: ABTestStatus,
    val variantA: ResponseVariant,
    val variantB: ResponseVariant,
    val clickSignificanceOrNull: Float? = null,
    val conversionSignificanceOrNull: Float? = null
) {

    @Transient
    public val clickSignificance: Float
        get() = clickSignificanceOrNull!!

    @Transient
    public val conversionSignificance: Float
        get() = conversionSignificanceOrNull!!

    @Serializer(ResponseABTest::class)
    internal companion object : KSerializer<ResponseABTest> {

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
                    +Json.noDefaults.toJson(ResponseVariant.serializer(), obj.variantA)
                    +Json.noDefaults.toJson(ResponseVariant.serializer(), obj.variantB)
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
                variantA = Json.plain.fromJson(ResponseVariant.serializer(), variants[0]),
                variantB = Json.plain.fromJson(ResponseVariant.serializer(), variants[1])
            )
        }
    }
}