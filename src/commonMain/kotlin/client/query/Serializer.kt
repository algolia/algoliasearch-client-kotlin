package client.query


internal fun Query.toQuerySerializable() = QuerySerializable(
    // Query
    query = query,
    // Attributes
    attributesToRetrieve = attributesToRetrieve,
    restrictSearchableAttributes = restrictSearchableAttributes,
    // Filters
    filters = filters,
    facetFilters = facetFilters,
    optionalFilters = optionalFilters,
    numericFilters = numericFilters,
    tagFilters = tagFilters,
    sumOrFiltersScores = sumOrFiltersScores,
    // Facets,
    facets = facets,
    maxValuesPerFacet = maxValuesPerFacet,
    facetingAfterDistinct = facetingAfterDistinct,
    sortFacetValuesBy = sortFacetValuesBy?.raw,
    // Highlighting
    attributesToHighlight = attributesToHighlight,
    attributesToSnippet = attributesToSnippet?.map { it.raw },
    highlightPreTag = highlightPreTag,
    highlightPostTag = highlightPostTag,
    snippetEllipsisText = snippetEllipsisText,
    restrictHighlightAndSnippetArrays = restrictHighlightAndSnippetArrays,
    // Pagination
    page = page,
    hitsPerPage = hitsPerPage,
    offset = offset,
    length = length,
    //Typos
    minWordSizefor1Typo = minWordSizefor1Typo,
    minWordSizefor2Typos = minWordSizefor2Typos,
    typoTolerance = typoTolerance,
    allowTyposOnNumericTokens = allowTyposOnNumericTokens,
    disableTypoToleranceOnAttributes = disableTypoToleranceOnAttributes,
    // Geo-Search
    aroundLatLng = aroundLatLng,
    aroundLatLngViaIP = aroundLatLngViaIP,
    aroundRadius = aroundRadius,
    aroundPrecision = aroundPrecision,
    minimumAroundRadius = minimumAroundRadius,
    insideBoundingBox = insideBoundingBox,
    insidePolygon = insidePolygon,
    // Languages
    ignorePlurals = ignorePlurals,
    removeStopWords = removeStopWords,
    queryLanguages = queryLanguages?.map { it.raw },
    // Query-rules
    enableRules = enableRules,
    ruleContexts = ruleContexts,
    // Query-strategy
    queryType = queryType?.raw,
    removeWordsIfNoResults = removeWordsIfNoResults?.raw,
    advancedSyntax = advancedSyntax,
    optionalWords = optionalWords,
    disableExactOnAttributes = disableExactOnAttributes,
    exactOnSingleWordQuery = exactOnSingleWordQuery?.raw,
    alternativesAsExact = alternativesAsExact?.map { it.raw },
    // Advanced
    distinct = distinct,
    getRankingInfo = getRankingInfo,
    clickAnalytics = clickAnalytics,
    analytics = analytics,
    analyticsTags = analyticsTags,
    synonyms = synonyms,
    replaceSynonymsInHighlight = replaceSynonymsInHighlight,
    minProximity = minProximity,
    responseFields = responseFields?.map { it.raw },
    maxFacetHits = maxFacetHits,
    percentileComputation = percentileComputation
)