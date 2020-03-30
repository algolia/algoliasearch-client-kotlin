package model.settings

import com.algolia.search.model.settings.SettingsKey.AdvancedSyntax
import com.algolia.search.model.settings.SettingsKey.AllowCompressionOfIntegerArray
import com.algolia.search.model.settings.SettingsKey.AllowTyposOnNumericTokens
import com.algolia.search.model.settings.SettingsKey.AlternativesAsExact
import com.algolia.search.model.settings.SettingsKey.AttributeForDistinct
import com.algolia.search.model.settings.SettingsKey.AttributesForFaceting
import com.algolia.search.model.settings.SettingsKey.AttributesToHighlight
import com.algolia.search.model.settings.SettingsKey.AttributesToRetrieve
import com.algolia.search.model.settings.SettingsKey.AttributesToSnippet
import com.algolia.search.model.settings.SettingsKey.CamelCaseAttributes
import com.algolia.search.model.settings.SettingsKey.CustomRanking
import com.algolia.search.model.settings.SettingsKey.DecompoundedAttributes
import com.algolia.search.model.settings.SettingsKey.DisableExactOnAttributes
import com.algolia.search.model.settings.SettingsKey.DisablePrefixOnAttributes
import com.algolia.search.model.settings.SettingsKey.DisableTypoToleranceOnAttributes
import com.algolia.search.model.settings.SettingsKey.DisableTypoToleranceOnWords
import com.algolia.search.model.settings.SettingsKey.Distinct
import com.algolia.search.model.settings.SettingsKey.EnableRules
import com.algolia.search.model.settings.SettingsKey.ExactOnSingleWordQuery
import com.algolia.search.model.settings.SettingsKey.HighlightPostTag
import com.algolia.search.model.settings.SettingsKey.HighlightPreTag
import com.algolia.search.model.settings.SettingsKey.HitsPerPage
import com.algolia.search.model.settings.SettingsKey.IgnorePlurals
import com.algolia.search.model.settings.SettingsKey.IndexLanguages
import com.algolia.search.model.settings.SettingsKey.KeepDiacriticsOnCharacters
import com.algolia.search.model.settings.SettingsKey.MaxFacetHits
import com.algolia.search.model.settings.SettingsKey.MaxValuesPerFacet
import com.algolia.search.model.settings.SettingsKey.MinProximity
import com.algolia.search.model.settings.SettingsKey.MinWordSizefor1Typo
import com.algolia.search.model.settings.SettingsKey.MinWordSizefor2Typos
import com.algolia.search.model.settings.SettingsKey.NumericAttributesForFiltering
import com.algolia.search.model.settings.SettingsKey.OptionalWords
import com.algolia.search.model.settings.SettingsKey.PaginationLimitedTo
import com.algolia.search.model.settings.SettingsKey.QueryLanguages
import com.algolia.search.model.settings.SettingsKey.QueryType
import com.algolia.search.model.settings.SettingsKey.Ranking
import com.algolia.search.model.settings.SettingsKey.RemoveStopWords
import com.algolia.search.model.settings.SettingsKey.RemoveWordsIfNoResults
import com.algolia.search.model.settings.SettingsKey.ReplaceSynonymsInHighlight
import com.algolia.search.model.settings.SettingsKey.Replicas
import com.algolia.search.model.settings.SettingsKey.ResponseFields
import com.algolia.search.model.settings.SettingsKey.RestrictHighlightAndSnippetArrays
import com.algolia.search.model.settings.SettingsKey.SearchableAttributes
import com.algolia.search.model.settings.SettingsKey.SeparatorsToIndex
import com.algolia.search.model.settings.SettingsKey.SnippetEllipsisText
import com.algolia.search.model.settings.SettingsKey.SortFacetsBy
import com.algolia.search.model.settings.SettingsKey.TypoTolerance
import com.algolia.search.model.settings.SettingsKey.UnretrievableAttributes
import com.algolia.search.model.settings.SettingsKey.UserData
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
import kotlin.test.Test
import shouldEqual

internal class TestSettingsKey {

