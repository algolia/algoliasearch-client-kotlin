package com.algolia.search.dsl

import com.algolia.search.dsl.advanced.DSLExplainModules
import com.algolia.search.dsl.advanced.DSLResponseFields
import com.algolia.search.dsl.attributes.DSLAttributes
import com.algolia.search.dsl.attributes.DSLAttributesSet
import com.algolia.search.dsl.attributes.DSLAttributesToRetrieve
import com.algolia.search.dsl.filtering.DSLFacetFilters
import com.algolia.search.dsl.filtering.DSLFilters
import com.algolia.search.dsl.filtering.DSLNumericFilters
import com.algolia.search.dsl.filtering.DSLTagFilters
import com.algolia.search.dsl.geosearch.DSLBoundingBox
import com.algolia.search.dsl.geosearch.DSLPolygon
import com.algolia.search.dsl.highlighting.DSLSnippet
import com.algolia.search.dsl.languages.DSLLanguage
import com.algolia.search.dsl.strategy.DSLAdvancedSyntaxFeatures
import com.algolia.search.dsl.strategy.DSLAlternativesAsExact
import com.algolia.search.model.filter.FilterGroupsConverter
import com.algolia.search.model.search.Query


/**
 * Create a [Query] with [block] and an optional [query].
 */
public fun query(query: String? = null, block: Query.() -> Unit): Query {
    return Query(query = query).apply(block)
}

/**
 * Assign the output of [block] to [Query.attributesToRetrieve].
 */
public fun Query.attributesToRetrieve(block: DSLAttributesToRetrieve.() -> Unit) {
    attributesToRetrieve = DSLAttributesToRetrieve(block)
}

/**
 * Assign the output of [block] to [Query.attributesToRetrieve].
 */
public fun Query.restrictSearchableAttributes(block: DSLAttributes.() -> Unit) {
    restrictSearchableAttributes = DSLAttributes(block)
}

/**
 * Use [FilterGroupsConverter.SQL] on the [block] output and assign it to [Query.filters].
 */
public fun Query.filters(block: DSLFilters.() -> Unit) {
    filters = FilterGroupsConverter.SQL(DSLFilters(block))
}

/**
 * Use [FilterGroupsConverter.Legacy] on the [block] output and assign it to [Query.optionalFilters].
 */
public fun Query.optionalFilters(block: DSLFacetFilters.() -> Unit) {
    optionalFilters = FilterGroupsConverter.Legacy.Facet(DSLFacetFilters(block))
}

/**
 * Use [FilterGroupsConverter.Legacy] on the [block] output and assign it to [Query.facetFilters].
 */
public fun Query.facetFilters(block: DSLFacetFilters.() -> Unit) {
    facetFilters = FilterGroupsConverter.Legacy.Facet(DSLFacetFilters(block))
}

/**
 * Use [FilterGroupsConverter.Legacy] on the [block] output and assign it to [Query.numericFilters].
 */
public fun Query.numericFilters(block: DSLNumericFilters.() -> Unit) {
    numericFilters = FilterGroupsConverter.Legacy.Numeric(DSLNumericFilters(block))
}

/**
 * Use [FilterGroupsConverter.Legacy] on the [block] output and assign it to [Query.tagFilters].
 */
public fun Query.tagFilters(block: DSLTagFilters.() -> Unit) {
    tagFilters = FilterGroupsConverter.Legacy.Tag(DSLTagFilters(block))
}

/**
 * Assign the output of [block] to [Query.facets].
 */
public fun Query.facets(block: DSLAttributesSet.() -> Unit) {
    facets = DSLAttributesSet(block)
}

/**
 * Assign the output of [block] to [Query.attributesToHighlight].
 */
public fun Query.attributesToHighlight(block: DSLAttributes.() -> Unit) {
    attributesToHighlight = DSLAttributes(block)
}

/**
 * Assign the output of [block] to [Query.attributesToSnippet].
 */
public fun Query.attributesToSnippet(block: DSLSnippet.() -> Unit) {
    attributesToSnippet = DSLSnippet(block)
}

/**
 * Assign the output of [block] to [Query.disableTypoToleranceOnAttributes].
 */
public fun Query.disableTypoToleranceOnAttributes(block: DSLAttributes.() -> Unit) {
    disableTypoToleranceOnAttributes = DSLAttributes(block)
}

/**
 * Assign the output of [block] to [Query.insideBoundingBox].
 */
public fun Query.insideBoundingBox(block: DSLBoundingBox.() -> Unit) {
    insideBoundingBox = DSLBoundingBox(block)
}

/**
 * Assign the output of [block] to [Query.insidePolygon].
 */
public fun Query.insidePolygon(block: DSLPolygon.() -> Unit) {
    insidePolygon = DSLPolygon(block)
}

/**
 * Assign the output of [block] to [Query.queryLanguages].
 */
public fun Query.queryLanguages(block: DSLLanguage.() -> Unit) {
    queryLanguages = DSLLanguage(block)
}

/**
 * Assign the output of [block] to [Query.advancedSyntaxFeatures].
 */
public fun Query.advancedSyntaxFeatures(block: DSLAdvancedSyntaxFeatures.() -> Unit) {
    advancedSyntaxFeatures = DSLAdvancedSyntaxFeatures(block)
}

/**
 * Assign the output of [block] to [Query.optionalWords].
 */
public fun Query.optionalWords(block: DSLStrings.() -> Unit) {
    optionalWords = DSLStrings(block)
}

/**
 * Assign the output of [block] to [Query.disableExactOnAttributes].
 */
public fun Query.disableExactOnAttributes(block: DSLAttributes.() -> Unit) {
    disableExactOnAttributes = DSLAttributes(block)
}

/**
 * Assign the output of [block] to [Query.alternativesAsExact].
 */
public fun Query.alternativesAsExact(block: DSLAlternativesAsExact.() -> Unit) {
    alternativesAsExact = DSLAlternativesAsExact(block)
}

/**
 * Assign the output of [block] to [Query.ruleContexts].
 */
public fun Query.ruleContexts(block: DSLStrings.() -> Unit) {
    ruleContexts = DSLStrings(block)
}

/**
 * Assign the output of [block] to [Query.analyticsTags].
 */
public fun Query.analyticsTags(block: DSLStrings.() -> Unit) {
    analyticsTags = DSLStrings(block)
}

/**
 * Assign the output of [block] to [Query.responseFields].
 */
public fun Query.responseFields(block: DSLResponseFields.() -> Unit) {
    responseFields = DSLResponseFields(block)
}

/**
 * Assign the output of [block] to [Query.explainModules].
 */
public fun Query.explainModules(block: DSLExplainModules.() -> Unit) {
    explainModules = DSLExplainModules(block)
}