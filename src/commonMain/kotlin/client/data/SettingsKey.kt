package client.data

import client.serialize.*


sealed class SettingsKey(override val raw: String) : RawString {

    object SearchableAttributes : SettingsKey(KeySearchableAttributes)

    object AttributesForFaceting : SettingsKey(KeyAttributesForFaceting)

    object UnretrievableAttributes : SettingsKey(KeyUnretrievableAttributes)

    object AttributesToRetrieve : SettingsKey(KeyAttributesToRetrieve)

    object Ranking : SettingsKey(KeyRanking)

    object CustomRanking : SettingsKey(KeyCustomRanking)

    object Replicas : SettingsKey(KeyReplicas)

    object MaxValuesPerFacet : SettingsKey(KeyMaxValuesPerFacet)

    object SortFacetValuesBy : SettingsKey(KeySortFacetValuesBy)

    object AttributesToHighlight : SettingsKey(KeyAttributesToHighlight)

    object AttributesToSnippet : SettingsKey(KeyAttributesToSnippet)

    object HighlightPreTag : SettingsKey(KeyHighlightPreTag)

    object HighlightPostTag : SettingsKey(KeyHighlightPostTag)

    object SnippetEllipsisText : SettingsKey(KeySnippetEllipsisText)

    object RestrictHighlightAndSnippetArrays : SettingsKey(KeyRestrictHighlightAndSnippetArrays)

    object HitsPerPage : SettingsKey(KeyHitsPerPage)

    object PaginationLimitedTo : SettingsKey(KeyPaginationLimitedTo)

    object MinWordSizefor1Typo : SettingsKey(KeyMinWordSizefor1Typo)

    object MinWordSizefor2Typos : SettingsKey(KeyMinWordSizefor2Typos)

    object TypoTolerance : SettingsKey(KeyTypoTolerance)

    object AllowTyposOnNumericTokens : SettingsKey(KeyAllowTyposOnNumericTokens)

    object DisableTypoToleranceOnAttributes : SettingsKey(KeyDisableTypoToleranceOnAttributes)

    object DisableTypoToleranceOnWords : SettingsKey(KeyDisableTypoToleranceOnWords)

    object SeparatorsToIndex : SettingsKey(KeySeparatorsToIndex)

    object IgnorePlurals : SettingsKey(KeyIgnorePlurals)

    object RemoveStopWords : SettingsKey(KeyRemoveStopWords)

    object CamelCaseAttributes : SettingsKey(KeyCamelCaseAttributes)

    object DecompoundedAttributes : SettingsKey(KeyDecompoundedAttributes)

    object KeepDiacriticsOnCharacters : SettingsKey(KeyKeepDiacriticsOnCharacters)

    object QueryLanguages : SettingsKey(KeyQueryLanguages)

    object EnableRules : SettingsKey(KeyEnableRules)

    object QueryType : SettingsKey(KeyQueryType)

    object RemoveWordsIfNoResults : SettingsKey(KeyRemoveWordsIfNoResults)

    object AdvancedSyntax : SettingsKey(KeyAdvancedSyntax)

    object OptionalWords : SettingsKey(KeyOptionalWords)

    object DisablePrefixOnAttributes : SettingsKey(KeyDisablePrefixOnAttributes)

    object DisableExactOnAttributes : SettingsKey(KeyDisableExactOnAttributes)

    object ExactOnSingleWordQuery : SettingsKey(KeyExactOnSingleWordQuery)

    object AlternativesAsExact : SettingsKey(KeyAlternativesAsExact)

    object NumericAttributesForFiltering : SettingsKey(KeyNumericAttributesForFiltering)

    object AllowCompressionOfIntegerArray : SettingsKey(KeyAllowCompressionOfIntegerArray)

    object AttributeForDistinct : SettingsKey(KeyAttributeForDistinct)

    object Distinct : SettingsKey(KeyDistinct)

    object ReplaceSynonymsInHighlight : SettingsKey(KeyReplaceSynonymsInHighlight)

    object MinProximity : SettingsKey(KeyMinProximity)

    object ResponseFields : SettingsKey(KeyResponseFields)

    object MaxFacetHits : SettingsKey(KeyMaxFacetHits)

    data class Custom(override val raw: String) : SettingsKey(raw)
}