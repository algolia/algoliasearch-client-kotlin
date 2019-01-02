package serialize

import attributeA
import attributes
import boolean
import com.algolia.search.saas.data.*
import com.algolia.search.saas.serialize.encodeNoNulls
import indexA
import int
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import string


@RunWith(JUnit4::class)
internal class TestSettings : TestSerializer<Settings>(Settings.serializer()) {

    override val items = listOf(
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
        )
    )

    @Test
    fun encodeNoNull() {
        Settings().encodeNoNulls().toString() shouldEqual "{}"
    }
}