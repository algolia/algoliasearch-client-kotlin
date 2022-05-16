package com.algolia.search.model.indexing

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.search.AroundPrecision
import com.algolia.search.model.search.AroundRadius
import com.algolia.search.model.search.BoundingBox
import com.algolia.search.model.search.Point
import com.algolia.search.model.search.Polygon
import com.algolia.search.serialize.KSerializerPoint
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@DSLParameters
public data class DeleteByQuery(
    /**
     * Filter the query with numeric, facet and/or tag filters.
     * Engine default: "" (no filters)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
     */
    @SerialName(Key.Filters) var filters: String? = null,

    /**
     * Filter hits by facet value.
     * Engine default: `[]`
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facetFilters/]
     */
    @SerialName(Key.FacetFilters) var facetFilters: List<List<String>>? = null,

    /**
     * Filter on numeric attributes.
     * Engine default: `[]`
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/numericFilters/]
     */
    @SerialName(Key.NumericFilters) var numericFilters: List<List<String>>? = null,

    /**
     * Filter hits by tags.
     * Engine default: `[]`
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/tagFilters/]
     */
    @SerialName(Key.TagFilters) var tagFilters: List<List<String>>? = null,

    /**
     * Search for entries around a central geolocation, enabling a geo search within a circular area.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundLatLng/]
     */
    @SerialName(Key.AroundLatLng) @Serializable(KSerializerPoint::class) var aroundLatLng: Point? = null,

    /**
     * Define the maximum radius for a geo search (in meters).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundRadius/]
     */
    @SerialName(Key.AroundRadius) var aroundRadius: AroundRadius? = null,

    /**
     * Precision of geo search (in meters), to add grouping by geo location to the ranking formula.
     * Engine default: 1
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundPrecision/]
     */
    @SerialName(Key.AroundPrecision) var aroundPrecision: AroundPrecision? = null,

    /**
     * Search inside a rectangular area (in geo coordinates).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/insideBoundingBox/]
     */
    @SerialName(Key.InsideBoundingBox) var insideBoundingBox: List<BoundingBox>? = null,

    /**
     * Search inside a polygon (in geo coordinates).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/insidePolygon/]
     */
    @SerialName(Key.InsidePolygon) var insidePolygon: List<Polygon>? = null
)
