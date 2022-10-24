package com.algolia.search.endpoint

import com.algolia.search.model.ObjectID
import com.algolia.search.model.places.PlaceLanguages
import com.algolia.search.model.places.PlacesQuery
import com.algolia.search.model.response.ResponseSearchPlacesMono
import com.algolia.search.model.response.ResponseSearchPlacesMulti
import com.algolia.search.model.search.Language
import com.algolia.search.model.search.Point
import com.algolia.search.transport.RequestOptions

@Deprecated("Algolia Places has reached end of life.")
public interface EndpointPlaces {

    public suspend fun searchPlaces(
        query: PlacesQuery = PlacesQuery(),
        requestOptions: RequestOptions? = null
    ): ResponseSearchPlacesMulti

    public suspend fun searchPlaces(
        language: Language,
        query: PlacesQuery = PlacesQuery(),
        requestOptions: RequestOptions? = null
    ): ResponseSearchPlacesMono

    public suspend fun getByObjectID(
        objectID: ObjectID,
        requestOptions: RequestOptions? = null
    ): PlaceLanguages

    public suspend fun reverseGeocoding(
        geolocation: Point,
        hitsPerPage: Int? = null,
        requestOptions: RequestOptions? = null
    ): ResponseSearchPlacesMulti

    public suspend fun reverseGeocoding(
        language: Language,
        geolocation: Point,
        hitsPerPage: Int? = null,
        requestOptions: RequestOptions? = null
    ): ResponseSearchPlacesMono
}
