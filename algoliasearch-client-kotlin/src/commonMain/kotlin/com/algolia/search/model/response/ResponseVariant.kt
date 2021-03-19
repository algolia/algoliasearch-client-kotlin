package com.algolia.search.model.response

import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.KeyAverageClickPosition
import com.algolia.search.serialize.KeyClickCount
import com.algolia.search.serialize.KeyClickThroughRate
import com.algolia.search.serialize.KeyConversionCount
import com.algolia.search.serialize.KeyConversionRate
import com.algolia.search.serialize.KeyCustomSearchParameters
import com.algolia.search.serialize.KeyDescription
import com.algolia.search.serialize.KeyIndex
import com.algolia.search.serialize.KeyNoResultCount
import com.algolia.search.serialize.KeySearchCount
import com.algolia.search.serialize.KeyTrackedSearchCount
import com.algolia.search.serialize.KeyTrafficPercentage
import com.algolia.search.serialize.KeyUserCount
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseVariant(
    /**
     * Distinct click count for the variant.
     */
    @SerialName(KeyClickCount) val clickCount: Int,
    /**
     * Distinct conversion count for the variant.
     */
    @SerialName(KeyConversionCount) val conversionCount: Int,
    /**
     *
     */
    @SerialName(KeyDescription) val description: String,
    @SerialName(KeyIndex) val indexName: IndexName,
    @SerialName(KeyTrafficPercentage) val trafficPercentage: Int,
    /**
     * Conversion rate for the variant.
     */
    @SerialName(KeyConversionRate) val conversionRateOrNull: Float? = null,
    @SerialName(KeyNoResultCount) val noResultCountOrNull: Int? = null,
    /**
     * Average click position for the variant.
     */
    @SerialName(KeyAverageClickPosition) val averageClickPositionOrNull: Float? = null,
    @SerialName(KeySearchCount) val searchCountOrNull: Long? = null,
    @SerialName(KeyTrackedSearchCount) val trackedSearchCountOrNull: Long? = null,
    @SerialName(KeyUserCount) val userCountOrNull: Long? = null,
    /**
     * Click through rate for the variant.
     */
    @SerialName(KeyClickThroughRate) val clickThroughRateOrNull: Float? = null,
    @SerialName(KeyCustomSearchParameters) val customSearchParametersOrNull: Query? = null
) {

    public val conversionRate: Float
        get() = conversionRateOrNull!!

    public val noResultCount: Int
        get() = noResultCountOrNull!!

    public val averageClickPosition: Float
        get() = averageClickPositionOrNull!!

    public val searchCount: Long
        get() = searchCountOrNull!!

    public val trackedSearchCount: Long
        get() = trackedSearchCountOrNull!!

    public val userCount: Long
        get() = userCountOrNull!!

    public val clickThroughRate: Float
        get() = clickThroughRateOrNull!!

    public val customSearchParameters: Query
        get() = customSearchParametersOrNull!!
}
