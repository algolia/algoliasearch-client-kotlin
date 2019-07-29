package com.algolia.search.model.response

import com.algolia.search.model.places.PlaceLanguages
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class ResponseSearchPlacesMulti(
    @SerialName(KeyHits) override val hits: List<PlaceLanguages>,
    @SerialName(KeyNbHits) override val nbHits: Int,
    @SerialName(KeyProcessingTimeMS) override val processingTimeMS: Long,
    @SerialName(KeyParams) override val params: String,
    @SerialName(KeyQuery) override val queryOrNull: String? = null,
    @SerialName(KeyDegradedQuery) override val degradedQueryOrNull: String? = null,
    @SerialName(KeyParsedQuery) override val parsedQueryOrNull: String? = null
) : ResponseSearchPlaces<PlaceLanguages>