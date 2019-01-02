package serialize

import attributeA
import attributes
import boolean
import com.algolia.search.saas.data.*
import com.algolia.search.saas.serialize.encodeNoNulls
import com.algolia.search.saas.to
import int
import nestedLists
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import string


@RunWith(JUnit4::class)
internal class TestQuery : TestSerializer<Query>(Query.serializer()) {

    override val items = listOf(
        Query(
            query = string,
            attributesToRetrieve = attributes,
            restrictSearchableAttributes = attributes,
            filters = string,
            facetFilters = nestedLists,
            optionalFilters = nestedLists,
            numericFilters = nestedLists,
            tagFilters = nestedLists,
            sumOrFiltersScores = boolean,
            facets = attributes,
            maxValuesPerFacet = int,
            facetingAfterDistinct = boolean,
            sortFacetValuesBy = SortFacetValuesBy.Count,
            attributesToHighlight = attributes,
            attributesToSnippet = listOf(Snippet(attributeA)),
            highlightPreTag = string,
            highlightPostTag = string,
            snippetEllipsisText = string,
            restrictHighlightAndSnippetArrays = boolean,
            page = int,
            hitsPerPage = int,
            offset = int,
            length = int,
            minWordSizefor1Typo = int,
            minWordSizefor2Typos = int,
            typoTolerance = TypoTolerance.Min,
            allowTyposOnNumericTokens = boolean,
            disableTypoToleranceOnAttributes = attributes,
            aroundLatLng = string,
            aroundLatLngViaIP = boolean,
            aroundRadius = AroundRadius.All,
            aroundPrecision = int,
            minimumAroundRadius = int,
            insideBoundingBox = listOf(BoundingBox(1f, 2f, 3f, 4f)),
            insidePolygon = listOf(Polygon(1f to 2f, 3f to 4f, 5f to 6f)),
            ignorePlurals = BooleanOrQueryLanguages.Boolean(boolean),
            removeStopWords = BooleanOrQueryLanguages.Boolean(boolean),
            queryLanguages = listOf(QueryLanguage.Afrikaans, QueryLanguage.Albanian),
            enableRules = boolean,
            ruleContexts = listOf(string),
            enablePersonalization = boolean,
            queryType = QueryType.PrefixLast,
            removeWordsIfNoResults = RemoveWordIfNoResults.LastWords,
            advancedSyntax = boolean,
            optionalWords = listOf(string),
            disableExactOnAttributes = attributes,
            exactOnSingleWordQuery = ExactOnSingleWordQuery.Word,
            alternativesAsExact = listOf(AlternativesAsExact.IgnorePlurals),
            distinct = int,
            getRankingInfo = boolean,
            clickAnalytics = boolean,
            analytics = boolean,
            analyticsTags = listOf(string),
            synonyms = boolean,
            replaceSynonymsInHighlight = boolean,
            minProximity = int,
            responseFields = listOf(ResponseFields.NbHits),
            maxFacetHits = int,
            percentileComputation = boolean
        )
    )

    @Test
    fun encodeNoNull() {
        Query().encodeNoNulls().toString() shouldEqual "{}"
    }
}