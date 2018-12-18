package client.query.helper

import client.data.AlternativesAsExact
import client.data.Attribute
import client.query.Query
import client.data.QueryLanguage
import client.data.ResponseFields

@DslMarker
annotation class QueryHelper

internal val Collection<Attribute>.names get() = map { it.name }

internal val all = Attribute("*")

fun String.toAttribute() = Attribute(this)

fun queryBuilder(init: Query.() -> Unit) = Query().apply(init)

fun Query.setAttributesToRetrieve(vararg attributes: Attribute, excludeAttributes: Boolean = false) {
    attributesToRetrieve = if (excludeAttributes) {
        if (attributes.isNotEmpty()) {
            attributes.map { Attribute("-${it.name}") }.plus(all)
        } else listOf()
    } else attributes.toList()
}

fun Query.setRetrieveAllAttributes() {
    attributesToRetrieve = listOf(all)
}

fun Query.setRestrictSearchableAttributes(vararg attributes: Attribute) {
    restrictSearchableAttributes = attributes.toList()
}

fun Query.setFacets(vararg attributes: Attribute) {
    facets = attributes.toList()
}

fun Query.setAllFacets() {
    facets = listOf(all)
}

fun Query.setAttributesToHighlight(vararg attributes: Attribute) {
    attributesToHighlight = attributes.toList()
}

fun Query.setHighlightAllAttributes() {
    attributesToHighlight = listOf(all)
}

fun Query.setAttributesToSnippet(vararg attributes: Pair<Attribute, Int?>) {
    attributesToSnippet = attributes.map { if (it.second != null) "${it.first.name}:${it.second}" else it.first.name }
}

fun Query.setSnippetAllAttributes(numberOfWords: Int? = null) {
    attributesToSnippet = listOf(if (numberOfWords != null) "*:$numberOfWords" else "*")
}

fun Query.setDisableTypoToleranceOnAttributes(vararg attributes: Attribute) {
    disableTypoToleranceOnAttributes = attributes.toList()
}

fun Query.setQueryLanguages(vararg languages: QueryLanguage) {
    queryLanguages = languages.toList()
}

fun Query.setRuleContexts(vararg contexts: String) {
    ruleContexts = contexts.toList()
}

fun Query.setOptionalWords(vararg words: String) {
    optionalWords = words.toList()
}

fun Query.setDisableExactOnAttributes(vararg attributes: Attribute) {
    disableExactOnAttributes = attributes.toList()
}

fun Query.setAlternativesAsExact(vararg alternativesAsExact: AlternativesAsExact) {
    this.alternativesAsExact = alternativesAsExact.toList()
}

fun Query.setAnalyticsTags(vararg tags: String) {
    analyticsTags = tags.toList()
}

fun Query.setResponseFields(vararg responseFields: ResponseFields) {
    this.responseFields = responseFields.toList()
}

inline fun Query.filterBuilder(init: FilterBuilder.() -> Unit) {
    filterBuilder.apply(init)
}

inline fun Query.optionalFilterBuilder(init: OptionalFilterBuilder.() -> Unit) {
    optionalFilterBuilder.apply(init)
}