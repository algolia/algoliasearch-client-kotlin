package com.algolia.search.model.response

import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseVariant(
    @SerialName(Key.Index) val indexName: IndexName,
    @SerialName(Key.TrafficPercentage) val trafficPercentage: Int,
    /**
     * Distinct click count for the variant.
     */
    @SerialName(Key.ClickCount) val clickCountOrNull: Int? = null,
    /**
     * Distinct conversion count for the variant.
     */
    @SerialName(Key.ConversionCount) val conversionCountOrNull: Int? = null,
    @SerialName(Key.Description) val descriptionOrNull: String? = null,
    /**
     * Conversion rate for the variant.
     */
    @SerialName(Key.ConversionRate) val conversionRateOrNull: Float? = null,
    @SerialName(Key.NoResultCount) val noResultCountOrNull: Int? = null,
    /**
     * Average click position for the variant.
     */
    @SerialName(Key.AverageClickPosition) val averageClickPositionOrNull: Float? = null,
    @SerialName(Key.SearchCount) val searchCountOrNull: Long? = null,
    @SerialName(Key.TrackedSearchCount) val trackedSearchCountOrNull: Long? = null,
    @SerialName(Key.UserCount) val userCountOrNull: Long? = null,
    /**
     * Click through rate for the variant.
     */
    @SerialName(Key.ClickThroughRate) val clickThroughRateOrNull: Float? = null,
    @SerialName(Key.CustomSearchParameters) val customSearchParametersOrNull: Query? = null
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

    public val clickCount: Int
        get() = requireNotNull(clickCountOrNull)

    public val conversionCount: Int
        get() = requireNotNull(conversionCountOrNull)

    public val description: String
        get() = requireNotNull(descriptionOrNull)
}
