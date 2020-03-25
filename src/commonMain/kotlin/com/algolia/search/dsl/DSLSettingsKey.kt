package com.algolia.search.dsl

import com.algolia.search.model.settings.SettingsKey

/**
 * DSL for building a [List] of [SettingsKey].
 */
@Suppress("PropertyName")
class DSLSettingsKey(
    private val settingsKeys: MutableList<SettingsKey> = mutableListOf()
) {
    val SearchableAttributes = SettingsKey.SearchableAttributes
    val AttributesForFaceting = SettingsKey.AttributesForFaceting
    val UnretrievableAttributes = SettingsKey.UnretrievableAttributes
    val AttributesToRetrieve = SettingsKey.AttributesToRetrieve
    val Ranking = SettingsKey.Ranking
    val CustomRanking = SettingsKey.CustomRanking
    val Replicas = SettingsKey.Replicas
    val MaxValuesPerFacet = SettingsKey.MaxValuesPerFacet
    val SortFacetsBy = SettingsKey.SortFacetsBy
    val AttributesToHighlight = SettingsKey.AttributesToHighlight
    val AttributesToSnippet = SettingsKey.AttributesToSnippet
    val HighlightPreTag = SettingsKey.HighlightPreTag
    val HighlightPostTag = SettingsKey.HighlightPostTag
    val SnippetEllipsisText = SettingsKey.SnippetEllipsisText
    val RestrictHighlightAndSnippetArrays = SettingsKey.RestrictHighlightAndSnippetArrays
    val HitsPerPage = SettingsKey.HitsPerPage
    val PaginationLimitedTo = SettingsKey.PaginationLimitedTo
    val MinWordSizefor1Typo = SettingsKey.MinWordSizefor1Typo
    val MinWordSizefor2Typos = SettingsKey.MinWordSizefor2Typos
    val TypoTolerance = SettingsKey.TypoTolerance
    val AllowTyposOnNumericTokens = SettingsKey.AllowTyposOnNumericTokens
    val DisableTypoToleranceOnAttributes = SettingsKey.DisableTypoToleranceOnAttributes
    val DisableTypoToleranceOnWords = SettingsKey.DisableTypoToleranceOnWords
    val SeparatorsToIndex = SettingsKey.SeparatorsToIndex
    val IgnorePlurals = SettingsKey.IgnorePlurals
    val RemoveStopWords = SettingsKey.RemoveStopWords
    val CamelCaseAttributes = SettingsKey.CamelCaseAttributes
    val DecompoundedAttributes = SettingsKey.DecompoundedAttributes
    val KeepDiacriticsOnCharacters = SettingsKey.KeepDiacriticsOnCharacters
    val QueryLanguages = SettingsKey.QueryLanguages
    val EnableRules = SettingsKey.EnableRules
    val QueryType = SettingsKey.QueryType
    val RemoveWordsIfNoResults = SettingsKey.RemoveWordsIfNoResults
    val AdvancedSyntax = SettingsKey.AdvancedSyntax
    val OptionalWords = SettingsKey.OptionalWords
    val DisablePrefixOnAttributes = SettingsKey.DisablePrefixOnAttributes
    val DisableExactOnAttributes = SettingsKey.DisableExactOnAttributes
    val ExactOnSingleWordQuery = SettingsKey.ExactOnSingleWordQuery
    val AlternativesAsExact = SettingsKey.AlternativesAsExact
    val NumericAttributesForFiltering = SettingsKey.NumericAttributesForFiltering
    val AllowCompressionOfIntegerArray = SettingsKey.AllowCompressionOfIntegerArray
    val AttributeForDistinct = SettingsKey.AttributeForDistinct
    val Distinct = SettingsKey.Distinct
    val ReplaceSynonymsInHighlight = SettingsKey.ReplaceSynonymsInHighlight
    val MinProximity = SettingsKey.MinProximity
    val ResponseFields = SettingsKey.ResponseFields
    val MaxFacetHits = SettingsKey.MaxFacetHits

    /**
     * Add [this] to [settingsKeys].
     */
    operator fun SettingsKey.unaryPlus() {
        settingsKeys += this
    }

    companion object :
        DSL<DSLSettingsKey, List<SettingsKey>> {

        override operator fun invoke(block: DSLSettingsKey.() -> Unit): List<SettingsKey> {
            return DSLSettingsKey().apply(block).settingsKeys
        }
    }
}
