package com.algolia.search.dsl

import com.algolia.search.model.settings.SettingsKey

/**
 * DSL for building a [List] of [SettingsKey].
 */
@Suppress("PropertyName")
public class DSLSettingsKey(
    private val settingsKeys: MutableList<SettingsKey> = mutableListOf()
) {
    public val SearchableAttributes = SettingsKey.SearchableAttributes
    public val AttributesForFaceting = SettingsKey.AttributesForFaceting
    public val UnretrievableAttributes = SettingsKey.UnretrievableAttributes
    public val AttributesToRetrieve = SettingsKey.AttributesToRetrieve
    public val Ranking = SettingsKey.Ranking
    public val CustomRanking = SettingsKey.CustomRanking
    public val Replicas = SettingsKey.Replicas
    public val MaxValuesPerFacet = SettingsKey.MaxValuesPerFacet
    public val SortFacetsBy = SettingsKey.SortFacetsBy
    public val AttributesToHighlight = SettingsKey.AttributesToHighlight
    public val AttributesToSnippet = SettingsKey.AttributesToSnippet
    public val HighlightPreTag = SettingsKey.HighlightPreTag
    public val HighlightPostTag = SettingsKey.HighlightPostTag
    public val SnippetEllipsisText = SettingsKey.SnippetEllipsisText
    public val RestrictHighlightAndSnippetArrays = SettingsKey.RestrictHighlightAndSnippetArrays
    public val HitsPerPage = SettingsKey.HitsPerPage
    public val PaginationLimitedTo = SettingsKey.PaginationLimitedTo
    public val MinWordSizefor1Typo = SettingsKey.MinWordSizefor1Typo
    public val MinWordSizefor2Typos = SettingsKey.MinWordSizefor2Typos
    public val TypoTolerance = SettingsKey.TypoTolerance
    public val AllowTyposOnNumericTokens = SettingsKey.AllowTyposOnNumericTokens
    public val DisableTypoToleranceOnAttributes = SettingsKey.DisableTypoToleranceOnAttributes
    public val DisableTypoToleranceOnWords = SettingsKey.DisableTypoToleranceOnWords
    public val SeparatorsToIndex = SettingsKey.SeparatorsToIndex
    public val IgnorePlurals = SettingsKey.IgnorePlurals
    public val RemoveStopWords = SettingsKey.RemoveStopWords
    public val CamelCaseAttributes = SettingsKey.CamelCaseAttributes
    public val DecompoundedAttributes = SettingsKey.DecompoundedAttributes
    public val KeepDiacriticsOnCharacters = SettingsKey.KeepDiacriticsOnCharacters
    public val QueryLanguages = SettingsKey.QueryLanguages
    public val EnableRules = SettingsKey.EnableRules
    public val QueryType = SettingsKey.QueryType
    public val RemoveWordsIfNoResults = SettingsKey.RemoveWordsIfNoResults
    public val AdvancedSyntax = SettingsKey.AdvancedSyntax
    public val OptionalWords = SettingsKey.OptionalWords
    public val DisablePrefixOnAttributes = SettingsKey.DisablePrefixOnAttributes
    public val DisableExactOnAttributes = SettingsKey.DisableExactOnAttributes
    public val ExactOnSingleWordQuery = SettingsKey.ExactOnSingleWordQuery
    public val AlternativesAsExact = SettingsKey.AlternativesAsExact
    public val NumericAttributesForFiltering = SettingsKey.NumericAttributesForFiltering
    public val AllowCompressionOfIntegerArray = SettingsKey.AllowCompressionOfIntegerArray
    public val AttributeForDistinct = SettingsKey.AttributeForDistinct
    public val Distinct = SettingsKey.Distinct
    public val ReplaceSynonymsInHighlight = SettingsKey.ReplaceSynonymsInHighlight
    public val MinProximity = SettingsKey.MinProximity
    public val ResponseFields = SettingsKey.ResponseFields
    public val MaxFacetHits = SettingsKey.MaxFacetHits

    /**
     * Add [this] to [settingsKeys].
     */
    public operator fun SettingsKey.unaryPlus() {
        settingsKeys += this
    }

    public companion object :
        DSL<DSLSettingsKey, List<SettingsKey>> {

        override operator fun invoke(block: DSLSettingsKey.() -> Unit): List<SettingsKey> {
            return DSLSettingsKey().apply(block).settingsKeys
        }
    }
}
