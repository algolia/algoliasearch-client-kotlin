package com.algolia.search.model.places

import com.algolia.search.model.ObjectID
import com.algolia.search.model.search.Language
import com.algolia.search.model.search.Point
import com.algolia.search.model.search.RankingInfo
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.JsonObject


@Serializable
data class PlaceMulti(
    @SerialName(KeyCountry) val countryOrNull: Map<Language, String>? = null,
    @SerialName(KeyCounty) val countyOrNull: Map<Language, List<String>>? = null,
    @SerialName(KeyCity) val cityOrNull: Map<Language, List<String>>? = null,
    @SerialName(KeyLocaleNames) val localNamesOrNull: Map<Language, List<String>>? = null,
    @SerialName(KeyObjectID) override val objectIDOrNull: ObjectID? = null,
    @SerialName(KeyAdministrative) override val administrativeOrNull: List<String>? = null,
    @SerialName(KeyCountryCode) override val countryCodeOrNull: Country? = null,
    @SerialName(KeyPostCode) override val postCodeOrNull: List<String>? = null,
    @SerialName(KeyPopulation) override val populationOrNull: Long? = null,
    @SerialName(Key_Geoloc) @Serializable(KSerializerGeoPoints::class) override val geolocationOrNull: List<Point>? = null,
    @SerialName(Key_HighlightResult) override val highlightResultOrNull: JsonObject? = null,
    @SerialName(KeyImportance) override val importanceOrNull: Int? = null,
    @SerialName(Key_Tags) override val tagsOrNull: List<String>? = null,
    @SerialName(KeyAdmin_Level) override val adminLevelOrNull: Int? = null,
    @SerialName(KeyDistrict) override val districtOrNull: String? = null,
    @SerialName(KeySuburb) override val suburbOrNull: List<String>? = null,
    @SerialName(KeyVillage) override val villageOrNull: List<String>? = null,
    @SerialName(KeyIs_Country) override val isCountryOrNull: Boolean? = null,
    @SerialName(KeyIs_City) override val isCityOrNull: Boolean? = null,
    @SerialName(KeyIs_Suburb) override val isSuburbOrNull: Boolean? = null,
    @SerialName(KeyIs_Highway) override val isHighwayOrNull: Boolean? = null,
    @SerialName(KeyIs_Popular) override val isPopularOrNull: Boolean? = null,
    @SerialName(Key_RankingInfo) override val rankingInfoOrNull: RankingInfo? = null
) : Place {

    @Transient
    val country: Map<Language, String>
        get() = countryOrNull!!

    @Transient
    val county: Map<Language, List<String>>
        get() = countyOrNull!!

    @Transient
    val city: Map<Language, List<String>>
        get () = cityOrNull!!

    @Transient
    val localNames: Map<Language, List<String>>
        get() = localNamesOrNull!!
}