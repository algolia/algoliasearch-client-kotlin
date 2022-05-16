package com.algolia.search.model.places

import com.algolia.search.model.ObjectID
import com.algolia.search.model.search.Language
import com.algolia.search.model.search.Point
import com.algolia.search.model.search.RankingInfo
import com.algolia.search.serialize.KSerializerGeoPoints
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
public data class PlaceLanguages(
    @SerialName(Key.Country) val countryOrNull: Map<Language, String>? = null,
    @SerialName(Key.County) val countyOrNull: Map<Language, List<String>>? = null,
    @SerialName(Key.City) val cityOrNull: Map<Language, List<String>>? = null,
    @SerialName(Key.LocaleNames) val localNamesOrNull: Map<Language, List<String>>? = null,
    @SerialName(Key.ObjectID) override val objectIDOrNull: ObjectID? = null,
    @SerialName(Key.Administrative) override val administrativeOrNull: List<String>? = null,
    @SerialName(Key.CountryCode) override val countryCodeOrNull: Country? = null,
    @SerialName(Key.PostCode) override val postCodeOrNull: List<String>? = null,
    @SerialName(Key.Population) override val populationOrNull: Long? = null,
    @SerialName(Key._Geoloc) @Serializable(KSerializerGeoPoints::class) override val geolocationOrNull: List<Point>? = null,
    @SerialName(Key._HighlightResult) override val highlightResultOrNull: JsonObject? = null,
    @SerialName(Key.Importance) override val importanceOrNull: Int? = null,
    @SerialName(Key._Tags) override val tagsOrNull: List<String>? = null,
    @SerialName(Key.Admin_Level) override val adminLevelOrNull: Int? = null,
    @SerialName(Key.District) override val districtOrNull: String? = null,
    @SerialName(Key.Suburb) override val suburbOrNull: List<String>? = null,
    @SerialName(Key.Village) override val villageOrNull: List<String>? = null,
    @SerialName(Key.Is_Country) override val isCountryOrNull: Boolean? = null,
    @SerialName(Key.Is_City) override val isCityOrNull: Boolean? = null,
    @SerialName(Key.Is_Suburb) override val isSuburbOrNull: Boolean? = null,
    @SerialName(Key.Is_Highway) override val isHighwayOrNull: Boolean? = null,
    @SerialName(Key.Is_Popular) override val isPopularOrNull: Boolean? = null,
    @SerialName(Key._RankingInfo) override val rankingInfoOrNull: RankingInfo? = null
) : Place {

    val country: Map<Language, String>
        get() = countryOrNull!!

    val county: Map<Language, List<String>>
        get() = countyOrNull!!

    val city: Map<Language, List<String>>
        get() = cityOrNull!!

    val localNames: Map<Language, List<String>>
        get() = localNamesOrNull!!
}
