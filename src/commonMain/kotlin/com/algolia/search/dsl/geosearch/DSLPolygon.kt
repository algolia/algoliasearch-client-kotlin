package com.algolia.search.dsl.geosearch

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.search.Polygon


@DSLParameters
public class DSLPolygon(
    private val polygons: MutableList<Polygon> = mutableListOf()
) {

    public operator fun Polygon.unaryPlus() {
        polygons += this
    }

    public fun build(): List<Polygon> {
        return polygons.toList()
    }
}