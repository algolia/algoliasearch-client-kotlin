package com.algolia.search.dsl

import com.algolia.search.dsl.advanced.DSLResponseFields
import com.algolia.search.dsl.attributes.DSLAttributes
import com.algolia.search.dsl.attributes.DSLAttributesToRetrieve
import com.algolia.search.dsl.filtering.*
import com.algolia.search.dsl.geosearch.DSLBoundingBox
import com.algolia.search.dsl.geosearch.DSLPolygon
import com.algolia.search.dsl.highlighting.DSLSnippet
import com.algolia.search.dsl.languages.DSLQueryLanguage
import com.algolia.search.dsl.strategy.DSLAlternativesAsExact
import com.algolia.search.model.search.Query


public fun query(query: String? = null, init: Query.() -> Unit): Query {
    return Query(query = query).apply(init)
}

public fun Query.attributesToRetrieve(block: DSLAttributesToRetrieve.() -> Unit) {
    attributesToRetrieve = DSLAttributesToRetrieve(block)
}

public fun Query.restrictSearchableAttributes(block: DSLAttributes.() -> Unit) {
    restrictSearchableAttributes = DSLAttributes(block)
}

public fun Query.filters(block: DSLFilters.() -> Unit) {
    filters = FilterBuilder.SQL(DSLFilters(block))
}

public fun Query.optionalFilters(block: DSLFacetFilters.() -> Unit) {
    optionalFilters = FilterBuilder.Legacy(DSLFacetFilters(block))
}

public fun Query.facetFilters(block: DSLFacetFilters.() -> Unit) {
    facetFilters = FilterBuilder.Legacy(DSLFacetFilters(block))
}

public fun Query.numericFilters(block: DSLNumericFilters.() -> Unit) {
    numericFilters = FilterBuilder.Legacy(DSLNumericFilters(block))
}

public fun Query.tagFilters(block: DSLTagFilters.() -> Unit) {
    tagFilters = FilterBuilder.Legacy(DSLTagFilters(block))
}

public fun Query.facets(block: DSLAttributes.() -> Unit) {
    facets = DSLAttributes(block)
}

public fun Query.attributesToHighlight(block: DSLAttributes.() -> Unit) {
    attributesToHighlight = DSLAttributes(block)
}

public fun Query.attributesToSnippet(block: DSLSnippet.() -> Unit) {
    attributesToSnippet = DSLSnippet(block)
}

public fun Query.disableTypoToleranceOnAttributes(block: DSLAttributes.() -> Unit) {
    disableTypoToleranceOnAttributes = DSLAttributes(block)
}

public fun Query.insideBoundingBox(block: DSLBoundingBox.() -> Unit) {
    insideBoundingBox = DSLBoundingBox(block)
}

public fun Query.insidePolygon(block: DSLPolygon.() -> Unit) {
    insidePolygon = DSLPolygon(block)
}

public fun Query.queryLanguages(block: DSLQueryLanguage.() -> Unit) {
    queryLanguages = DSLQueryLanguage(block)
}

public fun Query.optionalWords(block: DSLStrings.() -> Unit) {
    optionalWords = DSLStrings(block)
}

public fun Query.disableExactOnAttributes(block: DSLAttributes.() -> Unit) {
    disableExactOnAttributes = DSLAttributes(block)
}

public fun Query.alternativesAsExact(block: DSLAlternativesAsExact.() -> Unit) {
    alternativesAsExact = DSLAlternativesAsExact(block)
}

public fun Query.ruleContexts(block: DSLStrings.() -> Unit) {
    ruleContexts = DSLStrings(block)
}

public fun Query.analyticsTags(block: DSLStrings.() -> Unit) {
    analyticsTags = DSLStrings(block)
}

public fun Query.responseFields(block: DSLResponseFields.() -> Unit) {
    responseFields = DSLResponseFields(block)
}