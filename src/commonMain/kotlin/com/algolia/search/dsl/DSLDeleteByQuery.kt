package com.algolia.search.dsl

import com.algolia.search.dsl.filtering.DSLFacetFilters
import com.algolia.search.dsl.filtering.DSLFilters
import com.algolia.search.dsl.filtering.DSLNumericFilters
import com.algolia.search.dsl.filtering.DSLTagFilters
import com.algolia.search.dsl.geosearch.DSLBoundingBox
import com.algolia.search.dsl.geosearch.DSLPolygon
import com.algolia.search.model.filter.FilterGroupsConverter
import com.algolia.search.model.indexing.DeleteByQuery

/**
 * Create a [DeleteByQuery] with [block].
 */
public fun deleteByQuery(block: DeleteByQuery.() -> Unit): DeleteByQuery {
    return DeleteByQuery().apply(block)
}

/**
 * Use [FilterGroupsConverter.SQL] on the [block] output and assign it to [DeleteByQuery.filters].
 */
public fun DeleteByQuery.filters(block: DSLFilters.() -> Unit) {
    filters = FilterGroupsConverter.SQL(DSLFilters(block))
}

/**
 * Use [FilterGroupsConverter.Legacy] on the [block] output and assign it to [DeleteByQuery.facetFilters].
 */
public fun DeleteByQuery.facetFilters(block: DSLFacetFilters.() -> Unit) {
    facetFilters = FilterGroupsConverter.Legacy.Facet(DSLFacetFilters(block))
}

/**
 * Use [FilterGroupsConverter.Legacy] on the [block] output and assign it to [DeleteByQuery.numericFilters].
 */
public fun DeleteByQuery.numericFilters(block: DSLNumericFilters.() -> Unit) {
    numericFilters = FilterGroupsConverter.Legacy.Numeric(DSLNumericFilters(block))
}

/**
 * Use [FilterGroupsConverter.Legacy] on the [block] output and assign it to [DeleteByQuery.tagFilters].
 */
public fun DeleteByQuery.tagFilters(block: DSLTagFilters.() -> Unit) {
    tagFilters = FilterGroupsConverter.Legacy.Tag(DSLTagFilters(block))
}

/**
 * Assign the output of [block] to [DeleteByQuery.insideBoundingBox].
 */
public fun DeleteByQuery.insideBoundingBox(block: DSLBoundingBox.() -> Unit) {
    insideBoundingBox = DSLBoundingBox(block)
}

/**
 * AAssign the output of [block] to [DeleteByQuery.insidePolygon].
 */
public fun DeleteByQuery.insidePolygon(block: DSLPolygon.() -> Unit) {
    insidePolygon = DSLPolygon(block)
}
