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
 * SearchResult
 */
@Serializable(SearchResultSerializer::class)
public sealed interface SearchResult {

  public companion object {

    /**
     * SearchForFacetValuesResponse
     *
     * @param facetHits
     */
    public fun SearchForFacetValuesResponse(
      facetHits: List<FacetHits>,
    ): SearchForFacetValuesResponse = com.algolia.client.model.search.SearchForFacetValuesResponse(
      facetHits = facetHits,
    )

    /**
     * SearchResponse
     *
     * @param hitsPerPage Number of hits per page.
     * @param nbHits Number of hits the search query matched.
     * @param nbPages Number of pages of results for the current query.
     * @param page Page to retrieve (the first page is `0`, not `1`).
     * @param processingTimeMS Time the server took to process the request, in milliseconds.
     * @param hits
     * @param query Text to search for in an index.
     * @param params URL-encoded string of all search parameters.
     * @param abTestID A/B test ID. This is only included in the response for indices that are part of an A/B test.
     * @param abTestVariantID Variant ID. This is only included in the response for indices that are part of an A/B test.
     * @param aroundLatLng Computed geographical location.
     * @param automaticRadius Automatically-computed radius.
     * @param exhaustiveFacetsCount Indicates whether the facet count is exhaustive (exact) or approximate.
     * @param exhaustiveNbHits Indicates whether the number of hits `nbHits` is exhaustive (exact) or approximate.
     * @param exhaustiveTypo Indicates whether the search for typos was exhaustive (exact) or approximate.
     * @param facets Mapping of each facet name to the corresponding facet counts.
     * @param facetsStats Statistics for numerical facets.
     * @param index Index name used for the query.
     * @param indexUsed Index name used for the query. During A/B testing, the targeted index isn't always the index used by the query.
     * @param message Warnings about the query.
     * @param nbSortedHits Number of hits selected and sorted by the relevant sort algorithm.
     * @param redirect
     * @param parsedQuery Post-[normalization](https://www.algolia.com/doc/guides/managing-results/optimize-search-results/handling-natural-languages-nlp/#what-does-normalization-mean) query string that will be searched.
     * @param queryAfterRemoval Markup text indicating which parts of the original query have been removed to retrieve a non-empty result set.
     * @param serverUsed Host name of the server that processed the request.
     * @param userData Lets you store custom data in your indices.
     * @param renderingContent
     */
    public fun SearchResponse(
      hitsPerPage: Int,
      nbHits: Int,
      nbPages: Int,
      page: Int,
      processingTimeMS: Int,
      hits: List<Hit>,
      query: String,
      params: String,
      abTestID: Int? = null,
      abTestVariantID: Int? = null,
      aroundLatLng: String? = null,
      automaticRadius: String? = null,
      exhaustiveFacetsCount: Boolean? = null,
      exhaustiveNbHits: Boolean? = null,
      exhaustiveTypo: Boolean? = null,
      facets: Map<kotlin.String, Map<kotlin.String, Int>>? = null,
      facetsStats: Map<kotlin.String, FacetsStats>? = null,
      index: String? = null,
      indexUsed: String? = null,
      message: String? = null,
      nbSortedHits: Int? = null,
      redirect: BaseSearchResponseRedirect? = null,
      parsedQuery: String? = null,
      queryAfterRemoval: String? = null,
      serverUsed: String? = null,
      userData: JsonElement? = null,
      renderingContent: RenderingContent? = null,
    ): SearchResponse = com.algolia.client.model.search.SearchResponse(
      hitsPerPage = hitsPerPage,
      nbHits = nbHits,
      nbPages = nbPages,
      page = page,
      processingTimeMS = processingTimeMS,
      hits = hits,
      query = query,
      params = params,
      abTestID = abTestID,
      abTestVariantID = abTestVariantID,
      aroundLatLng = aroundLatLng,
      automaticRadius = automaticRadius,
      exhaustiveFacetsCount = exhaustiveFacetsCount,
      exhaustiveNbHits = exhaustiveNbHits,
      exhaustiveTypo = exhaustiveTypo,
      facets = facets,
      facetsStats = facetsStats,
      index = index,
      indexUsed = indexUsed,
      message = message,
      nbSortedHits = nbSortedHits,
      redirect = redirect,
      parsedQuery = parsedQuery,
      queryAfterRemoval = queryAfterRemoval,
      serverUsed = serverUsed,
      userData = userData,
      renderingContent = renderingContent,
    )
  }
}

internal class SearchResultSerializer : KSerializer<SearchResult> {

  override val descriptor: SerialDescriptor = buildClassSerialDescriptor("SearchResult")

  override fun serialize(encoder: Encoder, value: SearchResult) {
    when (value) {
      is SearchForFacetValuesResponse -> SearchForFacetValuesResponse.serializer().serialize(encoder, value)
      is SearchResponse -> SearchResponse.serializer().serialize(encoder, value)
    }
  }

  override fun deserialize(decoder: Decoder): SearchResult {
    val codec = decoder.asJsonDecoder()
    val tree = codec.decodeJsonElement()

    // deserialize SearchForFacetValuesResponse
    if (tree is JsonObject && tree.containsKey("facetHits")) {
      try {
        return codec.json.decodeFromJsonElement<SearchForFacetValuesResponse>(tree)
      } catch (e: Exception) {
        // deserialization failed, continue
        println("Failed to deserialize SearchForFacetValuesResponse (error: ${e.message})")
      }
    }

    // deserialize SearchResponse
    if (tree is JsonObject) {
      try {
        return codec.json.decodeFromJsonElement<SearchResponse>(tree)
      } catch (e: Exception) {
        // deserialization failed, continue
        println("Failed to deserialize SearchResponse (error: ${e.message})")
      }
    }

    throw AlgoliaClientException("Failed to deserialize json element: $tree")
  }
}
