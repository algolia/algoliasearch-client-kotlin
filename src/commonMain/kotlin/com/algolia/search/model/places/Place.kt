package com.algolia.search.model.places

import com.algolia.search.model.ObjectID
import com.algolia.search.model.search.Point
import com.algolia.search.model.search.RankingInfo
import kotlinx.serialization.json.JsonObject

public interface Place {

    public val objectIDOrNull: ObjectID?
    public val administrativeOrNull: List<String>?
    public val countryCodeOrNull: Country?
    public val postCodeOrNull: List<String>?
    public val populationOrNull: Long?
    public val geolocationOrNull: List<Point>?
    public val highlightResultOrNull: JsonObject?
    public val tagsOrNull: List<String>?
    public val importanceOrNull: Int?
    public val adminLevelOrNull: Int?
    public val districtOrNull: String?
    public val suburbOrNull: List<String>?
    public val villageOrNull: List<String>?
    public val isCountryOrNull: Boolean?
    public val isCityOrNull: Boolean?
    public val isSuburbOrNull: Boolean?
    public val isHighwayOrNull: Boolean?
    public val isPopularOrNull: Boolean?
    public val rankingInfoOrNull: RankingInfo?

    public val objectID: ObjectID get() = objectIDOrNull!!
    public val administrative: List<String> get() = administrativeOrNull!!
    public val countryCode: Country get() = countryCodeOrNull!!
    public val postCode: List<String> get() = postCodeOrNull!!
    public val population: Long get() = populationOrNull!!
    public val geolocation: List<Point> get() = geolocationOrNull!!
    public val highlightResult: JsonObject get() = highlightResultOrNull!!
    public val tags: List<String> get() = tagsOrNull!!
    public val importance: Int get() = importanceOrNull!!
    public val adminLevel: Int get() = adminLevelOrNull!!
    public val district: String get() = districtOrNull!!
    public val suburb: List<String> get() = suburbOrNull!!
    public val village: List<String> get() = villageOrNull!!
    public val isCountry: Boolean get() = isCountryOrNull!!
    public val isCity: Boolean get() = isCityOrNull!!
    public val isSuburb: Boolean get() = isSuburbOrNull!!
    public val isHighway: Boolean get() = isHighwayOrNull!!
    public val isPopular: Boolean get() = isPopularOrNull!!
    public val rankingInfo: RankingInfo get() = rankingInfoOrNull!!
}
