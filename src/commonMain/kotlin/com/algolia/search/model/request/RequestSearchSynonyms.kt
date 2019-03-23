package com.algolia.search.model.request

import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyPage
import com.algolia.search.serialize.KeyQuery
import com.algolia.search.serialize.KeyType
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class RequestSearchSynonyms(
    @SerialName(KeyQuery) val query: String? = null,
    @SerialName(KeyPage) val page: Int? = null,
    @SerialName(KeyHitsPerPage) val hitsPerPage: Int? = null,
    @SerialName(KeyType) val type: String? = null
)