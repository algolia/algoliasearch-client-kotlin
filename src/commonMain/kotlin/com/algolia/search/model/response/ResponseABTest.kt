package com.algolia.search.model.response

import com.algolia.search.model.IndexName
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.*
import com.algolia.search.toABTestID
import kotlinx.serialization.*
import kotlinx.serialization.json.*


@Serializable
data class ResponseABTest(
    @SerialName(KeyABTestID) val abTestID: ABTestID,
    @SerialName(KeyCreatedAt) val createdAt: String,
    @SerialName(KeyEndAt) val endAt: String,
    @SerialName(KeyName) val name: String,
    @SerialName(KeyStatus) val status: ABTestStatus,
    @Optional @SerialName(KeyConversionSignificance) val conversionSignificance: Float? = null,
    @Optional @SerialName(KeyClickSignificance) val clickSignificance: Float? = null,
    val variantA: Variant,
    val variantB: Variant
) {


    @Serializer(ResponseABTest::class)
    companion object : KSerializer<ResponseABTest> {

        override fun serialize(encoder: Encoder, obj: ResponseABTest) {
            val json = json {
                KeyABTestID to obj.abTestID
                KeyCreatedAt to obj.createdAt
                KeyEndAt to obj.endAt
                KeyName to obj.name
                KeyStatus to obj.status.raw
                obj.conversionSignificance?.let { KeyConversionSignificance to it }
                obj.clickSignificance?.let { KeyClickSignificance to it }
                KeyVariants to jsonArray {
                    +JsonNoNulls.toJson(Variant.serializer(), obj.variantA)
                    +JsonNoNulls.toJson(Variant.serializer(), obj.variantB)
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
                conversionSignificance = element[KeyConversionSignificance].floatOrNull,
                clickSignificance = element[KeyClickSignificance].floatOrNull,
                variantA = Json.plain.fromJson(Variant.serializer(), variants[0]),
                variantB = Json.plain.fromJson(Variant.serializer(), variants[1])
            )
        }
    }

    @Serializable
    data class Variant(
        @SerialName(KeyClickCount) val clickCount: Int,
        @SerialName(KeyConversionCount) val conversionCount: Int,
        @SerialName(KeyDescription) val description: String,
        @SerialName(KeyIndex) val indexName: IndexName,
        @SerialName(KeyTrafficPercentage) val trafficPercentage: Int,
        @Optional @SerialName(KeyConversionRate) val conversionRate: Float? = null,
        @Optional @SerialName(KeyNoResultCount) val noResultCount: Int? = null,
        @Optional @SerialName(KeyAverageClickPosition) val averageClickPosition: Int? = null,
        @Optional @SerialName(KeySearchCount) val searchCount: Long? = null,
        @Optional @SerialName(KeyTrackedSearchCount) val trackedSearchCount: Long? = null,
        @Optional @SerialName(KeyUserCount) val userCount: Long? = null,
        @Optional @SerialName(KeyClickThroughRate) val clickThroughRate: Float? = null,
        @Optional @SerialName(KeyCustomSearchParameters) val customSearchParameters: Query? = null
    )
}