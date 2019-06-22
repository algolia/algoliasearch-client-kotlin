package com.algolia.search.model.places

import com.algolia.search.model.ObjectID
import com.algolia.search.model.search.Point
import com.algolia.search.model.search.RankingInfo
import kotlinx.serialization.json.JsonObject


public interface Place {

    val objectIDOrNull: ObjectID?
    val administrativeOrNull: List<String>?
    val countryCodeOrNull: Country?
    val postCodeOrNull: List<String>?
    val populationOrNull: Long?
    val geolocationOrNull: List<Point>?
    val highlightResultOrNull: JsonObject?
    val tagsOrNull: List<String>?
    val importanceOrNull: Int?
    val adminLevelOrNull: Int?
    val districtOrNull: String?
    val suburbOrNull: List<String>?
    val villageOrNull: List<String>?
    val isCountryOrNull: Boolean?
    val isCityOrNull: Boolean?
    val isSuburbOrNull: Boolean?
    val isHighwayOrNull: Boolean?
    val isPopularOrNull: Boolean?
    val rankingInfoOrNull: RankingInfo?

    val objectID: ObjectID get() = objectIDOrNull!!
    val administrative: List<String> get() = administrativeOrNull!!
    val countryCode: Country get() = countryCodeOrNull!!
    val postCode: List<String> get() = postCodeOrNull!!
    val population: Long get() = populationOrNull!!
    val geolocation: List<Point> get() = geolocationOrNull!!
    val highlightResult: JsonObject get() = highlightResultOrNull!!
    val tags: List<String> get() = tagsOrNull!!
    val importance: Int get() = importanceOrNull!!
    val adminLevel: Int get() = adminLevelOrNull!!
    val district: String get() = districtOrNull!!
    val suburb: List<String> get() = suburbOrNull!!
    val village: List<String> get() = villageOrNull!!
    val isCountry: Boolean get() = isCountryOrNull!!
    val isCity: Boolean get() = isCityOrNull!!
    val isSuburb: Boolean get() = isSuburbOrNull!!
    val isHighway: Boolean get() = isHighwayOrNull!!
    val isPopular: Boolean get() = isPopularOrNull!!
    val rankingInfo: RankingInfo get() = rankingInfoOrNull!!
}