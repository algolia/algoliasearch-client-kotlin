package client.serialize

import client.data.AroundRadius
import client.data.TypoTolerance
import client.query.Query
import client.query.helper.names


internal fun Query.toMap(): MutableMap<String, Any> {
    val map = mutableMapOf<String, Any>()

    // Query
    query?.let { map[KeyQuery] = it }
    //Attributes
    attributesToRetrieve?.let { map[KeyAttributesToRetrieve] = it.names.toJsonArrayFromString() }
    restrictSearchableAttributes?.let { map[KeyRestrictSearchableAttributes] = it.names.toJsonArrayFromString() }
    // Filters
    filters?.let { map[KeyFilters] = it }
    facetFilters?.let { map[KeyFacetFilters] = it.toJsonArrayFromList() }
    optionalFilters?.let { map[KeyOptionalFilters] = it.toJsonArrayFromList() }
    numericFilters?.let { map[KeyNumericFilters] = it.toJsonArrayFromList() }
    tagFilters?.let { map[KeyTagFilters] = it.toJsonArrayFromList() }
    sumOrFiltersScores?.let { map[KeySumOrFiltersScores] = it }
    // Facets
    facets?.let { map[KeyFacets] = it.names.toJsonArrayFromString() }
    maxValuesPerFacet?.let { map[KeyMaxValuesPerFacet] = it }
    facetingAfterDistinct?.let { map[KeyFacetingAfterDistinct] = it }
    sortFacetValuesBy?.let { map[KeySortFacetValuesBy] = it.raw }
    // Highlighting
    attributesToHighlight?.let { map[KeyAttributesToHighlight] = it.names.toJsonArrayFromString() }
    attributesToSnippet?.let { map[KeyAttributesToSnippet] = it.map { it.raw }.toJsonArrayFromString() }
    highlightPreTag?.let { map[KeyHighlightPreTag] = it }
    highlightPostTag?.let { map[KeyHighlightPostTag] = it }
    snippetEllipsisText?.let { map[KeySnippetEllipsisText] = it }
    restrictHighlightAndSnippetArrays?.let { map[KeyRestrictHighlightAndSnippetArrays] = it }
    // Pagination
    page?.let { map[KeyPage] = it }
    hitsPerPage?.let { map[KeyHitsPerPage] = it }
    offset?.let { map[KeyOffset] = it }
    length?.let { map[KeyLength] = it }
    //Typos
    minWordSizefor1Typo?.let { map[KeyMinWordSizefor1Typo] = it }
    minWordSizefor2Typos?.let { map[KeyMinWordSizefor2Typos] = it }
    typoTolerance?.let {
        map[KeyTypoTolerance] = when (it) {
            is TypoTolerance.Boolean -> it.boolean
            is TypoTolerance.Min -> it.raw
            is TypoTolerance.Strict -> it.raw
            is TypoTolerance.Unknown -> it.raw
        }
    }
    allowTyposOnNumericTokens?.let { map[KeyAllowTyposOnNumericTokens] = it }
    disableTypoToleranceOnAttributes?.let { map[KeyDisableTypoToleranceOnAttributes] = it.names.toJsonArrayFromString() }
    // Geo-Search
    aroundLatLng?.let { map[KeyAroundLatLng] = it }
    aroundLatLngViaIP?.let { map[KeyAroundLatLngViaIP] = it }
    aroundRadius?.let {
        map[KeyAroundRadius] = when (it) {
            is AroundRadius.All -> it.raw
            is AroundRadius.InMeters -> it.int
            is AroundRadius.Unknown -> it.raw
        }
    }
    aroundPrecision?.let { map[KeyAroundPrecision] = it }
    minimumAroundRadius?.let { map[KeyMinimumAroundRadius] = it }
    insideBoundingBox?.let { map[KeyInsideBoundingBox] = it.flatMap { it.floats }.toJsonArrayFromFloat() }
    insidePolygon?.let { map[KeyInsidePolygon] = it.flatMap { it.floats }.toJsonArrayFromFloat() }
    // Languages
    ignorePlurals?.let { map[KeyIgnorePlurals] = it.toPrimitive() }
    removeStopWords?.let { map[KeyRemoveStopWords] = it.toPrimitive() }
    queryLanguages?.let { map[KeyQueryLanguages] = it.map { it.raw }.toJsonArrayFromString() }
    // Query-rules
    enableRules?.let { map[KeyEnableRules] = it }
    ruleContexts?.let { map[KeyRuleContexts] = it.toJsonArrayFromString() }
    // Query-strategy
    queryType?.let { map[KeyQueryType] = it.raw }
    removeWordsIfNoResults?.let { map[KeyRemoveWordsIfNoResults] = it.raw }
    advancedSyntax?.let { map[KeyAdvancedSyntax] = it }
    optionalWords?.let { map[KeyOptionalWords] = it.toJsonArrayFromString() }
    disableExactOnAttributes?.let { map[KeyDisableExactOnAttributes] = it.names.toJsonArrayFromString() }
    exactOnSingleWordQuery?.let { map[KeyExactOnSingleWordQuery] = it.raw }
    alternativesAsExact?.let { map[KeyAlternativesAsExact] = it.map { it.raw }.toJsonArrayFromString() }
    // Advanced
    distinct?.let { map[KeyDistinct] = it }
    getRankingInfo?.let { map[KeyGetRankingInfo] = it }
    clickAnalytics?.let { map[KeyClickAnalytics] = it }
    analytics?.let { map[KeyAnalytics] = it }
    analyticsTags?.let { map[KeyAnalyticsTags] = it.toJsonArrayFromString() }
    synonyms?.let { map[KeySynonyms] = it }
    replaceSynonymsInHighlight?.let { map[KeyReplaceSynonymsInHighlight] = it }
    minProximity?.let { map[KeyMinProximity] = it }
    responseFields?.let { map[KeyResponseFields] = it.map { it.raw }.toJsonArrayFromString() }
    maxFacetHits?.let { map[KeyMaxFacetHits] = it }
    percentileComputation?.let { map[KeyPercentileComputation] = it }
    return map
}