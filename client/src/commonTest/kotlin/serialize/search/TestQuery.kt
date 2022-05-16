package serialize.search

import attributes
import attributesJson
import boolean
import com.algolia.search.helper.and
import com.algolia.search.model.insights.UserToken
import com.algolia.search.model.search.AlternativesAsExact
import com.algolia.search.model.search.AroundPrecision
import com.algolia.search.model.search.AroundRadius
import com.algolia.search.model.search.ExactOnSingleWordQuery
import com.algolia.search.model.search.ExplainModule
import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.Language
import com.algolia.search.model.search.Query
import com.algolia.search.model.search.QueryType
import com.algolia.search.model.search.RemoveStopWords
import com.algolia.search.model.search.RemoveWordIfNoResults
import com.algolia.search.model.search.ResponseFields
import com.algolia.search.model.search.SortFacetsBy
import com.algolia.search.model.search.TypoTolerance
import com.algolia.search.model.settings.AdvancedSyntaxFeatures
import com.algolia.search.model.settings.Distinct
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.Key
import int
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import nestedLists
import nestedListsJson
import serialize.TestSerializer
import shouldEqual
import string
import unknown
import kotlin.test.Test

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
            facets = attributes.toSet(),
            maxValuesPerFacet = int,
            facetingAfterDistinct = boolean,
            sortFacetsBy = SortFacetsBy.Count,
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
            minWordSizeFor1Typo = int,
            minWordSizeFor2Typos = int,
            typoTolerance = TypoTolerance.Min,
            allowTyposOnNumericTokens = boolean,
            disableTypoToleranceOnAttributes = attributes,
            aroundLatLng = 0.0f and 0.0f,
            aroundLatLngViaIP = boolean,
            aroundRadius = AroundRadius.All,
            aroundPrecision = AroundPrecision.Int(int),
            minimumAroundRadius = int,
            insideBoundingBox = listOf(TestBoundingBox.boundingBox),
            insidePolygon = listOf(TestPolygon.polygon),
            ignorePlurals = IgnorePlurals.True,
            removeStopWords = RemoveStopWords.True,
            queryLanguages = listOf(Language.Afrikaans, Language.Albanian),
            enableRules = boolean,
            ruleContexts = listOf(string),
            enablePersonalization = boolean,
            personalizationImpact = 1,
            userToken = UserToken(unknown),
            queryType = QueryType.PrefixLast,
            removeWordsIfNoResults = RemoveWordIfNoResults.LastWords,
            advancedSyntax = boolean,
            advancedSyntaxFeatures = listOf(AdvancedSyntaxFeatures.ExcludeWords),
            optionalWords = listOf(string),
            disableExactOnAttributes = attributes,
            exactOnSingleWordQuery = ExactOnSingleWordQuery.Word,
            alternativesAsExact = listOf(AlternativesAsExact.IgnorePlurals),
            distinct = Distinct(int),
            getRankingInfo = boolean,
            clickAnalytics = boolean,
            analytics = boolean,
            analyticsTags = listOf(string),
            synonyms = boolean,
            replaceSynonymsInHighlight = boolean,
            minProximity = int,
            responseFields = listOf(ResponseFields.NbHits),
            maxFacetHits = int,
            percentileComputation = boolean,
            similarQuery = string,
            enableABTest = boolean,
            explainModules = listOf(ExplainModule.MatchAlternatives),
            naturalLanguages = listOf(Language.Afrikaans, Language.Albanian),
            relevancyStrictness = int,
            decompoundQuery = boolean,
            enableReRanking = boolean,
        ) to buildJsonObject {
            put(Key.Query, string)
            put(Key.AttributesToRetrieve, attributesJson)
            put(Key.RestrictSearchableAttributes, attributesJson)
            put(Key.Filters, string)
            put(Key.FacetFilters, nestedListsJson)
            put(Key.OptionalFilters, nestedListsJson)
            put(Key.NumericFilters, nestedListsJson)
            put(Key.TagFilters, nestedListsJson)
            put(Key.SumOrFiltersScores, boolean)
            put(Key.Facets, attributesJson)
            put(Key.MaxValuesPerFacet, int)
            put(Key.FacetingAfterDistinct, boolean)
            put(Key.SortFacetValuesBy, SortFacetsBy.Count.raw)
            put(Key.AttributesToHighlight, attributesJson)
            put(Key.AttributesToSnippet, buildJsonArray { add(TestSnippet.json) })
            put(Key.HighlightPreTag, string)
            put(Key.HighlightPostTag, string)
            put(Key.SnippetEllipsisText, string)
            put(Key.RestrictHighlightAndSnippetArrays, boolean)
            put(Key.Page, int)
            put(Key.HitsPerPage, int)
            put(Key.Offset, int)
            put(Key.Length, int)
            put(Key.MinWordSizeFor1Typo, int)
            put(Key.MinWordSizeFor2Typos, int)
            put(Key.TypoTolerance, TypoTolerance.Min.raw)
            put(Key.AllowTyposOnNumericTokens, boolean)
            put(Key.DisableTypoToleranceOnAttributes, attributesJson)
            put(Key.AroundLatLng, "0.0,0.0")
            put(Key.AroundLatLngViaIP, boolean)
            put(Key.AroundRadius, AroundRadius.All.raw)
            put(Key.AroundPrecision, int)
            put(Key.MinimumAroundRadius, int)
            put(Key.InsideBoundingBox, buildJsonArray { add(TestBoundingBox.json) })
            put(Key.InsidePolygon, buildJsonArray { add(TestPolygon.json) })
            put(Key.IgnorePlurals, boolean)
            put(Key.RemoveStopWords, boolean)
            put(
                Key.QueryLanguages,
                buildJsonArray {
                    add(Language.Afrikaans.raw)
                    add(Language.Albanian.raw)
                }
            )
            put(Key.EnableRules, boolean)
            put(Key.RuleContexts, buildJsonArray { add(string) })
            put(Key.EnablePersonalization, boolean)
            put(Key.PersonalizationImpact, 1)
            put(Key.UserToken, unknown)
            put(Key.QueryType, QueryType.PrefixLast.raw)
            put(Key.RemoveWordsIfNoResults, RemoveWordIfNoResults.LastWords.raw)
            put(Key.AdvancedSyntax, boolean)
            put(
                Key.AdvancedSyntaxFeatures,
                buildJsonArray { add(AdvancedSyntaxFeatures.ExcludeWords.raw) }
            )
            put(Key.OptionalWords, buildJsonArray { add(string) })
            put(Key.DisableExactOnAttributes, attributesJson)
            put(Key.ExactOnSingleWordQuery, ExactOnSingleWordQuery.Word.raw)
            put(
                Key.AlternativesAsExact,
                buildJsonArray {
                    add(
                        AlternativesAsExact.IgnorePlurals.raw
                    )
                }
            )
            put(Key.Distinct, int)
            put(Key.GetRankingInfo, boolean)
            put(Key.ClickAnalytics, boolean)
            put(Key.Analytics, boolean)
            put(Key.AnalyticsTags, buildJsonArray { add(string) })
            put(Key.Synonyms, boolean)
            put(Key.ReplaceSynonymsInHighlight, boolean)
            put(Key.MinProximity, int)
            put(Key.ResponseFields, buildJsonArray { add(ResponseFields.NbHits.raw) })
            put(Key.MaxFacetHits, int)
            put(Key.PercentileComputation, boolean)
            put(Key.SimilarQuery, string)
            put(Key.EnableABTest, boolean)
            put(Key.Explain, buildJsonArray { add(ExplainModule.MatchAlternatives.raw) })
            put(
                Key.NaturalLanguages,
                buildJsonArray {
                    add(Language.Afrikaans.raw)
                    add(Language.Albanian.raw)
                }
            )
            put(Key.RelevancyStrictness, int)
            put(Key.DecompoundQuery, boolean)
            put(Key.EnableReRanking, boolean)
        }
    )

    @Test
    fun encodeNoNull() {
        JsonNoDefaults.encodeToString(Query.serializer(), Query()) shouldEqual "{}"
    }
}
