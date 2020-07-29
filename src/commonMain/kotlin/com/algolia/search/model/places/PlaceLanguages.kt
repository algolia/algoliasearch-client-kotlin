package com.algolia.search.model.places

import com.algolia.search.model.ObjectID
import com.algolia.search.model.search.Language
import com.algolia.search.model.search.Point
import com.algolia.search.model.search.RankingInfo
import com.algolia.search.serialize.KSerializerGeoPoints
import com.algolia.search.serialize.KeyAdmin_Level
import com.algolia.search.serialize.KeyAdministrative
import com.algolia.search.serialize.KeyCity
import com.algolia.search.serialize.KeyCountry
import com.algolia.search.serialize.KeyCountryCode
import com.algolia.search.serialize.KeyCounty
import com.algolia.search.serialize.KeyDistrict
import com.algolia.search.serialize.KeyImportance
import com.algolia.search.serialize.KeyIs_City
import com.algolia.search.serialize.KeyIs_Country
import com.algolia.search.serialize.KeyIs_Highway
import com.algolia.search.serialize.KeyIs_Popular
import com.algolia.search.serialize.KeyIs_Suburb
import com.algolia.search.serialize.KeyLocaleNames
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.serialize.KeyPopulation
import com.algolia.search.serialize.KeyPostCode
import com.algolia.search.serialize.KeySuburb
import com.algolia.search.serialize.KeyVillage
import com.algolia.search.serialize.Key_Geoloc
import com.algolia.search.serialize.Key_HighlightResult
import com.algolia.search.serialize.Key_RankingInfo
import com.algolia.search.serialize.Key_Tags
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
public data class PlaceLanguages(
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

    val country: Map<Language, String>
        get() = countryOrNull!!

    val county: Map<Language, List<String>>
        get() = countyOrNull!!

    val city: Map<Language, List<String>>
        get() = cityOrNull!!

    val localNames: Map<Language, List<String>>
        get() = localNamesOrNull!!
}
