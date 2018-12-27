package client.serialize

import client.data.AroundRadius
import client.data.TypoTolerance
import client.query.Query
import client.query.helper.names


internal fun Query.toMap(): MutableMap<String, Any> {
    val map = mutableMapOf<String, Any>()

    // Query
    query?.let { map[Query] = it }
    //Attributes
    attributesToRetrieve?.let { map[AttributesToRetrieve] = it.names.toJsonArrayFromString() }
    restrictSearchableAttributes?.let { map[RestrictSearchableAttributes] = it.names.toJsonArrayFromString() }
    // Filters
    filters?.let { map[Filters] = it }
    facetFilters?.let { map[FacetFilters] = it.toJsonArrayFromList() }
    optionalFilters?.let { map[OptionalFilters] = it.toJsonArrayFromList() }
    numericFilters?.let { map[NumericFilters] = it.toJsonArrayFromList() }
    tagFilters?.let { map[TagFilters] = it.toJsonArrayFromList() }
    sumOrFiltersScores?.let { map[SumOrFiltersScores] = it }
    // Facets
    facets?.let { map[Facets] = it.names.toJsonArrayFromString() }
    maxValuesPerFacet?.let { map[MaxValuesPerFacet] = it }
    facetingAfterDistinct?.let { map[FacetingAfterDistinct] = it }
    sortFacetValuesBy?.let { map[SortFacetValuesBy] = it.raw }
    // Highlighting
    attributesToHighlight?.let { map[AttributesToHighlight] = it.names.toJsonArrayFromString() }
    attributesToSnippet?.let { map[AttributesToSnippet] = it.map { it.raw }.toJsonArrayFromString() }
    highlightPreTag?.let { map[HighlightPreTag] = it }
    highlightPostTag?.let { map[HighlightPostTag] = it }
    snippetEllipsisText?.let { map[SnippetEllipsisText] = it }
    restrictHighlightAndSnippetArrays?.let { map[RestrictHighlightAndSnippetArrays] = it }
    // Pagination
    page?.let { map[Page] = it }
    hitsPerPage?.let { map[HitsPerPage] = it }
    offset?.let { map[Offset] = it }
    length?.let { map[Length] = it }
    //Typos
    minWordSizefor1Typo?.let { map[MinWordSizefor1Typo] = it }
    minWordSizefor2Typos?.let { map[MinWordSizefor2Typos] = it }
    typoTolerance?.let {
        map[TypoTolerance] = when (it) {
            is TypoTolerance.Boolean -> it.boolean
            is TypoTolerance.Min -> it.raw
            is TypoTolerance.Strict -> it.raw
            is TypoTolerance.Unknown -> it.raw
        }
    }
    allowTyposOnNumericTokens?.let { map[AllowTyposOnNumericTokens] = it }
    disableTypoToleranceOnAttributes?.let { map[DisableTypoToleranceOnAttributes] = it.names.toJsonArrayFromString() }
    // Geo-Search
    aroundLatLng?.let { map[AroundLatLng] = it }
    aroundLatLngViaIP?.let { map[AroundLatLngViaIP] = it }
    aroundRadius?.let {
        map[AroundRadius] = when (it) {
            is AroundRadius.All -> it.raw
            is AroundRadius.InMeters -> it.int
            is AroundRadius.Unknown -> it.raw
        }
    }
    aroundPrecision?.let { map[AroundPrecision] = it }
    minimumAroundRadius?.let { map[MinimumAroundRadius] = it }
    insideBoundingBox?.let { map[InsideBoundingBox] = it.flatMap { it.floats }.toJsonArrayFromFloat() }
    insidePolygon?.let { map[InsidePolygon] = it.flatMap { it.floats }.toJsonArrayFromFloat() }
    // Languages
    ignorePlurals?.let { map[IgnorePlurals] = it.toPrimitive() }
    removeStopWords?.let { map[RemoveStopWords] = it.toPrimitive() }
    queryLanguages?.let { map[QueryLanguages] = it.map { it.raw }.toJsonArrayFromString() }
    // Query-rules
    enableRules?.let { map[EnableRules] = it }
    ruleContexts?.let { map[RuleContexts] = it.toJsonArrayFromString() }
    // Query-strategy
    queryType?.let { map[QueryType] = it.raw }
    removeWordsIfNoResults?.let { map[RemoveWordsIfNoResults] = it.raw }
    advancedSyntax?.let { map[AdvancedSyntax] = it }
    optionalWords?.let { map[OptionalWords] = it.toJsonArrayFromString() }
    disableExactOnAttributes?.let { map[DisableExactOnAttributes] = it.names.toJsonArrayFromString() }
    exactOnSingleWordQuery?.let { map[ExactOnSingleWordQuery] = it.raw }
    alternativesAsExact?.let { map[AlternativesAsExact] = it.map { it.raw }.toJsonArrayFromString() }
    // Advanced
    distinct?.let { map[Distinct] = it }
    getRankingInfo?.let { map[GetRankingInfo] = it }
    clickAnalytics?.let { map[ClickAnalytics] = it }
    analytics?.let { map[Analytics] = it }
    analyticsTags?.let { map[AnalyticsTags] = it.toJsonArrayFromString() }
    synonyms?.let { map[Synonyms] = it }
    replaceSynonymsInHighlight?.let { map[ReplaceSynonymsInHighlight] = it }
    minProximity?.let { map[MinProximity] = it }
    responseFields?.let { map[ResponseFields] = it.map { it.raw }.toJsonArrayFromString() }
    maxFacetHits?.let { map[MaxFacetHits] = it }
    percentileComputation?.let { map[PercentileComputation] = it }
    return map
}