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
fun settings(block: Settings.() -> Unit): Settings {
    return Settings().apply(block)
}

/**
 * Create a [List] of [SettingsKey] with [block].
 */
fun settingsKey(block: DSLSettingsKey.() -> Unit): List<SettingsKey> {
    return DSLSettingsKey(block)
}

/**
 * Assign the output of [block] to [Settings.attributesToRetrieve].
 */
fun Settings.attributesToRetrieve(block: DSLAttributesToRetrieve.() -> Unit) {
    attributesToRetrieve = DSLAttributesToRetrieve(block)
}

/**
 * Assign the output of [block] to [Settings.searchableAttributes].
 */
fun Settings.searchableAttributes(block: DSLSearchableAttributes.() -> Unit) {
    searchableAttributes = DSLSearchableAttributes(block)
}

/**
 * Assign the output of [block] to [Settings.attributesForFaceting].
 */
fun Settings.attributesForFaceting(block: DSLAttributesForFaceting.() -> Unit) {
    attributesForFaceting = DSLAttributesForFaceting(block)
}

/**
 * Assign the output of [block] to [Settings.unretrievableAttributes].
 */
fun Settings.unretrieveableAttributes(block: DSLAttributes.() -> Unit) {
    unretrievableAttributes = DSLAttributes(block)
}

/**
 * Assign the output of [block] to [Settings.ranking].
 */
fun Settings.ranking(block: DSLRanking.() -> Unit) {
    ranking = DSLRanking(block)
}

/**
 * Assign the output of [block] to [Settings.customRanking].
 */
fun Settings.customRanking(block: DSLCustomRanking.() -> Unit) {
    customRanking = DSLCustomRanking(block)
}

/**
 * Assign the output of [block] to [Settings.replicas].
 */
fun Settings.replicas(block: DSLIndexName.() -> Unit) {
    replicas = DSLIndexName(block)
}

/**
 * Assign the output of [block] to [Settings.attributesToHighlight].
 */
fun Settings.attributesToHighlight(block: DSLAttributes.() -> Unit) {
    attributesToHighlight = DSLAttributes(block)
}

/**
 * Assign the output of [block] to [Settings.attributesToSnippet].
 */
fun Settings.attributesToSnippet(block: DSLSnippet.() -> Unit) {
    attributesToSnippet = DSLSnippet(block)
}

/**
 * Assign the output of [block] to [Settings.disableTypoToleranceOnAttributes].
 */
fun Settings.disableTypoToleranceOnAttributes(block: DSLAttributes.() -> Unit) {
    disableTypoToleranceOnAttributes = DSLAttributes(block)
}

/**
 * Assign the output of [block] to [Settings.disableTypoToleranceOnWords].
 */
fun Settings.disableTypoToleranceOnWords(block: DSLStrings.() -> Unit) {
    disableTypoToleranceOnWords = DSLStrings(block)
}

/**
 * Assign the output of [block] to [Settings.queryLanguages].
 */
fun Settings.queryLanguages(block: DSLLanguage.() -> Unit) {
    queryLanguages = DSLLanguage(block)
}

/**
 * Assign the output of [block] to [Settings.queryLanguages].
 */
fun Settings.indexLanguages(block: DSLLanguage.() -> Unit) {
    indexLanguages = DSLLanguage(block)
}

/**
 * Assign the output of [block] to [Settings.advancedSyntaxFeatures].
 */
fun Settings.advancedSyntaxFeatures(block: DSLAdvancedSyntaxFeatures.() -> Unit) {
    advancedSyntaxFeatures = DSLAdvancedSyntaxFeatures(block)
}

/**
 * Assign the output of [block] to [Settings.camelCaseAttributes].
 */
fun Settings.camelCaseAttributes(block: DSLAttributes.() -> Unit) {
    camelCaseAttributes = DSLAttributes(block)
}

/**
 * Assign the output of [block] to [Settings.decompoundedAttributes].
 */
fun Settings.decompoundedAttributes(block: DSLDecompoundedAttributes.() -> Unit) {
    decompoundedAttributes = DSLDecompoundedAttributes(block)
}

/**
 * Assign the output of [block] to [Settings.optionalWords].
 */
fun Settings.optionalWords(block: DSLStrings.() -> Unit) {
    optionalWords = DSLStrings(block)
}

/**
 * Assign the output of [block] to [Settings.disablePrefixOnAttributes].
 */
fun Settings.disablePrefixOnAttributes(block: DSLAttributes.() -> Unit) {
    disablePrefixOnAttributes = DSLAttributes(block)
}

/**
 * Assign the output of [block] to [Settings.disableExactOnAttributes].
 */
fun Settings.disableExactOnAttributes(block: DSLAttributes.() -> Unit) {
    disableExactOnAttributes = DSLAttributes(block)
}

/**
 * Assign the output of [block] to [Settings.alternativesAsExact].
 */
fun Settings.alternativesAsExact(block: DSLAlternativesAsExact.() -> Unit) {
    alternativesAsExact = DSLAlternativesAsExact(block)
}

/**
 * Assign the output of [block] to [Settings.numericAttributesForFiltering].
 */
fun Settings.numericAttributesForFiltering(block: DSLNumericAttributeFilter.() -> Unit) {
    numericAttributesForFiltering = DSLNumericAttributeFilter(block)
}

/**
 * Assign the output of [block] to [Settings.responseFields].
 */
fun Settings.responseFields(block: DSLResponseFields.() -> Unit) {
    responseFields = DSLResponseFields(block)
}
