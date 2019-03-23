package com.algolia.search.model.indexing

import com.algolia.search.model.search.AroundRadius
import com.algolia.search.model.search.BoundingBox
import com.algolia.search.model.search.Point
import com.algolia.search.model.search.Polygon
import com.algolia.search.serialize.*
import com.algolia.search.serialize.KeyFacetFilters
import com.algolia.search.serialize.KeyFilters
import com.algolia.search.serialize.KeyNumericFilters
import com.algolia.search.serialize.KeyQuery
import com.algolia.search.serialize.KeyTagFilters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DeleteByQuery(
    /**
     * Filter the query with numeric, facet and/or tag filters.
     * Engine default: "" (no filters)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
     */
    @SerialName(KeyFilters) var filters: String? = null,

    /**
     * Filter hits by facet value.
     * Engine default: `[]`
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facetFilters/]
     */
    @SerialName(KeyFacetFilters) var facetFilters: List<List<String>>? = null,

    /**
     * Filter on numeric attributes.
     * Engine default: `[]`
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/numericFilters/]
     */
    @SerialName(KeyNumericFilters) var numericFilters: List<List<String>>? = null,

    /**
     * Filter hits by tags.
     * Engine default: `[]`
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/tagFilters/]
     */
    @SerialName(KeyTagFilters) var tagFilters: List<List<String>>? = null,

    /**
     * Search for entries around a central geolocation, enabling a geo search within a circular area.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundLatLng/]
     */
    @SerialName(KeyAroundLatLng) @Serializable(KSerializerPoint::class) var aroundLatLng: Point? = null,

    /**
     * Define the maximum radius for a geo search (in meters).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundRadius/]
     */
    @SerialName(KeyAroundRadius) var aroundRadius: AroundRadius? = null,

    /**
     * Precision of geo search (in meters), to add grouping by geo location to the ranking formula.
     * Engine default: 1
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundPrecision/]
     */
    @SerialName(KeyAroundPrecision) var aroundPrecision: Int? = null,

    /**
     * Search inside a rectangular area (in geo coordinates).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/insideBoundingBox/]
     */
    @SerialName(KeyInsideBoundingBox) var insideBoundingBox: List<BoundingBox>? = null,

    /**
     * Search inside a polygon (in geo coordinates).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/insidePolygon/]
     */
    @SerialName(KeyInsidePolygon) var insidePolygon: List<Polygon>? = null
)