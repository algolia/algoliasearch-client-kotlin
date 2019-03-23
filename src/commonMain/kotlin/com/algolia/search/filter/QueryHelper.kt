package com.algolia.search.filter

import com.algolia.search.model.Attribute
import com.algolia.search.model.search.*

@DslMarker
annotation class QueryHelper

internal val all = Attribute("*")

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

/**
 * When this attribute is set to true, the list of words in [optionalWords] will be replaced by all the words
 * found in the current [query].
 * This will trigger the engine to return records containing any word matching the query (OR operation).
 * Otherwise, the engine return records containing all the word matching the query (AND operation).
 * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalWords/#doing-an-or-between-all-words-of-a-query]
 */
public fun Query.setIsEveryWordInQueryOptional() {
    query?.let { optionalWords = listOf() }
}