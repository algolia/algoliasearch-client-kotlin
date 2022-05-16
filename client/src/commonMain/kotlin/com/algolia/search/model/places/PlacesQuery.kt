package com.algolia.search.model.places

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.search.AroundRadius
import com.algolia.search.model.search.Language
import com.algolia.search.model.search.Point
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@DSLParameters
public data class PlacesQuery(
    /**
     * The query to match places by name.
     */
    @SerialName(Key.Query) var query: String? = null,
    /**
     * Restrict the search results to a specific type.
     */
    @SerialName(Key.Type) var type: PlaceType? = null,
    /**
     * Engine default: Search on the whole planet.
     * If specified, restrict the search results to a specific list of countries.
     */
    @SerialName(Key.Countries) var countries: List<Country>? = null,
    /**
     * Force to first search around a specific latitude longitude.
     */
    @SerialName(Key.AroundLatLng) var aroundLatLng: Point? = null,
    /**
     * Engine default: true
     * Whether or not to first search around the geolocation of the user found via his IP address.
     */
    @SerialName(Key.AroundLatLngViaIP) var aroundLatLngViaIP: Boolean? = null,
    /**
     * Radius in meters to search around the latitude/longitude.
     * Otherwise a default radius is automatically computed given the area density.
     */
    @SerialName(Key.AroundRadius) var aroundRadius: AroundRadius? = null,
    /**
     * Engine default: false
     * Controls whether the _rankingInfo object should be included in the hits.
     */
    @SerialName(Key.GetRankingInfo) var getRankingInfo: Boolean? = null,
    /**
     * Engine default: 20
     * Specifies how many results you want to retrieve per search.
     */
    @SerialName(Key.HitsPerPage) var hitsPerPage: Int? = null
) {

    @SerialName(Key.Language)
    internal var language: Language? = null
}
