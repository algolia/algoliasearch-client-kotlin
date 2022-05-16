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
import com.algolia.search.serialize.internal.KeyAdvancedSyntax
import com.algolia.search.serialize.internal.KeyAdvancedSyntaxFeatures
import com.algolia.search.serialize.internal.KeyAllowTyposOnNumericTokens
import com.algolia.search.serialize.internal.KeyAlternativesAsExact
import com.algolia.search.serialize.internal.KeyAnalytics
import com.algolia.search.serialize.internal.KeyAnalyticsTags
import com.algolia.search.serialize.internal.KeyAroundLatLng
import com.algolia.search.serialize.internal.KeyAroundLatLngViaIP
import com.algolia.search.serialize.internal.KeyAroundPrecision
import com.algolia.search.serialize.internal.KeyAroundRadius
import com.algolia.search.serialize.internal.KeyAttributesToHighlight
import com.algolia.search.serialize.internal.KeyAttributesToRetrieve
import com.algolia.search.serialize.internal.KeyAttributesToSnippet
import com.algolia.search.serialize.internal.KeyClickAnalytics
import com.algolia.search.serialize.internal.KeyDecompoundQuery
import com.algolia.search.serialize.internal.KeyDisableExactOnAttributes
import com.algolia.search.serialize.internal.KeyDisableTypoToleranceOnAttributes
import com.algolia.search.serialize.internal.KeyDistinct
import com.algolia.search.serialize.internal.KeyEnableABTest
import com.algolia.search.serialize.internal.KeyEnablePersonalization
import com.algolia.search.serialize.internal.KeyEnableReRanking
import com.algolia.search.serialize.internal.KeyEnableRules
import com.algolia.search.serialize.internal.KeyExactOnSingleWordQuery
import com.algolia.search.serialize.internal.KeyExplain
import com.algolia.search.serialize.internal.KeyFacetFilters
import com.algolia.search.serialize.internal.KeyFacetingAfterDistinct
import com.algolia.search.serialize.internal.KeyFacets
import com.algolia.search.serialize.internal.KeyFilters
import com.algolia.search.serialize.internal.KeyGetRankingInfo
import com.algolia.search.serialize.internal.KeyHighlightPostTag
import com.algolia.search.serialize.internal.KeyHighlightPreTag
import com.algolia.search.serialize.internal.KeyHitsPerPage
import com.algolia.search.serialize.internal.KeyIgnorePlurals
import com.algolia.search.serialize.internal.KeyInsideBoundingBox
import com.algolia.search.serialize.internal.KeyInsidePolygon
import com.algolia.search.serialize.internal.KeyLength
import com.algolia.search.serialize.internal.KeyMaxFacetHits
import com.algolia.search.serialize.internal.KeyMaxValuesPerFacet
import com.algolia.search.serialize.internal.KeyMinProximity
import com.algolia.search.serialize.internal.KeyMinWordSizeFor1Typo
import com.algolia.search.serialize.internal.KeyMinWordSizeFor2Typos
import com.algolia.search.serialize.internal.KeyMinimumAroundRadius
import com.algolia.search.serialize.internal.KeyNaturalLanguages
import com.algolia.search.serialize.internal.KeyNumericFilters
import com.algolia.search.serialize.internal.KeyOffset
import com.algolia.search.serialize.internal.KeyOptionalFilters
import com.algolia.search.serialize.internal.KeyOptionalWords
import com.algolia.search.serialize.internal.KeyPage
import com.algolia.search.serialize.internal.KeyPercentileComputation
import com.algolia.search.serialize.internal.KeyPersonalizationImpact
import com.algolia.search.serialize.internal.KeyQuery
import com.algolia.search.serialize.internal.KeyQueryLanguages
import com.algolia.search.serialize.internal.KeyQueryType
import com.algolia.search.serialize.internal.KeyRelevancyStrictness
import com.algolia.search.serialize.internal.KeyRemoveStopWords
import com.algolia.search.serialize.internal.KeyRemoveWordsIfNoResults
import com.algolia.search.serialize.internal.KeyReplaceSynonymsInHighlight
import com.algolia.search.serialize.internal.KeyResponseFields
import com.algolia.search.serialize.internal.KeyRestrictHighlightAndSnippetArrays
import com.algolia.search.serialize.internal.KeyRestrictSearchableAttributes
import com.algolia.search.serialize.internal.KeyRuleContexts
import com.algolia.search.serialize.internal.KeySimilarQuery
import com.algolia.search.serialize.internal.KeySnippetEllipsisText
import com.algolia.search.serialize.internal.KeySortFacetValuesBy
import com.algolia.search.serialize.internal.KeySumOrFiltersScores
import com.algolia.search.serialize.internal.KeySynonyms
import com.algolia.search.serialize.internal.KeyTagFilters
import com.algolia.search.serialize.internal.KeyTypoTolerance
import com.algolia.search.serialize.internal.KeyUserToken
import com.algolia.search.serialize.internal.JsonNoDefaults
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
            put(KeyQuery, string)
            put(KeyAttributesToRetrieve, attributesJson)
            put(KeyRestrictSearchableAttributes, attributesJson)
            put(KeyFilters, string)
            put(KeyFacetFilters, nestedListsJson)
            put(KeyOptionalFilters, nestedListsJson)
            put(KeyNumericFilters, nestedListsJson)
            put(KeyTagFilters, nestedListsJson)
            put(KeySumOrFiltersScores, boolean)
            put(KeyFacets, attributesJson)
            put(KeyMaxValuesPerFacet, int)
            put(KeyFacetingAfterDistinct, boolean)
            put(KeySortFacetValuesBy, SortFacetsBy.Count.raw)
            put(KeyAttributesToHighlight, attributesJson)
            put(KeyAttributesToSnippet, buildJsonArray { add(TestSnippet.json) })
            put(KeyHighlightPreTag, string)
            put(KeyHighlightPostTag, string)
            put(KeySnippetEllipsisText, string)
            put(KeyRestrictHighlightAndSnippetArrays, boolean)
            put(KeyPage, int)
            put(KeyHitsPerPage, int)
            put(KeyOffset, int)
            put(KeyLength, int)
            put(KeyMinWordSizeFor1Typo, int)
            put(KeyMinWordSizeFor2Typos, int)
            put(KeyTypoTolerance, TypoTolerance.Min.raw)
            put(KeyAllowTyposOnNumericTokens, boolean)
            put(KeyDisableTypoToleranceOnAttributes, attributesJson)
            put(KeyAroundLatLng, "0.0,0.0")
            put(KeyAroundLatLngViaIP, boolean)
            put(KeyAroundRadius, AroundRadius.All.raw)
            put(KeyAroundPrecision, int)
            put(KeyMinimumAroundRadius, int)
            put(KeyInsideBoundingBox, buildJsonArray { add(TestBoundingBox.json) })
            put(KeyInsidePolygon, buildJsonArray { add(TestPolygon.json) })
            put(KeyIgnorePlurals, boolean)
            put(KeyRemoveStopWords, boolean)
            put(
                KeyQueryLanguages,
                buildJsonArray {
                    add(Language.Afrikaans.raw)
                    add(Language.Albanian.raw)
                }
            )
            put(KeyEnableRules, boolean)
            put(KeyRuleContexts, buildJsonArray { add(string) })
            put(KeyEnablePersonalization, boolean)
            put(KeyPersonalizationImpact, 1)
            put(KeyUserToken, unknown)
            put(KeyQueryType, QueryType.PrefixLast.raw)
            put(KeyRemoveWordsIfNoResults, RemoveWordIfNoResults.LastWords.raw)
            put(KeyAdvancedSyntax, boolean)
            put(
                KeyAdvancedSyntaxFeatures,
                buildJsonArray { add(AdvancedSyntaxFeatures.ExcludeWords.raw) }
            )
            put(KeyOptionalWords, buildJsonArray { add(string) })
            put(KeyDisableExactOnAttributes, attributesJson)
            put(KeyExactOnSingleWordQuery, ExactOnSingleWordQuery.Word.raw)
            put(
                KeyAlternativesAsExact,
                buildJsonArray {
                    add(
                        AlternativesAsExact.IgnorePlurals.raw
                    )
                }
            )
            put(KeyDistinct, int)
            put(KeyGetRankingInfo, boolean)
            put(KeyClickAnalytics, boolean)
            put(KeyAnalytics, boolean)
            put(KeyAnalyticsTags, buildJsonArray { add(string) })
            put(KeySynonyms, boolean)
            put(KeyReplaceSynonymsInHighlight, boolean)
            put(KeyMinProximity, int)
            put(KeyResponseFields, buildJsonArray { add(ResponseFields.NbHits.raw) })
            put(KeyMaxFacetHits, int)
            put(KeyPercentileComputation, boolean)
            put(KeySimilarQuery, string)
            put(KeyEnableABTest, boolean)
            put(KeyExplain, buildJsonArray { add(ExplainModule.MatchAlternatives.raw) })
            put(
                KeyNaturalLanguages,
                buildJsonArray {
                    add(Language.Afrikaans.raw)
                    add(Language.Albanian.raw)
                }
            )
            put(KeyRelevancyStrictness, int)
            put(KeyDecompoundQuery, boolean)
            put(KeyEnableReRanking, boolean)
        }
    )

    @Test
    fun encodeNoNull() {
        JsonNoDefaults.encodeToString(Query.serializer(), Query()) shouldEqual "{}"
    }
}
