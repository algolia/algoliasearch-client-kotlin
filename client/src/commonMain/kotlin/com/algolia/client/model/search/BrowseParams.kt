/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.search

import com.algolia.client.exception.AlgoliaClientException
import com.algolia.client.extensions.internal.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*

/**
 * BrowseParams
 */
@Serializable(BrowseParamsSerializer::class)
public sealed interface BrowseParams {

  public companion object {

    /**
     * BrowseParamsObject
     *
     * @param query Text to search for in an index.
     * @param similarQuery Overrides the query parameter and performs a more generic search.
     * @param filters [Filter](https://www.algolia.com/doc/guides/managing-results/refine-results/filtering/) the query with numeric, facet, or tag filters.
     * @param facetFilters
     * @param optionalFilters
     * @param numericFilters
     * @param tagFilters
     * @param sumOrFiltersScores Determines how to calculate [filter scores](https://www.algolia.com/doc/guides/managing-results/refine-results/filtering/in-depth/filter-scoring/#accumulating-scores-with-sumorfiltersscores). If `false`, maximum score is kept. If `true`, score is summed.
     * @param restrictSearchableAttributes Restricts a query to only look at a subset of your [searchable attributes](https://www.algolia.com/doc/guides/managing-results/must-do/searchable-attributes/).
     * @param facets Returns [facets](https://www.algolia.com/doc/guides/managing-results/refine-results/faceting/#contextual-facet-values-and-counts), their facet values, and the number of matching facet values.
     * @param facetingAfterDistinct Forces faceting to be applied after [de-duplication](https://www.algolia.com/doc/guides/managing-results/refine-results/grouping/) (with the distinct feature). Alternatively, the `afterDistinct` [modifier](https://www.algolia.com/doc/api-reference/api-parameters/attributesForFaceting/#modifiers) of `attributesForFaceting` allows for more granular control.
     * @param page Page to retrieve (the first page is `0`, not `1`).
     * @param offset Specifies the offset of the first hit to return. > **Note**: Using `page` and `hitsPerPage` is the recommended method for [paging results](https://www.algolia.com/doc/guides/building-search-ui/ui-and-ux-patterns/pagination/js/). However, you can use `offset` and `length` to implement [an alternative approach to paging](https://www.algolia.com/doc/guides/building-search-ui/ui-and-ux-patterns/pagination/js/#retrieving-a-subset-of-records-with-offset-and-length).
     * @param length Sets the number of hits to retrieve (for use with `offset`). > **Note**: Using `page` and `hitsPerPage` is the recommended method for [paging results](https://www.algolia.com/doc/guides/building-search-ui/ui-and-ux-patterns/pagination/js/). However, you can use `offset` and `length` to implement [an alternative approach to paging](https://www.algolia.com/doc/guides/building-search-ui/ui-and-ux-patterns/pagination/js/#retrieving-a-subset-of-records-with-offset-and-length).
     * @param aroundLatLng Search for entries [around a central location](https://www.algolia.com/doc/guides/managing-results/refine-results/geolocation/#filter-around-a-central-point), enabling a geographical search within a circular area.
     * @param aroundLatLngViaIP Search for entries around a location. The location is automatically computed from the requester's IP address.
     * @param aroundRadius
     * @param aroundPrecision
     * @param minimumAroundRadius Minimum radius (in meters) used for a geographical search when `aroundRadius` isn't set.
     * @param insideBoundingBox Search inside a [rectangular area](https://www.algolia.com/doc/guides/managing-results/refine-results/geolocation/#filtering-inside-rectangular-or-polygonal-areas) (in geographical coordinates).
     * @param insidePolygon Search inside a [polygon](https://www.algolia.com/doc/guides/managing-results/refine-results/geolocation/#filtering-inside-rectangular-or-polygonal-areas) (in geographical coordinates).
     * @param naturalLanguages Changes the default values of parameters that work best for a natural language query, such as `ignorePlurals`, `removeStopWords`, `removeWordsIfNoResults`, `analyticsTags`, and `ruleContexts`. These parameters work well together when the query consists of fuller natural language strings instead of keywords, for example when processing voice search queries.
     * @param ruleContexts Assigns [rule contexts](https://www.algolia.com/doc/guides/managing-results/rules/rules-overview/how-to/customize-search-results-by-platform/#whats-a-context) to search queries.
     * @param personalizationImpact Defines how much [Personalization affects results](https://www.algolia.com/doc/guides/personalization/personalizing-results/in-depth/configuring-personalization/#understanding-personalization-impact).
     * @param userToken Associates a [user token](https://www.algolia.com/doc/guides/sending-events/concepts/usertoken/) with the current search.
     * @param getRankingInfo Incidates whether the search response includes [detailed ranking information](https://www.algolia.com/doc/guides/building-search-ui/going-further/backend-search/in-depth/understanding-the-api-response/#ranking-information).
     * @param explain Enriches the API's response with information about how the query was processed.
     * @param synonyms Whether to take into account an index's synonyms for a particular search.
     * @param clickAnalytics Indicates whether a query ID parameter is included in the search response. This is required for [tracking click and conversion events](https://www.algolia.com/doc/guides/sending-events/concepts/event-types/#events-related-to-algolia-requests).
     * @param analytics Indicates whether this query will be included in [analytics](https://www.algolia.com/doc/guides/search-analytics/guides/exclude-queries/).
     * @param analyticsTags Tags to apply to the query for [segmenting analytics data](https://www.algolia.com/doc/guides/search-analytics/guides/segments/).
     * @param percentileComputation Whether to include or exclude a query from the processing-time percentile computation.
     * @param enableABTest Incidates whether this search will be considered in A/B testing.
     * @param attributesForFaceting Attributes used for [faceting](https://www.algolia.com/doc/guides/managing-results/refine-results/faceting/) and the [modifiers](https://www.algolia.com/doc/api-reference/api-parameters/attributesForFaceting/#modifiers) that can be applied: `filterOnly`, `searchable`, and `afterDistinct`.
     * @param attributesToRetrieve Attributes to include in the API response. To reduce the size of your response, you can retrieve only some of the attributes. By default, the response includes all attributes.
     * @param ranking Determines the order in which Algolia [returns your results](https://www.algolia.com/doc/guides/managing-results/relevance-overview/in-depth/ranking-criteria/).
     * @param customRanking Specifies the [Custom ranking criterion](https://www.algolia.com/doc/guides/managing-results/must-do/custom-ranking/). Use the `asc` and `desc` modifiers to specify the ranking order: ascending or descending.
     * @param relevancyStrictness Relevancy threshold below which less relevant results aren't included in the results.
     * @param attributesToHighlight Attributes to highlight. Strings that match the search query in the attributes are highlighted by surrounding them with HTML tags (`highlightPreTag` and `highlightPostTag`).
     * @param attributesToSnippet Attributes to _snippet_. 'Snippeting' is shortening the attribute to a certain number of words. If not specified, the attribute is shortened to the 10 words around the matching string but you can specify the number. For example: `body:20`.
     * @param highlightPreTag HTML string to insert before the highlighted parts in all highlight and snippet results.
     * @param highlightPostTag HTML string to insert after the highlighted parts in all highlight and snippet results.
     * @param snippetEllipsisText String used as an ellipsis indicator when a snippet is truncated.
     * @param restrictHighlightAndSnippetArrays Restrict highlighting and snippeting to items that matched the query.
     * @param hitsPerPage Number of hits per page.
     * @param minWordSizefor1Typo Minimum number of characters a word in the query string must contain to accept matches with [one typo](https://www.algolia.com/doc/guides/managing-results/optimize-search-results/typo-tolerance/in-depth/configuring-typo-tolerance/#configuring-word-length-for-typos).
     * @param minWordSizefor2Typos Minimum number of characters a word in the query string must contain to accept matches with [two typos](https://www.algolia.com/doc/guides/managing-results/optimize-search-results/typo-tolerance/in-depth/configuring-typo-tolerance/#configuring-word-length-for-typos).
     * @param typoTolerance
     * @param allowTyposOnNumericTokens Whether to allow typos on numbers (\"numeric tokens\") in the query string.
     * @param disableTypoToleranceOnAttributes Attributes for which you want to turn off [typo tolerance](https://www.algolia.com/doc/guides/managing-results/optimize-search-results/typo-tolerance/).
     * @param ignorePlurals
     * @param removeStopWords
     * @param keepDiacriticsOnCharacters Characters that the engine shouldn't automatically [normalize](https://www.algolia.com/doc/guides/managing-results/optimize-search-results/handling-natural-languages-nlp/in-depth/normalization/).
     * @param queryLanguages Sets your user's search language. This adjusts language-specific settings and features such as `ignorePlurals`, `removeStopWords`, and [CJK](https://www.algolia.com/doc/guides/managing-results/optimize-search-results/handling-natural-languages-nlp/in-depth/normalization/#normalization-for-logogram-based-languages-cjk) word detection.
     * @param decompoundQuery [Splits compound words](https://www.algolia.com/doc/guides/managing-results/optimize-search-results/handling-natural-languages-nlp/in-depth/language-specific-configurations/#splitting-compound-words) into their component word parts in the query.
     * @param enableRules Incidates whether [Rules](https://www.algolia.com/doc/guides/managing-results/rules/rules-overview/) are enabled.
     * @param enablePersonalization Incidates whether [Personalization](https://www.algolia.com/doc/guides/personalization/what-is-personalization/) is enabled.
     * @param queryType
     * @param removeWordsIfNoResults
     * @param mode
     * @param semanticSearch
     * @param advancedSyntax Enables the [advanced query syntax](https://www.algolia.com/doc/guides/managing-results/optimize-search-results/override-search-engine-defaults/#advanced-syntax).
     * @param optionalWords Words which should be considered [optional](https://www.algolia.com/doc/guides/managing-results/optimize-search-results/empty-or-insufficient-results/#creating-a-list-of-optional-words) when found in a query.
     * @param disableExactOnAttributes Attributes for which you want to [turn off the exact ranking criterion](https://www.algolia.com/doc/guides/managing-results/optimize-search-results/override-search-engine-defaults/in-depth/adjust-exact-settings/#turn-off-exact-for-some-attributes).
     * @param exactOnSingleWordQuery
     * @param alternativesAsExact Alternatives that should be considered an exact match by [the exact ranking criterion](https://www.algolia.com/doc/guides/managing-results/optimize-search-results/override-search-engine-defaults/in-depth/adjust-exact-settings/#turn-off-exact-for-some-attributes).
     * @param advancedSyntaxFeatures Allows you to specify which advanced syntax features are active when `advancedSyntax` is enabled.
     * @param distinct
     * @param replaceSynonymsInHighlight Whether to highlight and snippet the original word that matches the synonym or the synonym itself.
     * @param minProximity Precision of the [proximity ranking criterion](https://www.algolia.com/doc/guides/managing-results/relevance-overview/in-depth/ranking-criteria/#proximity).
     * @param responseFields Attributes to include in the API response for search and browse queries.
     * @param maxFacetHits Maximum number of facet hits to return when [searching for facet values](https://www.algolia.com/doc/guides/managing-results/refine-results/faceting/#search-for-facet-values).
     * @param maxValuesPerFacet Maximum number of facet values to return for each facet.
     * @param sortFacetValuesBy Controls how facet values are fetched.
     * @param attributeCriteriaComputedByMinProximity When the [Attribute criterion is ranked above Proximity](https://www.algolia.com/doc/guides/managing-results/relevance-overview/in-depth/ranking-criteria/#attribute-and-proximity-combinations) in your ranking formula, Proximity is used to select which searchable attribute is matched in the Attribute ranking stage.
     * @param renderingContent
     * @param enableReRanking Indicates whether this search will use [Dynamic Re-Ranking](https://www.algolia.com/doc/guides/algolia-ai/re-ranking/).
     * @param reRankingApplyFilter
     * @param cursor Cursor indicating the location to resume browsing from. Must match the value returned by the previous call. Pass this value to the subsequent browse call to get the next page of results. When the end of the index has been reached, `cursor` is absent from the response.
     */
    public fun BrowseParamsObject(
      query: String? = null,
      similarQuery: String? = null,
      filters: String? = null,
      facetFilters: FacetFilters? = null,
      optionalFilters: OptionalFilters? = null,
      numericFilters: NumericFilters? = null,
      tagFilters: TagFilters? = null,
      sumOrFiltersScores: Boolean? = null,
      restrictSearchableAttributes: List<String>? = null,
      facets: List<String>? = null,
      facetingAfterDistinct: Boolean? = null,
      page: Int? = null,
      offset: Int? = null,
      length: Int? = null,
      aroundLatLng: String? = null,
      aroundLatLngViaIP: Boolean? = null,
      aroundRadius: AroundRadius? = null,
      aroundPrecision: AroundPrecision? = null,
      minimumAroundRadius: Int? = null,
      insideBoundingBox: List<Double>? = null,
      insidePolygon: List<Double>? = null,
      naturalLanguages: List<String>? = null,
      ruleContexts: List<String>? = null,
      personalizationImpact: Int? = null,
      userToken: String? = null,
      getRankingInfo: Boolean? = null,
      explain: List<String>? = null,
      synonyms: Boolean? = null,
      clickAnalytics: Boolean? = null,
      analytics: Boolean? = null,
      analyticsTags: List<String>? = null,
      percentileComputation: Boolean? = null,
      enableABTest: Boolean? = null,
      attributesForFaceting: List<String>? = null,
      attributesToRetrieve: List<String>? = null,
      ranking: List<String>? = null,
      customRanking: List<String>? = null,
      relevancyStrictness: Int? = null,
      attributesToHighlight: List<String>? = null,
      attributesToSnippet: List<String>? = null,
      highlightPreTag: String? = null,
      highlightPostTag: String? = null,
      snippetEllipsisText: String? = null,
      restrictHighlightAndSnippetArrays: Boolean? = null,
      hitsPerPage: Int? = null,
      minWordSizefor1Typo: Int? = null,
      minWordSizefor2Typos: Int? = null,
      typoTolerance: TypoTolerance? = null,
      allowTyposOnNumericTokens: Boolean? = null,
      disableTypoToleranceOnAttributes: List<String>? = null,
      ignorePlurals: IgnorePlurals? = null,
      removeStopWords: RemoveStopWords? = null,
      keepDiacriticsOnCharacters: String? = null,
      queryLanguages: List<String>? = null,
      decompoundQuery: Boolean? = null,
      enableRules: Boolean? = null,
      enablePersonalization: Boolean? = null,
      queryType: QueryType? = null,
      removeWordsIfNoResults: RemoveWordsIfNoResults? = null,
      mode: Mode? = null,
      semanticSearch: SemanticSearch? = null,
      advancedSyntax: Boolean? = null,
      optionalWords: List<String>? = null,
      disableExactOnAttributes: List<String>? = null,
      exactOnSingleWordQuery: ExactOnSingleWordQuery? = null,
      alternativesAsExact: List<AlternativesAsExact>? = null,
      advancedSyntaxFeatures: List<AdvancedSyntaxFeatures>? = null,
      distinct: Distinct? = null,
      replaceSynonymsInHighlight: Boolean? = null,
      minProximity: Int? = null,
      responseFields: List<String>? = null,
      maxFacetHits: Int? = null,
      maxValuesPerFacet: Int? = null,
      sortFacetValuesBy: String? = null,
      attributeCriteriaComputedByMinProximity: Boolean? = null,
      renderingContent: RenderingContent? = null,
      enableReRanking: Boolean? = null,
      reRankingApplyFilter: ReRankingApplyFilter? = null,
      cursor: String? = null,
    ): BrowseParamsObject = com.algolia.client.model.search.BrowseParamsObject(
      query = query,
      similarQuery = similarQuery,
      filters = filters,
      facetFilters = facetFilters,
      optionalFilters = optionalFilters,
      numericFilters = numericFilters,
      tagFilters = tagFilters,
      sumOrFiltersScores = sumOrFiltersScores,
      restrictSearchableAttributes = restrictSearchableAttributes,
      facets = facets,
      facetingAfterDistinct = facetingAfterDistinct,
      page = page,
      offset = offset,
      length = length,
      aroundLatLng = aroundLatLng,
      aroundLatLngViaIP = aroundLatLngViaIP,
      aroundRadius = aroundRadius,
      aroundPrecision = aroundPrecision,
      minimumAroundRadius = minimumAroundRadius,
      insideBoundingBox = insideBoundingBox,
      insidePolygon = insidePolygon,
      naturalLanguages = naturalLanguages,
      ruleContexts = ruleContexts,
      personalizationImpact = personalizationImpact,
      userToken = userToken,
      getRankingInfo = getRankingInfo,
      explain = explain,
      synonyms = synonyms,
      clickAnalytics = clickAnalytics,
      analytics = analytics,
      analyticsTags = analyticsTags,
      percentileComputation = percentileComputation,
      enableABTest = enableABTest,
      attributesForFaceting = attributesForFaceting,
      attributesToRetrieve = attributesToRetrieve,
      ranking = ranking,
      customRanking = customRanking,
      relevancyStrictness = relevancyStrictness,
      attributesToHighlight = attributesToHighlight,
      attributesToSnippet = attributesToSnippet,
      highlightPreTag = highlightPreTag,
      highlightPostTag = highlightPostTag,
      snippetEllipsisText = snippetEllipsisText,
      restrictHighlightAndSnippetArrays = restrictHighlightAndSnippetArrays,
      hitsPerPage = hitsPerPage,
      minWordSizefor1Typo = minWordSizefor1Typo,
      minWordSizefor2Typos = minWordSizefor2Typos,
      typoTolerance = typoTolerance,
      allowTyposOnNumericTokens = allowTyposOnNumericTokens,
      disableTypoToleranceOnAttributes = disableTypoToleranceOnAttributes,
      ignorePlurals = ignorePlurals,
      removeStopWords = removeStopWords,
      keepDiacriticsOnCharacters = keepDiacriticsOnCharacters,
      queryLanguages = queryLanguages,
      decompoundQuery = decompoundQuery,
      enableRules = enableRules,
      enablePersonalization = enablePersonalization,
      queryType = queryType,
      removeWordsIfNoResults = removeWordsIfNoResults,
      mode = mode,
      semanticSearch = semanticSearch,
      advancedSyntax = advancedSyntax,
      optionalWords = optionalWords,
      disableExactOnAttributes = disableExactOnAttributes,
      exactOnSingleWordQuery = exactOnSingleWordQuery,
      alternativesAsExact = alternativesAsExact,
      advancedSyntaxFeatures = advancedSyntaxFeatures,
      distinct = distinct,
      replaceSynonymsInHighlight = replaceSynonymsInHighlight,
      minProximity = minProximity,
      responseFields = responseFields,
      maxFacetHits = maxFacetHits,
      maxValuesPerFacet = maxValuesPerFacet,
      sortFacetValuesBy = sortFacetValuesBy,
      attributeCriteriaComputedByMinProximity = attributeCriteriaComputedByMinProximity,
      renderingContent = renderingContent,
      enableReRanking = enableReRanking,
      reRankingApplyFilter = reRankingApplyFilter,
      cursor = cursor,
    )

    /**
     * SearchParamsString
     *
     * @param params Search parameters as a URL-encoded query string.
     */
    public fun SearchParamsString(
      params: String? = null,
    ): SearchParamsString = com.algolia.client.model.search.SearchParamsString(
      params = params,
    )
  }
}

