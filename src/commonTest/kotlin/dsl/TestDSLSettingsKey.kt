package dsl

import com.algolia.search.dsl.DSLSettingsKey
import com.algolia.search.model.settings.SettingsKey
import shouldEqual
import kotlin.test.Test

internal class TestDSLSettingsKey {

    @Test
    fun default() {
        val dsl = DSLSettingsKey {
            +AdvancedSyntax
        }

        dsl shouldEqual listOf(SettingsKey.AdvancedSyntax)
    }

    @Test
    fun keys() {
        DSLSettingsKey {
            SearchableAttributes shouldEqual SettingsKey.SearchableAttributes
            AttributesForFaceting shouldEqual SettingsKey.AttributesForFaceting
            UnretrievableAttributes shouldEqual SettingsKey.UnretrievableAttributes
            AttributesToRetrieve shouldEqual SettingsKey.AttributesToRetrieve
            Ranking shouldEqual SettingsKey.Ranking
            CustomRanking shouldEqual SettingsKey.CustomRanking
            Replicas shouldEqual SettingsKey.Replicas
            MaxValuesPerFacet shouldEqual SettingsKey.MaxValuesPerFacet
            SortFacetsBy shouldEqual SettingsKey.SortFacetsBy
            AttributesToHighlight shouldEqual SettingsKey.AttributesToHighlight
            AttributesToSnippet shouldEqual SettingsKey.AttributesToSnippet
            HighlightPreTag shouldEqual SettingsKey.HighlightPreTag
            HighlightPostTag shouldEqual SettingsKey.HighlightPostTag
            SnippetEllipsisText shouldEqual SettingsKey.SnippetEllipsisText
            RestrictHighlightAndSnippetArrays shouldEqual SettingsKey.RestrictHighlightAndSnippetArrays
            HitsPerPage shouldEqual SettingsKey.HitsPerPage
            PaginationLimitedTo shouldEqual SettingsKey.PaginationLimitedTo
            MinWordSizefor1Typo shouldEqual SettingsKey.MinWordSizefor1Typo
            MinWordSizefor2Typos shouldEqual SettingsKey.MinWordSizefor2Typos
            TypoTolerance shouldEqual SettingsKey.TypoTolerance
            AllowTyposOnNumericTokens shouldEqual SettingsKey.AllowTyposOnNumericTokens
            DisableTypoToleranceOnAttributes shouldEqual SettingsKey.DisableTypoToleranceOnAttributes
            DisableTypoToleranceOnWords shouldEqual SettingsKey.DisableTypoToleranceOnWords
            SeparatorsToIndex shouldEqual SettingsKey.SeparatorsToIndex
            IgnorePlurals shouldEqual SettingsKey.IgnorePlurals
            RemoveStopWords shouldEqual SettingsKey.RemoveStopWords
            CamelCaseAttributes shouldEqual SettingsKey.CamelCaseAttributes
            DecompoundedAttributes shouldEqual SettingsKey.DecompoundedAttributes
            KeepDiacriticsOnCharacters shouldEqual SettingsKey.KeepDiacriticsOnCharacters
            QueryLanguages shouldEqual SettingsKey.QueryLanguages
            EnableRules shouldEqual SettingsKey.EnableRules
            QueryType shouldEqual SettingsKey.QueryType
            RemoveWordsIfNoResults shouldEqual SettingsKey.RemoveWordsIfNoResults
            AdvancedSyntax shouldEqual SettingsKey.AdvancedSyntax
            OptionalWords shouldEqual SettingsKey.OptionalWords
            DisablePrefixOnAttributes shouldEqual SettingsKey.DisablePrefixOnAttributes
            DisableExactOnAttributes shouldEqual SettingsKey.DisableExactOnAttributes
            ExactOnSingleWordQuery shouldEqual SettingsKey.ExactOnSingleWordQuery
            AlternativesAsExact shouldEqual SettingsKey.AlternativesAsExact
            NumericAttributesForFiltering shouldEqual SettingsKey.NumericAttributesForFiltering
            AllowCompressionOfIntegerArray shouldEqual SettingsKey.AllowCompressionOfIntegerArray
            AttributeForDistinct shouldEqual SettingsKey.AttributeForDistinct
            Distinct shouldEqual SettingsKey.Distinct
            ReplaceSynonymsInHighlight shouldEqual SettingsKey.ReplaceSynonymsInHighlight
            MinProximity shouldEqual SettingsKey.MinProximity
            ResponseFields shouldEqual SettingsKey.ResponseFields
            MaxFacetHits shouldEqual SettingsKey.MaxFacetHits
        }
    }
}