    @Test
    fun raw() {
        SearchableAttributes.raw shouldEqual KeySearchableAttributes
        AttributesForFaceting.raw shouldEqual KeyAttributesForFaceting
        UnretrievableAttributes.raw shouldEqual KeyUnretrievableAttributes
        AttributesToRetrieve.raw shouldEqual KeyAttributesToRetrieve
        Ranking.raw shouldEqual KeyRanking
        CustomRanking.raw shouldEqual KeyCustomRanking
        Replicas.raw shouldEqual KeyReplicas
        MaxValuesPerFacet.raw shouldEqual KeyMaxValuesPerFacet
        SortFacetsBy.raw shouldEqual KeySortFacetValuesBy
        AttributesToHighlight.raw shouldEqual KeyAttributesToHighlight
        AttributesToSnippet.raw shouldEqual KeyAttributesToSnippet
        HighlightPreTag.raw shouldEqual KeyHighlightPreTag
        HighlightPostTag.raw shouldEqual KeyHighlightPostTag
        SnippetEllipsisText.raw shouldEqual KeySnippetEllipsisText
        RestrictHighlightAndSnippetArrays.raw shouldEqual KeyRestrictHighlightAndSnippetArrays
        HitsPerPage.raw shouldEqual KeyHitsPerPage
        PaginationLimitedTo.raw shouldEqual KeyPaginationLimitedTo
        MinWordSizefor1Typo.raw shouldEqual KeyMinWordSizeFor1Typo
        MinWordSizefor2Typos.raw shouldEqual KeyMinWordSizeFor2Typos
        TypoTolerance.raw shouldEqual KeyTypoTolerance
        AllowTyposOnNumericTokens.raw shouldEqual KeyAllowTyposOnNumericTokens
        DisableTypoToleranceOnAttributes.raw shouldEqual KeyDisableTypoToleranceOnAttributes
        DisableTypoToleranceOnWords.raw shouldEqual KeyDisableTypoToleranceOnWords
        SeparatorsToIndex.raw shouldEqual KeySeparatorsToIndex
        IgnorePlurals.raw shouldEqual KeyIgnorePlurals
        RemoveStopWords.raw shouldEqual KeyRemoveStopWords
        CamelCaseAttributes.raw shouldEqual KeyCamelCaseAttributes
        DecompoundedAttributes.raw shouldEqual KeyDecompoundedAttributes
        KeepDiacriticsOnCharacters.raw shouldEqual KeyKeepDiacriticsOnCharacters
        QueryLanguages.raw shouldEqual KeyQueryLanguages
        EnableRules.raw shouldEqual KeyEnableRules
        QueryType.raw shouldEqual KeyQueryType
        RemoveWordsIfNoResults.raw shouldEqual KeyRemoveWordsIfNoResults
        AdvancedSyntax.raw shouldEqual KeyAdvancedSyntax
        OptionalWords.raw shouldEqual KeyOptionalWords
        DisablePrefixOnAttributes.raw shouldEqual KeyDisablePrefixOnAttributes
        DisableExactOnAttributes.raw shouldEqual KeyDisableExactOnAttributes
        ExactOnSingleWordQuery.raw shouldEqual KeyExactOnSingleWordQuery
        AlternativesAsExact.raw shouldEqual KeyAlternativesAsExact
        NumericAttributesForFiltering.raw shouldEqual KeyNumericAttributesForFiltering
        AllowCompressionOfIntegerArray.raw shouldEqual KeyAllowCompressionOfIntegerArray
        AttributeForDistinct.raw shouldEqual KeyAttributeForDistinct
        Distinct.raw shouldEqual KeyDistinct
        ReplaceSynonymsInHighlight.raw shouldEqual KeyReplaceSynonymsInHighlight
        MinProximity.raw shouldEqual KeyMinProximity
        ResponseFields.raw shouldEqual KeyResponseFields
        MaxFacetHits.raw shouldEqual KeyMaxFacetHits
        IndexLanguages.raw shouldEqual KeyIndexLanguages
        UserData.raw shouldEqual KeyUserData
    }
}
