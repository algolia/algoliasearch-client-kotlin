package com.algolia.search.dsl.geosearch

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.search.BoundingBox


@DSLParameters
public class DSLBoundingBoxes(
    private val boundingBoxes: MutableList<BoundingBox> = mutableListOf()
) {

    public operator fun BoundingBox.unaryPlus() {
        boundingBoxes += this
    }

    public fun build(): List<BoundingBox> {
        return boundingBoxes.toList()
    }
}