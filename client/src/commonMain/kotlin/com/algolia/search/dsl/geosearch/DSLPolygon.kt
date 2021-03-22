package com.algolia.search.dsl.geosearch

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.search.Polygon

/**
 * DSL for building a [List] of [Polygon].
 */
@DSLParameters
public class DSLPolygon(
    private val polygons: MutableList<Polygon> = mutableListOf()
) {

    /**
     * Add [this] to [polygons].
     */
    public operator fun Polygon.unaryPlus() {
        polygons += this
    }

    public companion object : DSL<DSLPolygon, List<Polygon>> {

        override operator fun invoke(block: DSLPolygon.() -> Unit): List<Polygon> {
            return DSLPolygon().apply(block).polygons
        }
    }
}
