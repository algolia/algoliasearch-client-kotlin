package com.algolia.search.model.response

import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseSearches(
    /**
     * List of result in the order they were submitted, one element for each [IndexQuery].
     */
    @SerialName(Key.Results) val results: List<ResponseSearch>
)
