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
import com.algolia.search.serialize.internal.KeyAdvancedSyntax
import com.algolia.search.serialize.internal.KeyAllowCompressionOfIntegerArray
import com.algolia.search.serialize.internal.KeyAllowTyposOnNumericTokens
import com.algolia.search.serialize.internal.KeyAlternativesAsExact
import com.algolia.search.serialize.internal.KeyAttributeForDistinct
import com.algolia.search.serialize.internal.KeyAttributesForFaceting
import com.algolia.search.serialize.internal.KeyAttributesToHighlight
import com.algolia.search.serialize.internal.KeyAttributesToRetrieve
import com.algolia.search.serialize.internal.KeyAttributesToSnippet
import com.algolia.search.serialize.internal.KeyCamelCaseAttributes
import com.algolia.search.serialize.internal.KeyCustomRanking
import com.algolia.search.serialize.internal.KeyDecompoundedAttributes
import com.algolia.search.serialize.internal.KeyDisableExactOnAttributes
import com.algolia.search.serialize.internal.KeyDisablePrefixOnAttributes
import com.algolia.search.serialize.internal.KeyDisableTypoToleranceOnAttributes
import com.algolia.search.serialize.internal.KeyDisableTypoToleranceOnWords
import com.algolia.search.serialize.internal.KeyDistinct
import com.algolia.search.serialize.internal.KeyEnableRules
import com.algolia.search.serialize.internal.KeyExactOnSingleWordQuery
import com.algolia.search.serialize.internal.KeyHighlightPostTag
import com.algolia.search.serialize.internal.KeyHighlightPreTag
import com.algolia.search.serialize.internal.KeyHitsPerPage
import com.algolia.search.serialize.internal.KeyIgnorePlurals
import com.algolia.search.serialize.internal.KeyIndexLanguages
import com.algolia.search.serialize.internal.KeyKeepDiacriticsOnCharacters
import com.algolia.search.serialize.internal.KeyMaxFacetHits
import com.algolia.search.serialize.internal.KeyMaxValuesPerFacet
import com.algolia.search.serialize.internal.KeyMinProximity
import com.algolia.search.serialize.internal.KeyMinWordSizeFor1Typo
import com.algolia.search.serialize.internal.KeyMinWordSizeFor2Typos
import com.algolia.search.serialize.internal.KeyNumericAttributesForFiltering
import com.algolia.search.serialize.internal.KeyOptionalWords
import com.algolia.search.serialize.internal.KeyPaginationLimitedTo
import com.algolia.search.serialize.internal.KeyQueryLanguages
import com.algolia.search.serialize.internal.KeyQueryType
import com.algolia.search.serialize.internal.KeyRanking
import com.algolia.search.serialize.internal.KeyRemoveStopWords
import com.algolia.search.serialize.internal.KeyRemoveWordsIfNoResults
import com.algolia.search.serialize.internal.KeyReplaceSynonymsInHighlight
import com.algolia.search.serialize.internal.KeyReplicas
import com.algolia.search.serialize.internal.KeyResponseFields
import com.algolia.search.serialize.internal.KeyRestrictHighlightAndSnippetArrays
import com.algolia.search.serialize.internal.KeySearchableAttributes
import com.algolia.search.serialize.internal.KeySeparatorsToIndex
import com.algolia.search.serialize.internal.KeySnippetEllipsisText
import com.algolia.search.serialize.internal.KeySortFacetValuesBy
import com.algolia.search.serialize.internal.KeyTypoTolerance
import com.algolia.search.serialize.internal.KeyUnretrievableAttributes
import com.algolia.search.serialize.internal.KeyUserData
import shouldEqual
import kotlin.test.Test

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
