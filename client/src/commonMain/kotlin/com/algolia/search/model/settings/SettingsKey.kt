package com.algolia.search.model.settings

import com.algolia.search.endpoint.EndpointSettings
import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.internal.Key

/**
 * Used to mark which settings should be reset to its default value by [EndpointSettings.setSettings].
 */
public sealed class SettingsKey(override val raw: String) : Raw<String> {

    public object SearchableAttributes : SettingsKey(Key.SearchableAttributes)

    public object AttributesForFaceting : SettingsKey(Key.AttributesForFaceting)

    public object UnretrievableAttributes : SettingsKey(Key.UnretrievableAttributes)

    public object AttributesToRetrieve : SettingsKey(Key.AttributesToRetrieve)

    public object Ranking : SettingsKey(Key.Ranking)

    public object CustomRanking : SettingsKey(Key.CustomRanking)

    public object Replicas : SettingsKey(Key.Replicas)

    public object MaxValuesPerFacet : SettingsKey(Key.MaxValuesPerFacet)

    public object SortFacetsBy : SettingsKey(Key.SortFacetValuesBy)

    public object AttributesToHighlight : SettingsKey(Key.AttributesToHighlight)

    public object AttributesToSnippet : SettingsKey(Key.AttributesToSnippet)

    public object HighlightPreTag : SettingsKey(Key.HighlightPreTag)

    public object HighlightPostTag : SettingsKey(Key.HighlightPostTag)

    public object SnippetEllipsisText : SettingsKey(Key.SnippetEllipsisText)

    public object RestrictHighlightAndSnippetArrays : SettingsKey(Key.RestrictHighlightAndSnippetArrays)

    public object HitsPerPage : SettingsKey(Key.HitsPerPage)

    public object PaginationLimitedTo : SettingsKey(Key.PaginationLimitedTo)

    public object MinWordSizefor1Typo : SettingsKey(Key.MinWordSizeFor1Typo)

    public object MinWordSizefor2Typos : SettingsKey(Key.MinWordSizeFor2Typos)

    public object TypoTolerance : SettingsKey(Key.TypoTolerance)

    public object AllowTyposOnNumericTokens : SettingsKey(Key.AllowTyposOnNumericTokens)

    public object DisableTypoToleranceOnAttributes : SettingsKey(Key.DisableTypoToleranceOnAttributes)

    public object DisableTypoToleranceOnWords : SettingsKey(Key.DisableTypoToleranceOnWords)

    public object SeparatorsToIndex : SettingsKey(Key.SeparatorsToIndex)

    public object IgnorePlurals : SettingsKey(Key.IgnorePlurals)

    public object RemoveStopWords : SettingsKey(Key.RemoveStopWords)

    public object CamelCaseAttributes : SettingsKey(Key.CamelCaseAttributes)

    public object DecompoundedAttributes : SettingsKey(Key.DecompoundedAttributes)

    public object KeepDiacriticsOnCharacters : SettingsKey(Key.KeepDiacriticsOnCharacters)

    public object QueryLanguages : SettingsKey(Key.QueryLanguages)

    public object EnableRules : SettingsKey(Key.EnableRules)

    public object QueryType : SettingsKey(Key.QueryType)

    public object RemoveWordsIfNoResults : SettingsKey(Key.RemoveWordsIfNoResults)

    public object AdvancedSyntax : SettingsKey(Key.AdvancedSyntax)

    public object OptionalWords : SettingsKey(Key.OptionalWords)

    public object DisablePrefixOnAttributes : SettingsKey(Key.DisablePrefixOnAttributes)

    public object DisableExactOnAttributes : SettingsKey(Key.DisableExactOnAttributes)

    public object ExactOnSingleWordQuery : SettingsKey(Key.ExactOnSingleWordQuery)

    public object AlternativesAsExact : SettingsKey(Key.AlternativesAsExact)

    public object NumericAttributesForFiltering : SettingsKey(Key.NumericAttributesForFiltering)

    public object AllowCompressionOfIntegerArray : SettingsKey(Key.AllowCompressionOfIntegerArray)

    public object AttributeForDistinct : SettingsKey(Key.AttributeForDistinct)

    public object Distinct : SettingsKey(Key.Distinct)

    public object ReplaceSynonymsInHighlight : SettingsKey(Key.ReplaceSynonymsInHighlight)

    public object MinProximity : SettingsKey(Key.MinProximity)

    public object ResponseFields : SettingsKey(Key.ResponseFields)

    public object MaxFacetHits : SettingsKey(Key.MaxFacetHits)

    public object UserData : SettingsKey(Key.UserData)

    public object IndexLanguages : SettingsKey(Key.IndexLanguages)

    public data class Other(override val raw: String) : SettingsKey(raw)
}
