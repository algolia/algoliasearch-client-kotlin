package data

import com.algolia.search.saas.data.SettingsKey.*
import com.algolia.search.saas.serialize.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
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
        SortFacetValuesBy.raw shouldEqual KeySortFacetValuesBy
        AttributesToHighlight.raw shouldEqual KeyAttributesToHighlight
        AttributesToSnippet.raw shouldEqual KeyAttributesToSnippet
        HighlightPreTag.raw shouldEqual KeyHighlightPreTag
        HighlightPostTag.raw shouldEqual KeyHighlightPostTag
        SnippetEllipsisText.raw shouldEqual KeySnippetEllipsisText
        RestrictHighlightAndSnippetArrays.raw shouldEqual KeyRestrictHighlightAndSnippetArrays
        HitsPerPage.raw shouldEqual KeyHitsPerPage
        PaginationLimitedTo.raw shouldEqual KeyPaginationLimitedTo
        MinWordSizefor1Typo.raw shouldEqual KeyMinWordSizefor1Typo
        MinWordSizefor2Typos.raw shouldEqual KeyMinWordSizefor2Typos
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
    }
}