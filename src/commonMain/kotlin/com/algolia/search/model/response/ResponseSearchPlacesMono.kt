package com.algolia.search.model.response

import com.algolia.search.model.places.PlaceLanguage
import com.algolia.search.serialize.KeyDegradedQuery
import com.algolia.search.serialize.KeyHits
import com.algolia.search.serialize.KeyNbHits
import com.algolia.search.serialize.KeyParams
import com.algolia.search.serialize.KeyParsedQuery
import com.algolia.search.serialize.KeyProcessingTimeMS
import com.algolia.search.serialize.KeyQuery
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSearchPlacesMono(
    @SerialName(KeyHits) override val hits: List<PlaceLanguage>,
    @SerialName(KeyNbHits) override val nbHits: Int,
    @SerialName(KeyProcessingTimeMS) override val processingTimeMS: Long,
    @SerialName(KeyParams) override val params: String,
    @SerialName(KeyQuery) override val queryOrNull: String? = null,
    @SerialName(KeyDegradedQuery) override val degradedQueryOrNull: String? = null,
    @SerialName(KeyParsedQuery) override val parsedQueryOrNull: String? = null
) : ResponseSearchPlaces<PlaceLanguage>
