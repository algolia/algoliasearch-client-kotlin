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
import com.algolia.search.serialize.JsonNoDefaults
import com.algolia.search.serialize.KeyAdvancedSyntax
import com.algolia.search.serialize.KeyAdvancedSyntaxFeatures
import com.algolia.search.serialize.KeyAllowTyposOnNumericTokens
import com.algolia.search.serialize.KeyAlternativesAsExact
import com.algolia.search.serialize.KeyAnalytics
import com.algolia.search.serialize.KeyAnalyticsTags
import com.algolia.search.serialize.KeyAroundLatLng
import com.algolia.search.serialize.KeyAroundLatLngViaIP
import com.algolia.search.serialize.KeyAroundPrecision
import com.algolia.search.serialize.KeyAroundRadius
import com.algolia.search.serialize.KeyAttributesToHighlight
import com.algolia.search.serialize.KeyAttributesToRetrieve
import com.algolia.search.serialize.KeyAttributesToSnippet
import com.algolia.search.serialize.KeyClickAnalytics
import com.algolia.search.serialize.KeyDisableExactOnAttributes
import com.algolia.search.serialize.KeyDisableTypoToleranceOnAttributes
import com.algolia.search.serialize.KeyDistinct
import com.algolia.search.serialize.KeyEnableABTest
import com.algolia.search.serialize.KeyEnablePersonalization
import com.algolia.search.serialize.KeyEnableRules
import com.algolia.search.serialize.KeyExactOnSingleWordQuery
import com.algolia.search.serialize.KeyExplain
import com.algolia.search.serialize.KeyFacetFilters
import com.algolia.search.serialize.KeyFacetingAfterDistinct
import com.algolia.search.serialize.KeyFacets
import com.algolia.search.serialize.KeyFilters
import com.algolia.search.serialize.KeyGetRankingInfo
import com.algolia.search.serialize.KeyHighlightPostTag
import com.algolia.search.serialize.KeyHighlightPreTag
import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyIgnorePlurals
import com.algolia.search.serialize.KeyInsideBoundingBox
import com.algolia.search.serialize.KeyInsidePolygon
import com.algolia.search.serialize.KeyLength
import com.algolia.search.serialize.KeyMaxFacetHits
import com.algolia.search.serialize.KeyMaxValuesPerFacet
import com.algolia.search.serialize.KeyMinProximity
import com.algolia.search.serialize.KeyMinWordSizeFor1Typo
import com.algolia.search.serialize.KeyMinWordSizeFor2Typos
import com.algolia.search.serialize.KeyMinimumAroundRadius
import com.algolia.search.serialize.KeyNumericFilters
import com.algolia.search.serialize.KeyOffset
import com.algolia.search.serialize.KeyOptionalFilters
import com.algolia.search.serialize.KeyOptionalWords
import com.algolia.search.serialize.KeyPage
import com.algolia.search.serialize.KeyPercentileComputation
import com.algolia.search.serialize.KeyPersonalizationImpact
import com.algolia.search.serialize.KeyQuery
import com.algolia.search.serialize.KeyQueryLanguages
import com.algolia.search.serialize.KeyQueryType
import com.algolia.search.serialize.KeyRemoveStopWords
import com.algolia.search.serialize.KeyRemoveWordsIfNoResults
import com.algolia.search.serialize.KeyReplaceSynonymsInHighlight
import com.algolia.search.serialize.KeyResponseFields
import com.algolia.search.serialize.KeyRestrictHighlightAndSnippetArrays
import com.algolia.search.serialize.KeyRestrictSearchableAttributes
import com.algolia.search.serialize.KeyRuleContexts
import com.algolia.search.serialize.KeySimilarQuery
import com.algolia.search.serialize.KeySnippetEllipsisText
import com.algolia.search.serialize.KeySortFacetValuesBy
import com.algolia.search.serialize.KeySumOrFiltersScores
import com.algolia.search.serialize.KeySynonyms
import com.algolia.search.serialize.KeyTagFilters
import com.algolia.search.serialize.KeyTypoTolerance
import com.algolia.search.serialize.KeyUserToken
import int
import kotlin.test.Test
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import nestedLists
import nestedListsJson
import serialize.TestSerializer
import shouldEqual
import string
import unknown

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
            explainModules = listOf(ExplainModule.MatchAlternatives)
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
            KeySortFacetValuesBy to SortFacetsBy.Count.raw
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
            KeyMinWordSizeFor1Typo to int
            KeyMinWordSizeFor2Typos to int
            KeyTypoTolerance to TypoTolerance.Min.raw
            KeyAllowTyposOnNumericTokens to boolean
            KeyDisableTypoToleranceOnAttributes to attributesJson
            KeyAroundLatLng to "0.0,0.0"
            KeyAroundLatLngViaIP to boolean
            KeyAroundRadius to AroundRadius.All.raw
            KeyAroundPrecision to int
            KeyMinimumAroundRadius to int
            KeyInsideBoundingBox to jsonArray { +TestBoundingBox.json }
            KeyInsidePolygon to jsonArray { +TestPolygon.json }
            KeyIgnorePlurals to boolean
            KeyRemoveStopWords to boolean
            KeyQueryLanguages to jsonArray {
                +Language.Afrikaans.raw
                +Language.Albanian.raw
            }
            KeyEnableRules to boolean
            KeyRuleContexts to jsonArray { +string }
            KeyEnablePersonalization to boolean
            KeyPersonalizationImpact to 1
            KeyUserToken to unknown
            KeyQueryType to QueryType.PrefixLast.raw
            KeyRemoveWordsIfNoResults to RemoveWordIfNoResults.LastWords.raw
            KeyAdvancedSyntax to boolean
            KeyAdvancedSyntaxFeatures to jsonArray { +AdvancedSyntaxFeatures.ExcludeWords.raw }
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
            KeySimilarQuery to string
            KeyEnableABTest to boolean
            KeyExplain to jsonArray { +ExplainModule.MatchAlternatives.raw }
        }
    )

    @Test
    fun encodeNoNull() {
        JsonNoDefaults.stringify(Query.serializer(), Query()) shouldEqual "{}"
    }
}
