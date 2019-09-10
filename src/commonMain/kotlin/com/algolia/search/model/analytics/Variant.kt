package com.algolia.search.model.analytics

import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.KeyCustomSearchParameters
import com.algolia.search.serialize.KeyDescription
import com.algolia.search.serialize.KeyIndex
import com.algolia.search.serialize.KeyTrafficPercentage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmOverloads


/**
 * Variant of an [ABTest]
 */
@Serializable
public data class Variant @JvmOverloads constructor(
    /**
     * Index name.
     */
    @SerialName(KeyIndex) val indexName: IndexName,
    /**
     * Percentage of the traffic that should be going to the variant.
     * The sum of the percentage between [ABTest.variantA] and [ABTest.variantB] should be equal to 100.
     */
    @SerialName(KeyTrafficPercentage) val trafficPercentage: Int,
    /**
     * Applies search parameters on a variant. This can only be used if the two variants are using the same index.
     */
    @SerialName(KeyCustomSearchParameters) val customSearchParameters: Query? = null,
    /**
     * Description of the variant. This is useful when seeing the results in the dashboard or via the API.
     */
    @SerialName(KeyDescription) val description: String = ""
)