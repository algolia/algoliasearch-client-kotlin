package com.algolia.search.model.analytics

import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.KeyCustomSearchParameters
import com.algolia.search.serialize.KeyDescription
import com.algolia.search.serialize.KeyIndex
import com.algolia.search.serialize.KeyTrafficPercentage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class Variant(
    @SerialName(KeyIndex) val indexName: IndexName,
    @SerialName(KeyTrafficPercentage) val trafficPercentage: Int,
    @SerialName(KeyDescription) val description: String = "",
    @SerialName(KeyCustomSearchParameters) val customSearchParameters: Query? = null
)