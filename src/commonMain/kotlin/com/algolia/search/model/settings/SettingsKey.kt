package com.algolia.search.model.settings

import com.algolia.search.endpoint.EndpointSettings
import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.KeyAdvancedSyntax
import com.algolia.search.serialize.KeyAllowCompressionOfIntegerArray
import com.algolia.search.serialize.KeyAllowTyposOnNumericTokens
import com.algolia.search.serialize.KeyAlternativesAsExact
import com.algolia.search.serialize.KeyAttributeForDistinct
import com.algolia.search.serialize.KeyAttributesForFaceting
import com.algolia.search.serialize.KeyAttributesToHighlight
import com.algolia.search.serialize.KeyAttributesToRetrieve
import com.algolia.search.serialize.KeyAttributesToSnippet
import com.algolia.search.serialize.KeyCamelCaseAttributes
import com.algolia.search.serialize.KeyCustomRanking
import com.algolia.search.serialize.KeyDecompoundedAttributes
import com.algolia.search.serialize.KeyDisableExactOnAttributes
import com.algolia.search.serialize.KeyDisablePrefixOnAttributes
import com.algolia.search.serialize.KeyDisableTypoToleranceOnAttributes
import com.algolia.search.serialize.KeyDisableTypoToleranceOnWords
import com.algolia.search.serialize.KeyDistinct
import com.algolia.search.serialize.KeyEnableRules
import com.algolia.search.serialize.KeyExactOnSingleWordQuery
import com.algolia.search.serialize.KeyHighlightPostTag
import com.algolia.search.serialize.KeyHighlightPreTag
import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyIgnorePlurals
import com.algolia.search.serialize.KeyIndexLanguages
import com.algolia.search.serialize.KeyKeepDiacriticsOnCharacters
import com.algolia.search.serialize.KeyMaxFacetHits
import com.algolia.search.serialize.KeyMaxValuesPerFacet
import com.algolia.search.serialize.KeyMinProximity
import com.algolia.search.serialize.KeyMinWordSizeFor1Typo
import com.algolia.search.serialize.KeyMinWordSizeFor2Typos
import com.algolia.search.serialize.KeyNumericAttributesForFiltering
import com.algolia.search.serialize.KeyOptionalWords
import com.algolia.search.serialize.KeyPaginationLimitedTo
import com.algolia.search.serialize.KeyQueryLanguages
import com.algolia.search.serialize.KeyQueryType
import com.algolia.search.serialize.KeyRanking
import com.algolia.search.serialize.KeyRemoveStopWords
import com.algolia.search.serialize.KeyRemoveWordsIfNoResults
import com.algolia.search.serialize.KeyReplaceSynonymsInHighlight
import com.algolia.search.serialize.KeyReplicas
import com.algolia.search.serialize.KeyResponseFields
import com.algolia.search.serialize.KeyRestrictHighlightAndSnippetArrays
import com.algolia.search.serialize.KeySearchableAttributes
import com.algolia.search.serialize.KeySeparatorsToIndex
import com.algolia.search.serialize.KeySnippetEllipsisText
import com.algolia.search.serialize.KeySortFacetValuesBy
import com.algolia.search.serialize.KeyTypoTolerance
import com.algolia.search.serialize.KeyUnretrievableAttributes
import com.algolia.search.serialize.KeyUserData

/**
 * Used to mark which settings should be reset to its default value by [EndpointSettings.setSettings].
 */
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

    public object UserData : SettingsKey(KeyUserData)

    public object IndexLanguages : SettingsKey(KeyIndexLanguages)

    public data class Other(override val raw: String) : SettingsKey(raw)
}
