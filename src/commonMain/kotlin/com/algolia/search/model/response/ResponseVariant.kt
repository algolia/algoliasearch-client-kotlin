package com.algolia.search.model.response

import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseVariant(
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