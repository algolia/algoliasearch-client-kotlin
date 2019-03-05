package com.algolia.search.model.response

import com.algolia.search.model.synonym.Synonym
import com.algolia.search.serialize.KeyHits
import com.algolia.search.serialize.KeyNbHits
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class ResponseSearchSynonyms(
    @SerialName(KeyHits) val hits: List<Synonym>,
    @SerialName(KeyNbHits) val nbHits: Int
)