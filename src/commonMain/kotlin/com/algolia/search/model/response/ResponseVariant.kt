package com.algolia.search.model.response

import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
public data class ResponseVariant(
    @SerialName(KeyClickCount) val clickCount: Int,
    @SerialName(KeyConversionCount) val conversionCount: Int,
    @SerialName(KeyDescription) val description: String,
    @SerialName(KeyIndex) val indexName: IndexName,
    @SerialName(KeyTrafficPercentage) val trafficPercentage: Int,
    @SerialName(KeyConversionRate) val conversionRateOrNull: Float? = null,
    @SerialName(KeyNoResultCount) val noResultCountOrNull: Int? = null,
    @SerialName(KeyAverageClickPosition) val averageClickPositionOrNull: Int? = null,
    @SerialName(KeySearchCount) val searchCountOrNull: Long? = null,
    @SerialName(KeyTrackedSearchCount) val trackedSearchCountOrNull: Long? = null,
    @SerialName(KeyUserCount) val userCountOrNull: Long? = null,
    @SerialName(KeyClickThroughRate) val clickThroughRateOrNull: Float? = null,
    @SerialName(KeyCustomSearchParameters) val customSearchParametersOrNull: Query? = null
) {

    @Transient
    public val conversionRate: Float
        get() = conversionRateOrNull!!

    @Transient
    public val noResultCount: Int
        get() = noResultCountOrNull!!

    @Transient
    public val averageClickPosition: Int
        get() = averageClickPositionOrNull!!

    @Transient
    public val searchCount: Long
        get() = searchCountOrNull!!

    @Transient
    public val trackedSearchCount: Long
        get() = trackedSearchCountOrNull!!

    @Transient
    public val userCount: Long
        get() = userCountOrNull!!

    @Transient
    public val clickThroughRate: Float
        get() = clickThroughRateOrNull!!

    @Transient
    public val customSearchParameters: Query
        get() = customSearchParametersOrNull!!
}