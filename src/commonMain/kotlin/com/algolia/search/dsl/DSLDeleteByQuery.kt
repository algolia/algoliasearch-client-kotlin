package com.algolia.search.dsl

import com.algolia.search.dsl.filtering.DSLFacetFilters
import com.algolia.search.dsl.filtering.DSLFilters
import com.algolia.search.dsl.filtering.DSLNumericFilters
import com.algolia.search.dsl.filtering.DSLTagFilters
import com.algolia.search.dsl.geosearch.DSLBoundingBox
import com.algolia.search.dsl.geosearch.DSLPolygon
import com.algolia.search.model.indexing.DeleteByQuery


public fun deleteByQuery(init: DeleteByQuery.() -> Unit): DeleteByQuery {
    return DeleteByQuery().apply(init)
}

public fun DeleteByQuery.filters(block: DSLFilters.() -> Unit) {
    filters = DSLFilters().apply(block).build()
}

public fun DeleteByQuery.facetFilters(block: DSLFacetFilters.() -> Unit) {
    facetFilters = DSLFacetFilters().apply(block).build()
}

public fun DeleteByQuery.numericFilters(block: DSLNumericFilters.() -> Unit) {
    numericFilters = DSLNumericFilters().apply(block).build()
}

public fun DeleteByQuery.tagFilters(block: DSLTagFilters.() -> Unit) {
    tagFilters = DSLTagFilters().apply(block).build()
}

public fun DeleteByQuery.insideBoundingBox(block: DSLBoundingBox.() -> Unit) {
    insideBoundingBox = DSLBoundingBox().apply(block).build()
}

public fun DeleteByQuery.insidePolygon(block: DSLPolygon.() -> Unit) {
    insidePolygon = DSLPolygon().apply(block).build()
}