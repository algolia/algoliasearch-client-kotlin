@file:Suppress("FunctionName")

package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.dsl.internal.requestOptionsBuilder
import com.algolia.search.endpoint.EndpointPlaces
import com.algolia.search.model.ObjectID
import com.algolia.search.model.places.PlaceLanguages
import com.algolia.search.model.places.PlacesQuery
import com.algolia.search.model.response.ResponseSearchPlacesMono
import com.algolia.search.model.response.ResponseSearchPlacesMulti
import com.algolia.search.model.search.Language
import com.algolia.search.model.search.Point
import com.algolia.search.serialize.KeyAroundLatLng
import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyLanguage
import com.algolia.search.serialize.RoutePlaces
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.http.HttpMethod

internal class EndpointPlacesImpl(
    private val transport: Transport,
) : EndpointPlaces {

    override suspend fun searchPlaces(
        query: PlacesQuery,
        requestOptions: RequestOptions?,
    ): ResponseSearchPlacesMulti {
        val body = JsonNoDefaults.encodeToString(PlacesQuery.serializer(), query)

        return transport.request(HttpMethod.Post, CallType.Read, "$RoutePlaces/query", requestOptions, body)
    }

    override suspend fun searchPlaces(
        language: Language,
        query: PlacesQuery,
        requestOptions: RequestOptions?,
    ): ResponseSearchPlacesMono {
        val copy = query.copy().apply { this.language = language }
        val body = JsonNoDefaults.encodeToString(PlacesQuery.serializer(), copy)

        return transport.request(HttpMethod.Post, CallType.Read, "$RoutePlaces/query", requestOptions, body)
    }

    override suspend fun getByObjectID(objectID: ObjectID, requestOptions: RequestOptions?): PlaceLanguages {
        return transport.request(HttpMethod.Get, CallType.Read, "$RoutePlaces/$objectID", requestOptions)
    }

    override suspend fun reverseGeocoding(
        geolocation: Point,
        hitsPerPage: Int?,
        requestOptions: RequestOptions?,
    ): ResponseSearchPlacesMulti {
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyAroundLatLng, "${geolocation.latitude},${geolocation.longitude}")
            parameter(KeyHitsPerPage, hitsPerPage)
        }

        return transport.request(HttpMethod.Get, CallType.Read, "$RoutePlaces/reverse", options)
    }

    override suspend fun reverseGeocoding(
        language: Language,
        geolocation: Point,
        hitsPerPage: Int?,
        requestOptions: RequestOptions?,
    ): ResponseSearchPlacesMono {
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyAroundLatLng, "${geolocation.latitude},${geolocation.longitude}")
            parameter(KeyHitsPerPage, hitsPerPage)
            parameter(KeyLanguage, language.raw)
        }

        return transport.request(HttpMethod.Get, CallType.Read, "$RoutePlaces/reverse", options)
    }
}

/**
 * Create an [EndpointPlaces] instance.
 */
internal fun EndpointPlaces(
    transport: Transport,
): EndpointPlaces = EndpointPlacesImpl(transport)
