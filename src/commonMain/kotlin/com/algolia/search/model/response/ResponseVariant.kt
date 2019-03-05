package com.algolia.search.model.response

import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
data class ResponseVariant(
    @SerialName(KeyClickCount) val clickCount: Int,
    @SerialName(KeyConversionCount) val conversionCount: Int,
    @SerialName(KeyDescription) val description: String,
    @SerialName(KeyIndex) val indexName: IndexName,
    @SerialName(KeyTrafficPercentage) val trafficPercentage: Int,
    @Optional @SerialName(KeyConversionRate) val conversionRateOrNull: Float? = null,
    @Optional @SerialName(KeyNoResultCount) val noResultCountOrNull: Int? = null,
    @Optional @SerialName(KeyAverageClickPosition) val averageClickPositionOrNull: Int? = null,
    @Optional @SerialName(KeySearchCount) val searchCountOrNull: Long? = null,
    @Optional @SerialName(KeyTrackedSearchCount) val trackedSearchCountOrNull: Long? = null,
    @Optional @SerialName(KeyUserCount) val userCountOrNull: Long? = null,
    @Optional @SerialName(KeyClickThroughRate) val clickThroughRateOrNull: Float? = null,
    @Optional @SerialName(KeyCustomSearchParameters) val customSearchParametersOrNull: Query? = null
) {

    @Transient
    val conversionRate: Float
        get() = conversionRateOrNull!!

    @Transient
    val noResultCount: Int
        get() = noResultCountOrNull!!

    @Transient
    val averageClickPosition: Int
        get() = averageClickPositionOrNull!!

    @Transient
    val searchCount: Long
        get() = searchCountOrNull!!

    @Transient
    val trackedSearchCount: Long
        get() = trackedSearchCountOrNull!!

    @Transient
    val userCount: Long
        get() = userCountOrNull!!

    @Transient
    val clickThroughRate: Float
        get() = clickThroughRateOrNull!!

    @Transient
    val customSearchParameters: Query
        get() = customSearchParametersOrNull!!
}