package com.algolia.search.model.settings

import com.algolia.search.endpoint.EndpointSettings
import com.algolia.search.model.Raw
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
sealed class SettingsKey(override val raw: String) : Raw<String> {

    object SearchableAttributes : SettingsKey(KeySearchableAttributes)

    object AttributesForFaceting : SettingsKey(KeyAttributesForFaceting)

    object UnretrievableAttributes : SettingsKey(KeyUnretrievableAttributes)

    object AttributesToRetrieve : SettingsKey(KeyAttributesToRetrieve)

    object Ranking : SettingsKey(KeyRanking)

    object CustomRanking : SettingsKey(KeyCustomRanking)

    object Replicas : SettingsKey(KeyReplicas)

    object MaxValuesPerFacet : SettingsKey(KeyMaxValuesPerFacet)

    object SortFacetsBy : SettingsKey(KeySortFacetValuesBy)

    object AttributesToHighlight : SettingsKey(KeyAttributesToHighlight)

    object AttributesToSnippet : SettingsKey(KeyAttributesToSnippet)

    object HighlightPreTag : SettingsKey(KeyHighlightPreTag)

    object HighlightPostTag : SettingsKey(KeyHighlightPostTag)

    object SnippetEllipsisText : SettingsKey(KeySnippetEllipsisText)

    object RestrictHighlightAndSnippetArrays : SettingsKey(KeyRestrictHighlightAndSnippetArrays)

    object HitsPerPage : SettingsKey(KeyHitsPerPage)

    object PaginationLimitedTo : SettingsKey(KeyPaginationLimitedTo)

    object MinWordSizefor1Typo : SettingsKey(KeyMinWordSizeFor1Typo)

    object MinWordSizefor2Typos : SettingsKey(KeyMinWordSizeFor2Typos)

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

    object UserData : SettingsKey(KeyUserData)

    object IndexLanguages : SettingsKey(KeyIndexLanguages)

    data class Other(override val raw: String) : SettingsKey(raw)
}
