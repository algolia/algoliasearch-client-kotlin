package serialize

import attributes
import attributesJson
import boolean
import com.algolia.search.model.search.*
import com.algolia.search.serialize.*
import int
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import nestedLists
import nestedListsJson
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
            attributesToSnippet = listOf(TestSnippet.snippet),
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
            insideBoundingBox = listOf(TestBoundingBox.boundingBox),
            insidePolygon = listOf(TestPolygon.polygon),
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
        ) to json {
            KeyQuery to string
            KeyAttributesToRetrieve to attributesJson
            KeyRestrictSearchableAttributes to attributesJson
            KeyFilters to string
            KeyFacetFilters to nestedListsJson
            KeyOptionalFilters to nestedListsJson
            KeyNumericFilters to nestedListsJson
            KeyTagFilters to nestedListsJson
            KeySumOrFiltersScores to boolean
            KeyFacets to attributesJson
            KeyMaxValuesPerFacet to int
            KeyFacetingAfterDistinct to boolean
            KeySortFacetValuesBy to SortFacetValuesBy.Count.raw
            KeyAttributesToHighlight to attributesJson
            KeyAttributesToSnippet to jsonArray { +TestSnippet.json }
            KeyHighlightPreTag to string
            KeyHighlightPostTag to string
            KeySnippetEllipsisText to string
            KeyRestrictHighlightAndSnippetArrays to boolean
            KeyPage to int
            KeyHitsPerPage to int
            KeyOffset to int
            KeyLength to int
            KeyMinWordSizefor1Typo to int
            KeyMinWordSizefor2Typos to int
            KeyTypoTolerance to TypoTolerance.Min.raw
            KeyAllowTyposOnNumericTokens to boolean
            KeyDisableTypoToleranceOnAttributes to attributesJson
            KeyAroundLatLng to string
            KeyAroundLatLngViaIP to boolean
            KeyAroundRadius to AroundRadius.All.raw
            KeyAroundPrecision to int
            KeyMinimumAroundRadius to int
            KeyInsideBoundingBox to jsonArray { +TestBoundingBox.json }
            KeyInsidePolygon to jsonArray { +TestPolygon.json }
            KeyIgnorePlurals to boolean
            KeyRemoveStopWords to boolean
            KeyQueryLanguages to jsonArray {
                +QueryLanguage.Afrikaans.raw
                +QueryLanguage.Albanian.raw
            }
            KeyEnableRules to boolean
            KeyRuleContexts to jsonArray { +string }
            KeyEnablePersonalization to boolean
            KeyQueryType to QueryType.PrefixLast.raw
            KeyRemoveWordsIfNoResults to RemoveWordIfNoResults.LastWords.raw
            KeyAdvancedSyntax to boolean
            KeyOptionalWords to jsonArray { +string }
            KeyDisableExactOnAttributes to attributesJson
            KeyExactOnSingleWordQuery to ExactOnSingleWordQuery.Word.raw
            KeyAlternativesAsExact to jsonArray { +AlternativesAsExact.IgnorePlurals.raw }
            KeyDistinct to int
            KeyGetRankingInfo to boolean
            KeyClickAnalytics to boolean
            KeyAnalytics to boolean
            KeyAnalyticsTags to jsonArray { +string }
            KeySynonyms to boolean
            KeyReplaceSynonymsInHighlight to boolean
            KeyMinProximity to int
            KeyResponseFields to jsonArray { +ResponseFields.NbHits.raw }
            KeyMaxFacetHits to int
            KeyPercentileComputation to boolean
        }
    )

    @Test
    fun encodeNoNull() {
        JsonNoNulls.stringify(Query.serializer(), Query()) shouldEqual "{}"
    }
}