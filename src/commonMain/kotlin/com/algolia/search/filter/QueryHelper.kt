package com.algolia.search.filter

import com.algolia.search.model.Attribute
import com.algolia.search.model.search.*

@DslMarker
annotation class QueryHelper

internal val all = Attribute("*")

internal fun Query.build() = copy(
    filters = filters ?: filterBuilder.build().let { if (it.isNotEmpty()) it else null },
    optionalFilters = optionalFilters ?: optionalFilterBuilder.build().let { if (it.isNotEmpty()) it else null },
    optionalWords = query?.let { if (isEveryWordInQueryOptional) listOf(it) else optionalWords }
)

public fun queryBuilder(init: Query.() -> Unit) = Query().apply(init)

public fun Query.setAttributesToRetrieve(vararg attributes: Attribute, excludeAttributes: Boolean = false) {
    attributesToRetrieve = if (excludeAttributes) {
        if (attributes.isNotEmpty()) {
            attributes.map { Attribute("-$it") }.plus(all)
        } else listOf()
    } else attributes.toList()
}

public fun Query.setAllAttributesToRetrieve() {
    attributesToRetrieve = listOf(all)
}

public fun Query.setRestrictSearchableAttributes(vararg attributes: Attribute) {
    restrictSearchableAttributes = attributes.toList()
}

public fun Query.setFacets(vararg attributes: Attribute) {
    facets = attributes.toList()
}

public fun Query.setAllFacets() {
    facets = listOf(all)
}

public fun Query.setAttributesToHighlight(vararg attributes: Attribute) {
    attributesToHighlight = attributes.toList()
}

public fun Query.setAllAttributesToHighlight() {
    attributesToHighlight = listOf(all)
}

public fun Query.setAttributesToSnippet(vararg snippets: Snippet) {
    attributesToSnippet = snippets.toList()
}

public fun Query.setAllAttributesToSnippet(numberOfWords: Int? = null) {
    attributesToSnippet = listOf(Snippet(all, numberOfWords))
}

public fun Query.setDisableTypoToleranceOnAttributes(vararg attributes: Attribute) {
    disableTypoToleranceOnAttributes = attributes.toList()
}

public fun Query.setQueryLanguages(vararg languages: QueryLanguage) {
    queryLanguages = languages.toList()
}

public fun Query.setRuleContexts(vararg contexts: String) {
    ruleContexts = contexts.toList()
}

public fun Query.setOptionalWords(vararg words: String) {
    optionalWords = words.toList()
}

public fun Query.setDisableExactOnAttributes(vararg attributes: Attribute) {
    disableExactOnAttributes = attributes.toList()
}

public fun Query.setAlternativesAsExact(vararg alternativesAsExact: AlternativesAsExact) {
    this.alternativesAsExact = alternativesAsExact.toList()
}

public fun Query.setAnalyticsTags(vararg tags: String) {
    analyticsTags = tags.toList()
}

public fun Query.setResponseFields(vararg responseFields: ResponseFields) {
    this.responseFields = responseFields.toList()
}

inline fun Query.filterBuilder(init: FilterBuilder.() -> Unit) {
    filterBuilder.apply(init)
}

inline fun Query.optionalFilterBuilder(init: OptionalFilterBuilder.() -> Unit) {
    optionalFilterBuilder.apply(init)
}