package com.algolia.search.dsl

import com.algolia.search.dsl.advanced.DSLResponseFields
import com.algolia.search.dsl.attributes.DSLAttributes
import com.algolia.search.dsl.attributes.DSLAttributesForFaceting
import com.algolia.search.dsl.attributes.DSLAttributesToRetrieve
import com.algolia.search.dsl.attributes.DSLSearchableAttributes
import com.algolia.search.dsl.highlighting.DSLSnippet
import com.algolia.search.dsl.languages.DSLDecompoundedAttributes
import com.algolia.search.dsl.languages.DSLLanguage
import com.algolia.search.dsl.performance.DSLNumericAttributeFilter
import com.algolia.search.dsl.ranking.DSLCustomRanking
import com.algolia.search.dsl.ranking.DSLIndexName
import com.algolia.search.dsl.ranking.DSLRanking
import com.algolia.search.dsl.strategy.DSLAdvancedSyntaxFeatures
import com.algolia.search.dsl.strategy.DSLAlternativesAsExact
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.settings.SettingsKey

/**
 * Create a [Settings] with [block].
 */
public fun settings(block: Settings.() -> Unit): Settings {
    return Settings().apply(block)
}

/**
 * Create a [List] of [SettingsKey] with [block].
 */
public fun settingsKey(block: DSLSettingsKey.() -> Unit): List<SettingsKey> {
    return DSLSettingsKey(block)
}

/**
 * Assign the output of [block] to [Settings.attributesToRetrieve].
 */
public fun Settings.attributesToRetrieve(block: DSLAttributesToRetrieve.() -> Unit) {
    attributesToRetrieve = DSLAttributesToRetrieve(block)
}

/**
 * Assign the output of [block] to [Settings.searchableAttributes].
 */
public fun Settings.searchableAttributes(block: DSLSearchableAttributes.() -> Unit) {
    searchableAttributes = DSLSearchableAttributes(block)
}

/**
 * Assign the output of [block] to [Settings.attributesForFaceting].
 */
public fun Settings.attributesForFaceting(block: DSLAttributesForFaceting.() -> Unit) {
    attributesForFaceting = DSLAttributesForFaceting(block)
}

/**
 * Assign the output of [block] to [Settings.unretrievableAttributes].
 */
public fun Settings.unretrieveableAttributes(block: DSLAttributes.() -> Unit) {
    unretrievableAttributes = DSLAttributes(block)
}

/**
 * Assign the output of [block] to [Settings.ranking].
 */
public fun Settings.ranking(block: DSLRanking.() -> Unit) {
    ranking = DSLRanking(block)
}

/**
 * Assign the output of [block] to [Settings.customRanking].
 */
public fun Settings.customRanking(block: DSLCustomRanking.() -> Unit) {
    customRanking = DSLCustomRanking(block)
}

/**
 * Assign the output of [block] to [Settings.replicas].
 */
public fun Settings.replicas(block: DSLIndexName.() -> Unit) {
    replicas = DSLIndexName(block)
}

/**
 * Assign the output of [block] to [Settings.attributesToHighlight].
 */
public fun Settings.attributesToHighlight(block: DSLAttributes.() -> Unit) {
    attributesToHighlight = DSLAttributes(block)
}

/**
 * Assign the output of [block] to [Settings.attributesToSnippet].
 */
public fun Settings.attributesToSnippet(block: DSLSnippet.() -> Unit) {
    attributesToSnippet = DSLSnippet(block)
}

/**
 * Assign the output of [block] to [Settings.disableTypoToleranceOnAttributes].
 */
public fun Settings.disableTypoToleranceOnAttributes(block: DSLAttributes.() -> Unit) {
    disableTypoToleranceOnAttributes = DSLAttributes(block)
}

/**
 * Assign the output of [block] to [Settings.disableTypoToleranceOnWords].
 */
public fun Settings.disableTypoToleranceOnWords(block: DSLStrings.() -> Unit) {
    disableTypoToleranceOnWords = DSLStrings(block)
}

/**
 * Assign the output of [block] to [Settings.queryLanguages].
 */
public fun Settings.queryLanguages(block: DSLLanguage.() -> Unit) {
    queryLanguages = DSLLanguage(block)
}

/**
 * Assign the output of [block] to [Settings.queryLanguages].
 */
public fun Settings.indexLanguages(block: DSLLanguage.() -> Unit) {
    indexLanguages = DSLLanguage(block)
}

/**
 * Assign the output of [block] to [Settings.advancedSyntaxFeatures].
 */
public fun Settings.advancedSyntaxFeatures(block: DSLAdvancedSyntaxFeatures.() -> Unit) {
    advancedSyntaxFeatures = DSLAdvancedSyntaxFeatures(block)
}

/**
 * Assign the output of [block] to [Settings.camelCaseAttributes].
 */
public fun Settings.camelCaseAttributes(block: DSLAttributes.() -> Unit) {
    camelCaseAttributes = DSLAttributes(block)
}

/**
 * Assign the output of [block] to [Settings.decompoundedAttributes].
 */
public fun Settings.decompoundedAttributes(block: DSLDecompoundedAttributes.() -> Unit) {
    decompoundedAttributes = DSLDecompoundedAttributes(block)
}

/**
 * Assign the output of [block] to [Settings.optionalWords].
 */
public fun Settings.optionalWords(block: DSLStrings.() -> Unit) {
    optionalWords = DSLStrings(block)
}

/**
 * Assign the output of [block] to [Settings.disablePrefixOnAttributes].
 */
public fun Settings.disablePrefixOnAttributes(block: DSLAttributes.() -> Unit) {
    disablePrefixOnAttributes = DSLAttributes(block)
}

/**
 * Assign the output of [block] to [Settings.disableExactOnAttributes].
 */
public fun Settings.disableExactOnAttributes(block: DSLAttributes.() -> Unit) {
    disableExactOnAttributes = DSLAttributes(block)
}

/**
 * Assign the output of [block] to [Settings.alternativesAsExact].
 */
public fun Settings.alternativesAsExact(block: DSLAlternativesAsExact.() -> Unit) {
    alternativesAsExact = DSLAlternativesAsExact(block)
}

/**
 * Assign the output of [block] to [Settings.numericAttributesForFiltering].
 */
public fun Settings.numericAttributesForFiltering(block: DSLNumericAttributeFilter.() -> Unit) {
    numericAttributesForFiltering = DSLNumericAttributeFilter(block)
}

/**
 * Assign the output of [block] to [Settings.responseFields].
 */
public fun Settings.responseFields(block: DSLResponseFields.() -> Unit) {
    responseFields = DSLResponseFields(block)
}