internal class BrowseParamsSerializer : KSerializer<BrowseParams> {

  override val descriptor: SerialDescriptor = buildClassSerialDescriptor("BrowseParams")

  override fun serialize(encoder: Encoder, value: BrowseParams) {
    when (value) {
      is SearchParamsString -> SearchParamsString.serializer().serialize(encoder, value)
      is BrowseParamsObject -> BrowseParamsObject.serializer().serialize(encoder, value)
    }
  }

  override fun deserialize(decoder: Decoder): BrowseParams {
    val codec = decoder.asJsonDecoder()
    val tree = codec.decodeJsonElement()

    // deserialize SearchParamsString
    if (tree is JsonObject && tree.containsKey("params")) {
      try {
        return codec.json.decodeFromJsonElement<SearchParamsString>(tree)
      } catch (e: Exception) {
        // deserialization failed, continue
        println("Failed to deserialize SearchParamsString (error: ${e.message})")
      }
    }

    // deserialize BrowseParamsObject
    if (tree is JsonObject) {
      try {
        return codec.json.decodeFromJsonElement<BrowseParamsObject>(tree)
      } catch (e: Exception) {
        // deserialization failed, continue
        println("Failed to deserialize BrowseParamsObject (error: ${e.message})")
      }
    }

    throw AlgoliaClientException("Failed to deserialize json element: $tree")
  }
}
