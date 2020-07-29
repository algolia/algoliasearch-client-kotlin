package com.algolia.search.dsl

import com.algolia.search.model.settings.SettingsKey

/**
 * DSL for building a [List] of [SettingsKey].
 */
@Suppress("PropertyName")
public class DSLSettingsKey(
    private val settingsKeys: MutableList<SettingsKey> = mutableListOf()
) {
    public val SearchableAttributes: SettingsKey.SearchableAttributes = SettingsKey.SearchableAttributes
    public val AttributesForFaceting: SettingsKey.AttributesForFaceting = SettingsKey.AttributesForFaceting
    public val UnretrievableAttributes: SettingsKey.UnretrievableAttributes = SettingsKey.UnretrievableAttributes
    public val AttributesToRetrieve: SettingsKey.AttributesToRetrieve = SettingsKey.AttributesToRetrieve
    public val Ranking: SettingsKey.Ranking = SettingsKey.Ranking
    public val CustomRanking: SettingsKey.CustomRanking = SettingsKey.CustomRanking
    public val Replicas: SettingsKey.Replicas = SettingsKey.Replicas
    public val MaxValuesPerFacet: SettingsKey.MaxValuesPerFacet = SettingsKey.MaxValuesPerFacet
    public val SortFacetsBy: SettingsKey.SortFacetsBy = SettingsKey.SortFacetsBy
    public val AttributesToHighlight: SettingsKey.AttributesToHighlight = SettingsKey.AttributesToHighlight
    public val AttributesToSnippet: SettingsKey.AttributesToSnippet = SettingsKey.AttributesToSnippet
    public val HighlightPreTag: SettingsKey.HighlightPreTag = SettingsKey.HighlightPreTag
    public val HighlightPostTag: SettingsKey.HighlightPostTag = SettingsKey.HighlightPostTag
    public val SnippetEllipsisText: SettingsKey.SnippetEllipsisText = SettingsKey.SnippetEllipsisText
    public val RestrictHighlightAndSnippetArrays: SettingsKey.RestrictHighlightAndSnippetArrays = SettingsKey.RestrictHighlightAndSnippetArrays
    public val HitsPerPage: SettingsKey.HitsPerPage = SettingsKey.HitsPerPage
    public val PaginationLimitedTo: SettingsKey.PaginationLimitedTo = SettingsKey.PaginationLimitedTo
    public val MinWordSizefor1Typo: SettingsKey.MinWordSizefor1Typo = SettingsKey.MinWordSizefor1Typo
    public val MinWordSizefor2Typos: SettingsKey.MinWordSizefor2Typos = SettingsKey.MinWordSizefor2Typos
    public val TypoTolerance: SettingsKey.TypoTolerance = SettingsKey.TypoTolerance
    public val AllowTyposOnNumericTokens: SettingsKey.AllowTyposOnNumericTokens = SettingsKey.AllowTyposOnNumericTokens
    public val DisableTypoToleranceOnAttributes: SettingsKey.DisableTypoToleranceOnAttributes = SettingsKey.DisableTypoToleranceOnAttributes
    public val DisableTypoToleranceOnWords: SettingsKey.DisableTypoToleranceOnWords = SettingsKey.DisableTypoToleranceOnWords
    public val SeparatorsToIndex: SettingsKey.SeparatorsToIndex = SettingsKey.SeparatorsToIndex
    public val IgnorePlurals: SettingsKey.IgnorePlurals = SettingsKey.IgnorePlurals
    public val RemoveStopWords: SettingsKey.RemoveStopWords = SettingsKey.RemoveStopWords
    public val CamelCaseAttributes: SettingsKey.CamelCaseAttributes = SettingsKey.CamelCaseAttributes
    public val DecompoundedAttributes: SettingsKey.DecompoundedAttributes = SettingsKey.DecompoundedAttributes
    public val KeepDiacriticsOnCharacters: SettingsKey.KeepDiacriticsOnCharacters = SettingsKey.KeepDiacriticsOnCharacters
    public val QueryLanguages: SettingsKey.QueryLanguages = SettingsKey.QueryLanguages
    public val EnableRules: SettingsKey.EnableRules = SettingsKey.EnableRules
    public val QueryType: SettingsKey.QueryType = SettingsKey.QueryType
    public val RemoveWordsIfNoResults: SettingsKey.RemoveWordsIfNoResults = SettingsKey.RemoveWordsIfNoResults
    public val AdvancedSyntax: SettingsKey.AdvancedSyntax = SettingsKey.AdvancedSyntax
    public val OptionalWords: SettingsKey.OptionalWords = SettingsKey.OptionalWords
    public val DisablePrefixOnAttributes: SettingsKey.DisablePrefixOnAttributes = SettingsKey.DisablePrefixOnAttributes
    public val DisableExactOnAttributes: SettingsKey.DisableExactOnAttributes = SettingsKey.DisableExactOnAttributes
    public val ExactOnSingleWordQuery: SettingsKey.ExactOnSingleWordQuery = SettingsKey.ExactOnSingleWordQuery
    public val AlternativesAsExact: SettingsKey.AlternativesAsExact = SettingsKey.AlternativesAsExact
    public val NumericAttributesForFiltering: SettingsKey.NumericAttributesForFiltering = SettingsKey.NumericAttributesForFiltering
    public val AllowCompressionOfIntegerArray: SettingsKey.AllowCompressionOfIntegerArray = SettingsKey.AllowCompressionOfIntegerArray
    public val AttributeForDistinct: SettingsKey.AttributeForDistinct = SettingsKey.AttributeForDistinct
    public val Distinct: SettingsKey.Distinct = SettingsKey.Distinct
    public val ReplaceSynonymsInHighlight: SettingsKey.ReplaceSynonymsInHighlight = SettingsKey.ReplaceSynonymsInHighlight
    public val MinProximity: SettingsKey.MinProximity = SettingsKey.MinProximity
    public val ResponseFields: SettingsKey.ResponseFields = SettingsKey.ResponseFields
    public val MaxFacetHits: SettingsKey.MaxFacetHits = SettingsKey.MaxFacetHits

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
