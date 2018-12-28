package client.serialize

import client.data.*
import client.query.Query
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.json


object QuerySerializer : Serializer<Query> {

    override fun serialize(input: Query): JsonElement {
        return input.run {
            json {
                // Query
                query?.let { KeyQuery to it }
                //Attributes
                attributesToRetrieve?.let { KeyAttributesToRetrieve to Attribute.serializes(it) }
                restrictSearchableAttributes?.let { KeyRestrictSearchableAttributes to Attribute.serializes(it) }
                // Filters
                filters?.let { KeyFilters to it }
                facetFilters?.let { KeyFacetFilters to ListStringSerializer.serializes(it) }
                optionalFilters?.let { KeyOptionalFilters to ListStringSerializer.serializes(it) }
                numericFilters?.let { KeyNumericFilters to ListStringSerializer.serializes(it) }
                tagFilters?.let { KeyTagFilters to ListStringSerializer.serializes(it) }
                sumOrFiltersScores?.let { KeySumOrFiltersScores to it }
                // Facets
                facets?.let { KeyFacets to Attribute.serializes(it) }
                maxValuesPerFacet?.let { KeyMaxValuesPerFacet to it }
                facetingAfterDistinct?.let { KeyFacetingAfterDistinct to it }
                sortFacetValuesBy?.let { KeySortFacetValuesBy to SortFacetValuesBy.serialize(it) }
                // Highlighting
                attributesToHighlight?.let { KeyAttributesToHighlight to Attribute.serializes(it) }
                attributesToSnippet?.let { KeyAttributesToSnippet to Snippet.serializes(it) }
                highlightPreTag?.let { KeyHighlightPreTag to it }
                highlightPostTag?.let { KeyHighlightPostTag to it }
                snippetEllipsisText?.let { KeySnippetEllipsisText to it }
                restrictHighlightAndSnippetArrays?.let { KeyRestrictHighlightAndSnippetArrays to it }
                // Pagination
                page?.let { KeyPage to it }
                hitsPerPage?.let { KeyHitsPerPage to it }
                offset?.let { KeyOffset to it }
                length?.let { KeyLength to it }
                //Typos
                minWordSizefor1Typo?.let { KeyMinWordSizefor1Typo to it }
                minWordSizefor2Typos?.let { KeyMinWordSizefor2Typos to it }
                typoTolerance?.let { KeyTypoTolerance to TypoTolerance.serialize(it) }
                allowTyposOnNumericTokens?.let { KeyAllowTyposOnNumericTokens to it }
                disableTypoToleranceOnAttributes?.let { KeyDisableTypoToleranceOnAttributes to Attribute.serializes(it) }
                // Geo-Search
                aroundLatLng?.let { KeyAroundLatLng to it }
                aroundLatLngViaIP?.let { KeyAroundLatLngViaIP to it }
                aroundRadius?.let { KeyAroundRadius to AroundRadius.serialize(it) }
                aroundPrecision?.let { KeyAroundPrecision to it }
                minimumAroundRadius?.let { KeyMinimumAroundRadius to it }
                insideBoundingBox?.let { KeyInsideBoundingBox to BoundingBox.serializes(it) }
                insidePolygon?.let { KeyInsidePolygon to Polygon.serializes(it) }
                // Languages
                ignorePlurals?.let { KeyIgnorePlurals to BooleanOrQueryLanguages.serialize(it) }
                removeStopWords?.let { KeyRemoveStopWords to BooleanOrQueryLanguages.serialize(it) }
                queryLanguages?.let { KeyQueryLanguages to QueryLanguage.serializes(it) }
                // Query-rules
                enableRules?.let { KeyEnableRules to it }
                ruleContexts?.let { KeyRuleContexts to ListStringSerializer.serialize(it) }
                // Query-strategy
                queryType?.let { KeyQueryType to QueryType.serialize(it) }
                removeWordsIfNoResults?.let { KeyRemoveWordsIfNoResults to RemoveWordIfNoResults.serialize(it) }
                advancedSyntax?.let { KeyAdvancedSyntax to it }
                optionalWords?.let { KeyOptionalWords to ListStringSerializer.serialize(it) }
                disableExactOnAttributes?.let { KeyDisableExactOnAttributes to Attribute.serializes(it) }
                exactOnSingleWordQuery?.let { KeyExactOnSingleWordQuery to ExactOnSingleWordQuery.serialize(it) }
                alternativesAsExact?.let { KeyAlternativesAsExact to AlternativesAsExact.serializes(it) }
                // Advanced
                distinct?.let { KeyDistinct to it }
                getRankingInfo?.let { KeyGetRankingInfo to it }
                clickAnalytics?.let { KeyClickAnalytics to it }
                analytics?.let { KeyAnalytics to it }
                analyticsTags?.let { KeyAnalyticsTags to ListStringSerializer.serialize(it) }
                synonyms?.let { KeySynonyms to it }
                replaceSynonymsInHighlight?.let { KeyReplaceSynonymsInHighlight to it }
                minProximity?.let { KeyMinProximity to it }
                responseFields?.let { KeyResponseFields to ResponseFields.serializes(it) }
                maxFacetHits?.let { KeyMaxFacetHits to it }
                percentileComputation?.let { KeyPercentileComputation to it }
            }
        }
    }
}