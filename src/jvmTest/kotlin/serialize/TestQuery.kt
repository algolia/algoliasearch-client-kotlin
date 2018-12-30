package serialize

import attributeA
import attributes
import boolean
import client.data.*
import client.data.Query
import client.serialize.*
import client.to
import int
import jsonAttributes
import jsonNestedLists
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import nestedLists
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import string


@RunWith(JUnit4::class)
internal class TestQuery : TestSerializer<Query>(Query, null) {

    override val item = listOf(
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
        ) to json {
            KeyQuery to string
            KeyAttributesToRetrieve to jsonAttributes
            KeyRestrictSearchableAttributes to jsonAttributes
            KeyFilters to string
            KeyFacetFilters to jsonNestedLists
            KeyOptionalFilters to jsonNestedLists
            KeyNumericFilters to jsonNestedLists
            KeyTagFilters to jsonNestedLists
            KeySumOrFiltersScores to boolean
            KeyFacets to jsonAttributes
            KeyMaxValuesPerFacet to int
            KeyFacetingAfterDistinct to boolean
            KeySortFacetValuesBy to SortFacetValuesBy.Count.raw
            KeyAttributesToHighlight to jsonAttributes
            KeyAttributesToSnippet to jsonArray { +Snippet(attributeA).raw }
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
            KeyDisableTypoToleranceOnAttributes to jsonAttributes
            KeyAroundLatLng to string
            KeyAroundLatLngViaIP to boolean
            KeyAroundRadius to AroundRadius.All.raw
            KeyAroundPrecision to int
            KeyMinimumAroundRadius to int
            KeyInsideBoundingBox to ListNumberSerializer.serializeList(listOf(BoundingBox(1f, 2f, 3f, 4f).raw))
            KeyInsidePolygon to ListNumberSerializer.serializeList(listOf(Polygon(1f to 2f, 3f to 4f, 5f to 6f).raw))
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
            KeyDisableExactOnAttributes to jsonAttributes
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
    override val items: List<Pair<List<Query>, JsonArray>> = listOf()
}