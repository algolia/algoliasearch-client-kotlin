package com.algolia.search.model.response

import com.algolia.search.model.places.PlaceLanguage
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseSearchPlacesMono(
    @SerialName(Key.Hits) override val hits: List<PlaceLanguage>,
    @SerialName(Key.NbHits) override val nbHits: Int,
    @SerialName(Key.ProcessingTimeMS) override val processingTimeMS: Long,
    @SerialName(Key.Params) override val params: String,
    @SerialName(Key.Query) override val queryOrNull: String? = null,
    @SerialName(Key.DegradedQuery) override val degradedQueryOrNull: String? = null,
    @SerialName(Key.ParsedQuery) override val parsedQueryOrNull: String? = null
) : ResponseSearchPlaces<PlaceLanguage>
