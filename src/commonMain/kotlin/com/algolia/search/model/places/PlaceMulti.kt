package com.algolia.search.model.places

import com.algolia.search.model.ObjectID
import com.algolia.search.model.search.Point
import com.algolia.search.model.search.QueryLanguage
import com.algolia.search.model.search.RankingInfo
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.JsonObject


@Serializable
data class PlaceMulti(
    @SerialName(KeyCity) val cityOrNull: Map<QueryLanguage, List<String>>? = null,
    @SerialName(KeyCounty) val countyOrNull: Map<QueryLanguage, List<String>>? = null,
    @SerialName(KeyCountry) val countryOrNull: Map<QueryLanguage, String>? = null,
    @SerialName(KeyLocaleNames) val localNamesOrNull: Map<QueryLanguage, List<String>>? = null,
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
    val localNames: Map<QueryLanguage, List<String>>
        get() = localNamesOrNull!!

    @Transient
    val city: Map<QueryLanguage, List<String>>
        get () = cityOrNull!!

    @Transient
    val county: Map<QueryLanguage, List<String>>
        get() = countyOrNull!!

    @Transient
    val country: Map<QueryLanguage, String>
        get() = countryOrNull!!
}