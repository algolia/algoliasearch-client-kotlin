package serialize

import attributeA
import attributes
import boolean
import client.data.*
import client.serialize.*
import indexA
import int
import jsonAttributes
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import string


@RunWith(JUnit4::class)
internal class TestSettings : TestSerializer<Settings>(Settings.serializer()) {

    override val item = listOf(
        Settings(
            // Attributes
            searchableAttributes = attributes,
            attributesForFaceting = attributes,
            unretrievableAttributes = attributes,
            attributesToRetrieve = attributes,
            // Ranking
            ranking = listOf(Ranking.Geo),
            customRanking = listOf(CustomRanking.Asc(attributeA)),
            replicas = listOf(indexA),
            // Faceting
            maxValuesPerFacet = int,
            sortFacetValuesBy = SortFacetValuesBy.Count,
            // Highlighting
            attributesToHighlight = attributes,
            attributesToSnippet = listOf(Snippet(attributeA)),
            highlightPreTag = string,
            highlightPostTag = string,
            snippetEllipsisText = string,
            restrictHighlightAndSnippetArrays = boolean,
            // Pagination
            hitsPerPage = int,
            paginationLimitedTo = int,
            minWordSizefor1Typo = int,
            minWordSizefor2Typos = int,
            // Typos
            typoTolerance = TypoTolerance.Min,
            allowTyposOnNumericTokens = boolean,
            disableTypoToleranceOnAttributes = attributes,
            disableTypoToleranceOnWords = listOf(string),
            separatorsToIndex = string,
            // Languages
            ignorePlurals = BooleanOrQueryLanguages.Boolean(boolean),
            removeStopWords = BooleanOrQueryLanguages.Boolean(boolean),
            camelCaseAttributes = attributes,
            decompoundedAttributes = listOf(DecompoundedAttributes(QueryLanguage.German, attributeA)),
            keepDiacriticsOnCharacters = string,
            queryLanguages = listOf(QueryLanguage.Afrikaans, QueryLanguage.Albanian),
            // Query-rules
            enableRules = boolean,
            // Query-strategy
            queryType = QueryType.PrefixLast,
            removeWordsIfNoResults = RemoveWordIfNoResults.LastWords,
            advancedSyntax = boolean,
            optionalWords = listOf(string),
            disablePrefixOnAttributes = attributes,
            disableExactOnAttributes = attributes,
            exactOnSingleWordQuery = ExactOnSingleWordQuery.Word,
            alternativesAsExact = listOf(AlternativesAsExact.IgnorePlurals),
            // Performance
            numericAttributesForFiltering = listOf(NumericAttributeFilter(attributeA)),
            allowCompressionOfIntegerArray = boolean,
            // Advanced
            attributeForDistinct = attributeA,
            distinct = int,
            replaceSynonymsInHighlight = boolean,
            minProximity = int,
            responseFields = listOf(ResponseFields.NbHits),
            maxFacetHits = int
        ) to json {
            // Attributes
            KeySearchableAttributes to jsonAttributes
            KeyAttributesForFaceting to jsonAttributes
            KeyUnretrievableAttributes to jsonAttributes
            KeyAttributesToRetrieve to jsonAttributes
            // Ranking
            KeyRanking to jsonArray { +Ranking.Geo.raw }
            KeyCustomRanking to jsonArray { +CustomRanking.Asc(attributeA).raw }
            KeyReplicas to jsonArray { +indexA.raw }
            // Faceting
            KeyMaxValuesPerFacet to int
            KeySortFacetValuesBy to SortFacetValuesBy.Count.raw
            // Highlighting
            KeyAttributesToHighlight to jsonAttributes
            KeyAttributesToSnippet to jsonArray { +Snippet(attributeA).raw }
            KeyHighlightPreTag to string
            KeyHighlightPostTag to string
            KeySnippetEllipsisText to string
            KeyRestrictHighlightAndSnippetArrays to boolean
            // Pagination
            KeyHitsPerPage to int
            KeyPaginationLimitedTo to int
            KeyMinWordSizefor1Typo to int
            KeyMinWordSizefor2Typos to int
            // Typos
            KeyTypoTolerance to TypoTolerance.Min.raw
            KeyAllowTyposOnNumericTokens to boolean
            KeyDisableTypoToleranceOnAttributes to jsonAttributes
            KeyDisableTypoToleranceOnWords to jsonArray { +string }
            KeySeparatorsToIndex to string
            // Languages
            KeyIgnorePlurals to boolean
            KeyRemoveStopWords to boolean
            KeyCamelCaseAttributes to jsonAttributes
            KeyDecompoundedAttributes to json {
                QueryLanguage.German.raw to jsonArray {
                    +attributeA.raw
                }
            }
            KeyKeepDiacriticsOnCharacters to string
            KeyQueryLanguages to jsonArray {
                +QueryLanguage.Afrikaans.raw
                +QueryLanguage.Albanian.raw
            }
            // Query-rules
            KeyEnableRules to boolean
            // Query-strategy
            KeyQueryType to QueryType.PrefixLast.raw
            KeyRemoveWordsIfNoResults to RemoveWordIfNoResults.LastWords.raw
            KeyAdvancedSyntax to boolean
            KeyOptionalWords to jsonArray { +string }
            KeyDisablePrefixOnAttributes to jsonAttributes
            KeyDisableExactOnAttributes to jsonAttributes
            KeyExactOnSingleWordQuery to ExactOnSingleWordQuery.Word.raw
            KeyAlternativesAsExact to jsonArray { +AlternativesAsExact.IgnorePlurals.raw }
            // Performance
            KeyNumericAttributesForFiltering to jsonArray { +NumericAttributeFilter(attributeA).raw }
            KeyAllowCompressionOfIntegerArray to boolean
            // Advanced
            KeyAttributeForDistinct to attributeA.raw
            KeyDistinct to int
            KeyReplaceSynonymsInHighlight to boolean
            KeyMinProximity to int
            KeyResponseFields to jsonArray { +ResponseFields.NbHits.raw }
            KeyMaxFacetHits to int
        }
    )

    @Test
    fun encodeNoNull() {
        Settings().encodeNoNulls().toString() shouldEqual "{}"
    }
}