package com.algolia.search.dsl.geosearch

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.search.BoundingBox

/**
 * DSL for building a [List] of [BoundingBox].
 */
@DSLParameters
public class DSLBoundingBox(
    private val boundingBoxes: MutableList<BoundingBox> = mutableListOf()
) {

    /**
     * Add [this] to [boundingBoxes].
     */
    public operator fun BoundingBox.unaryPlus() {
        boundingBoxes += this
    }

    public companion object : DSL<DSLBoundingBox, List<BoundingBox>> {

        override operator fun invoke(block: DSLBoundingBox.() -> Unit): List<BoundingBox> {
            return DSLBoundingBox().apply(block).boundingBoxes
        }
    }
}
