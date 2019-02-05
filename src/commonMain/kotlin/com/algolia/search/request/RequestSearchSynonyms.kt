package com.algolia.search.request

import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyPage
import com.algolia.search.serialize.KeyQuery
import com.algolia.search.serialize.KeyType
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RequestSearchSynonyms(
    @Optional @SerialName(KeyQuery) val query: String? = null,
    @Optional @SerialName(KeyPage) val page: Int? = null,
    @Optional @SerialName(KeyHitsPerPage) val hitsPerPage: Int? = null,
    @Optional @SerialName(KeyType) val type: String? = null
)