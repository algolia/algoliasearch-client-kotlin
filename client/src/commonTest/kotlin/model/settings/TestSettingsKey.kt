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
import com.algolia.search.serialize.internal.Key
import shouldEqual
import kotlin.test.Test

internal class TestSettingsKey {

    @Test
    fun raw() {
        SearchableAttributes.raw shouldEqual Key.SearchableAttributes
        AttributesForFaceting.raw shouldEqual Key.AttributesForFaceting
        UnretrievableAttributes.raw shouldEqual Key.UnretrievableAttributes
        AttributesToRetrieve.raw shouldEqual Key.AttributesToRetrieve
        Ranking.raw shouldEqual Key.Ranking
        CustomRanking.raw shouldEqual Key.CustomRanking
        Replicas.raw shouldEqual Key.Replicas
        MaxValuesPerFacet.raw shouldEqual Key.MaxValuesPerFacet
        SortFacetsBy.raw shouldEqual Key.SortFacetValuesBy
        AttributesToHighlight.raw shouldEqual Key.AttributesToHighlight
        AttributesToSnippet.raw shouldEqual Key.AttributesToSnippet
        HighlightPreTag.raw shouldEqual Key.HighlightPreTag
        HighlightPostTag.raw shouldEqual Key.HighlightPostTag
        SnippetEllipsisText.raw shouldEqual Key.SnippetEllipsisText
        RestrictHighlightAndSnippetArrays.raw shouldEqual Key.RestrictHighlightAndSnippetArrays
        HitsPerPage.raw shouldEqual Key.HitsPerPage
        PaginationLimitedTo.raw shouldEqual Key.PaginationLimitedTo
        MinWordSizefor1Typo.raw shouldEqual Key.MinWordSizeFor1Typo
        MinWordSizefor2Typos.raw shouldEqual Key.MinWordSizeFor2Typos
        TypoTolerance.raw shouldEqual Key.TypoTolerance
        AllowTyposOnNumericTokens.raw shouldEqual Key.AllowTyposOnNumericTokens
        DisableTypoToleranceOnAttributes.raw shouldEqual Key.DisableTypoToleranceOnAttributes
        DisableTypoToleranceOnWords.raw shouldEqual Key.DisableTypoToleranceOnWords
        SeparatorsToIndex.raw shouldEqual Key.SeparatorsToIndex
        IgnorePlurals.raw shouldEqual Key.IgnorePlurals
        RemoveStopWords.raw shouldEqual Key.RemoveStopWords
        CamelCaseAttributes.raw shouldEqual Key.CamelCaseAttributes
        DecompoundedAttributes.raw shouldEqual Key.DecompoundedAttributes
        KeepDiacriticsOnCharacters.raw shouldEqual Key.KeepDiacriticsOnCharacters
        QueryLanguages.raw shouldEqual Key.QueryLanguages
        EnableRules.raw shouldEqual Key.EnableRules
        QueryType.raw shouldEqual Key.QueryType
        RemoveWordsIfNoResults.raw shouldEqual Key.RemoveWordsIfNoResults
        AdvancedSyntax.raw shouldEqual Key.AdvancedSyntax
        OptionalWords.raw shouldEqual Key.OptionalWords
        DisablePrefixOnAttributes.raw shouldEqual Key.DisablePrefixOnAttributes
        DisableExactOnAttributes.raw shouldEqual Key.DisableExactOnAttributes
        ExactOnSingleWordQuery.raw shouldEqual Key.ExactOnSingleWordQuery
        AlternativesAsExact.raw shouldEqual Key.AlternativesAsExact
        NumericAttributesForFiltering.raw shouldEqual Key.NumericAttributesForFiltering
        AllowCompressionOfIntegerArray.raw shouldEqual Key.AllowCompressionOfIntegerArray
        AttributeForDistinct.raw shouldEqual Key.AttributeForDistinct
        Distinct.raw shouldEqual Key.Distinct
        ReplaceSynonymsInHighlight.raw shouldEqual Key.ReplaceSynonymsInHighlight
        MinProximity.raw shouldEqual Key.MinProximity
        ResponseFields.raw shouldEqual Key.ResponseFields
        MaxFacetHits.raw shouldEqual Key.MaxFacetHits
        IndexLanguages.raw shouldEqual Key.IndexLanguages
        UserData.raw shouldEqual Key.UserData
    }
}
