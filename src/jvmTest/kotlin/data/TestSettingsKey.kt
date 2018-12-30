package data

import client.data.SettingsKey.*
import client.serialize.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestSettingsKey {

    @Test
    fun raw() {
        KeySearchableAttributes shouldEqual SearchableAttributes.raw
        KeyAttributesForFaceting shouldEqual AttributesForFaceting.raw
        KeyUnretrievableAttributes shouldEqual UnretrievableAttributes.raw
        KeyAttributesToRetrieve shouldEqual AttributesToRetrieve.raw
        KeyRanking shouldEqual Ranking.raw
        KeyCustomRanking shouldEqual CustomRanking.raw
        KeyReplicas shouldEqual Replicas.raw
        KeyMaxValuesPerFacet shouldEqual MaxValuesPerFacet.raw
        KeySortFacetValuesBy shouldEqual SortFacetValuesBy.raw
        KeyAttributesToHighlight shouldEqual AttributesToHighlight.raw
        KeyAttributesToSnippet shouldEqual AttributesToSnippet.raw
        KeyHighlightPreTag shouldEqual HighlightPreTag.raw
        KeyHighlightPostTag shouldEqual HighlightPostTag.raw
        KeySnippetEllipsisText shouldEqual SnippetEllipsisText.raw
        KeyRestrictHighlightAndSnippetArrays shouldEqual RestrictHighlightAndSnippetArrays.raw
        KeyHitsPerPage shouldEqual HitsPerPage.raw
        KeyPaginationLimitedTo shouldEqual PaginationLimitedTo.raw
        KeyMinWordSizefor1Typo shouldEqual MinWordSizefor1Typo.raw
        KeyMinWordSizefor2Typos shouldEqual MinWordSizefor2Typos.raw
        KeyTypoTolerance shouldEqual TypoTolerance.raw
        KeyAllowTyposOnNumericTokens shouldEqual AllowTyposOnNumericTokens.raw
        KeyDisableTypoToleranceOnAttributes shouldEqual DisableTypoToleranceOnAttributes.raw
        KeyDisableTypoToleranceOnWords shouldEqual DisableTypoToleranceOnWords.raw
        KeySeparatorsToIndex shouldEqual SeparatorsToIndex.raw
        KeyIgnorePlurals shouldEqual IgnorePlurals.raw
        KeyRemoveStopWords shouldEqual RemoveStopWords.raw
        KeyCamelCaseAttributes shouldEqual CamelCaseAttributes.raw
        KeyDecompoundedAttributes shouldEqual DecompoundedAttributes.raw
        KeyKeepDiacriticsOnCharacters shouldEqual KeepDiacriticsOnCharacters.raw
        KeyQueryLanguages shouldEqual QueryLanguages.raw
        KeyEnableRules shouldEqual EnableRules.raw
        KeyQueryType shouldEqual QueryType.raw
        KeyRemoveWordsIfNoResults shouldEqual RemoveWordsIfNoResults.raw
        KeyAdvancedSyntax shouldEqual AdvancedSyntax.raw
        KeyOptionalWords shouldEqual OptionalWords.raw
        KeyDisablePrefixOnAttributes shouldEqual DisablePrefixOnAttributes.raw
        KeyDisableExactOnAttributes shouldEqual DisableExactOnAttributes.raw
        KeyExactOnSingleWordQuery shouldEqual ExactOnSingleWordQuery.raw
        KeyAlternativesAsExact shouldEqual AlternativesAsExact.raw
        KeyNumericAttributesForFiltering shouldEqual NumericAttributesForFiltering.raw
        KeyAllowCompressionOfIntegerArray shouldEqual AllowCompressionOfIntegerArray.raw
        KeyAttributeForDistinct shouldEqual AttributeForDistinct.raw
        KeyDistinct shouldEqual Distinct.raw
        KeyReplaceSynonymsInHighlight shouldEqual ReplaceSynonymsInHighlight.raw
        KeyMinProximity shouldEqual MinProximity.raw
        KeyResponseFields shouldEqual ResponseFields.raw
        KeyMaxFacetHits shouldEqual MaxFacetHits.raw
    }
}