package com.algolia.search.dsl

import com.algolia.search.dsl.attributes.DSLAttributes
import com.algolia.search.dsl.attributes.DSLAttributesToRetrieve
import com.algolia.search.dsl.filtering.DSLFacetFilters
import com.algolia.search.dsl.filtering.DSLFilters
import com.algolia.search.dsl.filtering.DSLNumericFilters
import com.algolia.search.dsl.filtering.DSLTagFilters
import com.algolia.search.dsl.geosearch.DSLBoundingBoxes
import com.algolia.search.dsl.geosearch.DSLPolygons
import com.algolia.search.dsl.highlighting.DSLSnippet
import com.algolia.search.dsl.languages.DSLQueryLanguages
import com.algolia.search.model.search.Query


public fun query(query: String? = null, init: Query.() -> Unit) = Query(query = query).apply(init)

public fun Query.attributesToRetrieve(block: DSLAttributesToRetrieve.() -> Unit) {
    attributesToRetrieve = DSLAttributesToRetrieve().apply(block).build()
}

public fun Query.restrictSearchableAttributes(block: DSLAttributes.() -> Unit) {
    restrictSearchableAttributes = DSLAttributes().apply(block).build()
}

public fun Query.filters(block: DSLFilters.() -> Unit) {
    filters = DSLFilters().apply(block).build()
}

public fun Query.optionalFilters(block: DSLFacetFilters.() -> Unit) {
    optionalFilters = DSLFacetFilters().apply(block).build()
}

public fun Query.facetFilters(block: DSLFacetFilters.() -> Unit) {
    facetFilters = DSLFacetFilters().apply(block).build()
}

public fun Query.numericFilters(block: DSLNumericFilters.() -> Unit) {
    numericFilters = DSLNumericFilters().apply(block).build()
}

public fun Query.tagFilters(block: DSLTagFilters.() -> Unit) {
    tagFilters = DSLTagFilters().apply(block).build()
}

public fun Query.facets(block: DSLAttributes.() -> Unit) {
    facets = DSLAttributes().apply(block).build()
}

public fun Query.attributesToHighlight(block: DSLAttributes.() -> Unit) {
    attributesToHighlight = DSLAttributes().apply(block).build()
}

public fun Query.attributesToSnippet(block: DSLSnippet.() -> Unit) {
    attributesToSnippet = DSLSnippet().apply(block).build()
}

public fun Query.disableTypoToleranceOnAttributes(block: DSLAttributes.() -> Unit) {
    disableTypoToleranceOnAttributes = DSLAttributes().apply(block).build()
}

public fun Query.insideBoundingBox(block: DSLBoundingBoxes.() -> Unit) {
    insideBoundingBox = DSLBoundingBoxes().apply(block).build()
}

public fun Query.insidePolygon(block: DSLPolygons.() -> Unit) {
    insidePolygon = DSLPolygons().apply(block).build()
}

public fun Query.queryLanguages(block: DSLQueryLanguages.() -> Unit) {
    queryLanguages = DSLQueryLanguages().apply(block).build()
}