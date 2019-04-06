package com.algolia.search.dsl

import com.algolia.search.dsl.advanced.DSLResponseFields
import com.algolia.search.dsl.attributes.DSLAttributes
import com.algolia.search.dsl.attributes.DSLAttributesForFaceting
import com.algolia.search.dsl.attributes.DSLAttributesToRetrieve
import com.algolia.search.dsl.attributes.DSLSearchableAttributes
import com.algolia.search.dsl.highlighting.DSLSnippet
import com.algolia.search.dsl.languages.DSLDecompoundedAttributes
import com.algolia.search.dsl.languages.DSLQueryLanguage
import com.algolia.search.dsl.performance.DSLNumericAttributeFilter
import com.algolia.search.dsl.ranking.DSLCustomRanking
import com.algolia.search.dsl.ranking.DSLIndexName
import com.algolia.search.dsl.ranking.DSLRanking
import com.algolia.search.dsl.strategy.DSLAdvancedSyntaxFeatures
import com.algolia.search.dsl.strategy.DSLAlternativesAsExact
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.settings.SettingsKey


public fun settings(block: Settings.() -> Unit): Settings {
    return Settings().apply(block)
}

public fun settingsKey(block: DSLSettingsKey.() -> Unit): List<SettingsKey> {
    return DSLSettingsKey(block)
}

public fun Settings.attributesToRetrieve(block: DSLAttributesToRetrieve.() -> Unit) {
    attributesToRetrieve = DSLAttributesToRetrieve(block)
}

public fun Settings.searchableAttributes(block: DSLSearchableAttributes.() -> Unit) {
    searchableAttributes = DSLSearchableAttributes(block)
}

public fun Settings.attributesForFaceting(block: DSLAttributesForFaceting.() -> Unit) {
    attributesForFaceting = DSLAttributesForFaceting(block)
}

public fun Settings.unretrieveableAttributes(block: DSLAttributes.() -> Unit) {
    unretrievableAttributes = DSLAttributes(block)
}

public fun Settings.ranking(block: DSLRanking.() -> Unit) {
    ranking = DSLRanking(block)
}

public fun Settings.customRanking(block: DSLCustomRanking.() -> Unit) {
    customRanking = DSLCustomRanking(block)
}

public fun Settings.replicas(block: DSLIndexName.() -> Unit) {
    replicas = DSLIndexName(block)
}

public fun Settings.attributesToHighlight(block: DSLAttributes.() -> Unit) {
    attributesToHighlight = DSLAttributes(block)
}

public fun Settings.attributesToSnippet(block: DSLSnippet.() -> Unit) {
    attributesToSnippet = DSLSnippet(block)
}

public fun Settings.disableTypoToleranceOnAttributes(block: DSLAttributes.() -> Unit) {
    disableTypoToleranceOnAttributes = DSLAttributes(block)
}

public fun Settings.disableTypoToleranceOnWords(block: DSLStrings.() -> Unit) {
    disableTypoToleranceOnWords = DSLStrings(block)
}

public fun Settings.queryLanguages(block: DSLQueryLanguage.() -> Unit) {
    queryLanguages = DSLQueryLanguage(block)
}

public fun Settings.advancedSyntaxFeatures(block: DSLAdvancedSyntaxFeatures.() -> Unit) {
    advancedSyntaxFeatures = DSLAdvancedSyntaxFeatures(block)
}

public fun Settings.camelCaseAttributes(block: DSLAttributes.() -> Unit) {
    camelCaseAttributes = DSLAttributes(block)
}

public fun Settings.decompoundedAttributes(block: DSLDecompoundedAttributes.() -> Unit) {
    decompoundedAttributes = DSLDecompoundedAttributes(block)
}

public fun Settings.optionalWords(block: DSLStrings.() -> Unit) {
    optionalWords = DSLStrings(block)
}

public fun Settings.disablePrefixOnAttributes(block: DSLAttributes.() -> Unit) {
    disablePrefixOnAttributes = DSLAttributes(block)
}

public fun Settings.disableExactOnAttributes(block: DSLAttributes.() -> Unit) {
    disableExactOnAttributes = DSLAttributes(block)
}

public fun Settings.alternativesAsExact(block: DSLAlternativesAsExact.() -> Unit) {
    alternativesAsExact = DSLAlternativesAsExact(block)
}

public fun Settings.numericAttributesForFiltering(block: DSLNumericAttributeFilter.() -> Unit) {
    numericAttributesForFiltering = DSLNumericAttributeFilter(block)
}

public fun Settings.responseFields(block: DSLResponseFields.() -> Unit) {
    responseFields = DSLResponseFields(block)
}