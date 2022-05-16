package com.algolia.search.model.analytics

import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Variant of an [ABTest]
 */
@Serializable
public data class Variant(
    /**
     * Index name.
     */
    @SerialName(Key.Index) val indexName: IndexName,
    /**
     * Percentage of the traffic that should be going to the variant.
     * The sum of the percentage between [ABTest.variantA] and [ABTest.variantB] should be equal to 100.
     */
    @SerialName(Key.TrafficPercentage) val trafficPercentage: Int,
    /**
     * Applies search parameters on a variant. This can only be used if the two variants are using the same index.
     */
    @SerialName(Key.CustomSearchParameters) val customSearchParameters: Query? = null,
    /**
     * Description of the variant. This is useful when seeing the results in the dashboard or via the API.
     */
    @SerialName(Key.Description) val description: String = ""
)
