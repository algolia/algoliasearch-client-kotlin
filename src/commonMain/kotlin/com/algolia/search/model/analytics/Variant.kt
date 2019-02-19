package com.algolia.search.model.analytics

import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.KeyCustomSearchParameters
import com.algolia.search.serialize.KeyDescription
import com.algolia.search.serialize.KeyIndex
import com.algolia.search.serialize.KeyTrafficPercentage
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Variant(
    @SerialName(KeyIndex) val index: IndexName,
    @SerialName(KeyTrafficPercentage) val trafficPercentage: Int,
    @SerialName(KeyDescription) val description: String = "",
    @Optional @SerialName(KeyCustomSearchParameters) val customSearchParameters: Query? = null
)