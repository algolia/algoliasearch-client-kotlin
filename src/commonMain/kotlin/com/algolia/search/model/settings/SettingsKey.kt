package com.algolia.search.model.settings

import com.algolia.search.model.Raw
import com.algolia.search.serialize.*


public sealed class SettingsKey(override val raw: String) : Raw<String> {

    public object SearchableAttributes : SettingsKey(KeySearchableAttributes)

    public object AttributesForFaceting : SettingsKey(KeyAttributesForFaceting)

    public object UnretrievableAttributes : SettingsKey(KeyUnretrievableAttributes)

    public object AttributesToRetrieve : SettingsKey(KeyAttributesToRetrieve)

    public object Ranking : SettingsKey(KeyRanking)

    public object CustomRanking : SettingsKey(KeyCustomRanking)

    public object Replicas : SettingsKey(KeyReplicas)

    public object MaxValuesPerFacet : SettingsKey(KeyMaxValuesPerFacet)

    public object SortFacetsBy : SettingsKey(KeySortFacetValuesBy)

    public object AttributesToHighlight : SettingsKey(KeyAttributesToHighlight)

    public object AttributesToSnippet : SettingsKey(KeyAttributesToSnippet)

    public object HighlightPreTag : SettingsKey(KeyHighlightPreTag)

    public object HighlightPostTag : SettingsKey(KeyHighlightPostTag)

    public object SnippetEllipsisText : SettingsKey(KeySnippetEllipsisText)

    public object RestrictHighlightAndSnippetArrays : SettingsKey(KeyRestrictHighlightAndSnippetArrays)

    public object HitsPerPage : SettingsKey(KeyHitsPerPage)

    public object PaginationLimitedTo : SettingsKey(KeyPaginationLimitedTo)

    public object MinWordSizefor1Typo : SettingsKey(KeyMinWordSizeFor1Typo)

    public object MinWordSizefor2Typos : SettingsKey(KeyMinWordSizeFor2Typos)

    public object TypoTolerance : SettingsKey(KeyTypoTolerance)

    public object AllowTyposOnNumericTokens : SettingsKey(KeyAllowTyposOnNumericTokens)

    public object DisableTypoToleranceOnAttributes : SettingsKey(KeyDisableTypoToleranceOnAttributes)

    public object DisableTypoToleranceOnWords : SettingsKey(KeyDisableTypoToleranceOnWords)

    public object SeparatorsToIndex : SettingsKey(KeySeparatorsToIndex)

    public object IgnorePlurals : SettingsKey(KeyIgnorePlurals)

    public object RemoveStopWords : SettingsKey(KeyRemoveStopWords)

    public object CamelCaseAttributes : SettingsKey(KeyCamelCaseAttributes)

    public object DecompoundedAttributes : SettingsKey(KeyDecompoundedAttributes)

    public object KeepDiacriticsOnCharacters : SettingsKey(KeyKeepDiacriticsOnCharacters)

    public object QueryLanguages : SettingsKey(KeyQueryLanguages)

    public object EnableRules : SettingsKey(KeyEnableRules)

    public object QueryType : SettingsKey(KeyQueryType)

    public object RemoveWordsIfNoResults : SettingsKey(KeyRemoveWordsIfNoResults)

    public object AdvancedSyntax : SettingsKey(KeyAdvancedSyntax)

    public object OptionalWords : SettingsKey(KeyOptionalWords)

    public object DisablePrefixOnAttributes : SettingsKey(KeyDisablePrefixOnAttributes)

    public object DisableExactOnAttributes : SettingsKey(KeyDisableExactOnAttributes)

    public object ExactOnSingleWordQuery : SettingsKey(KeyExactOnSingleWordQuery)

    public object AlternativesAsExact : SettingsKey(KeyAlternativesAsExact)

    public object NumericAttributesForFiltering : SettingsKey(KeyNumericAttributesForFiltering)

    public object AllowCompressionOfIntegerArray : SettingsKey(KeyAllowCompressionOfIntegerArray)

    public object AttributeForDistinct : SettingsKey(KeyAttributeForDistinct)

    public object Distinct : SettingsKey(KeyDistinct)

    public object ReplaceSynonymsInHighlight : SettingsKey(KeyReplaceSynonymsInHighlight)

    public object MinProximity : SettingsKey(KeyMinProximity)

    public object ResponseFields : SettingsKey(KeyResponseFields)

    public object MaxFacetHits : SettingsKey(KeyMaxFacetHits)

    public data class Other(override val raw: String) : SettingsKey(raw)
}