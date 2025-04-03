/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.composition

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * SearchResultsItem
 *
 * @param processingTimeMS Time the server took to process the request, in milliseconds.
 * @param page Page of search results to retrieve.
 * @param nbHits Number of results (hits).
 * @param nbPages Number of pages of results.
 * @param hitsPerPage Number of hits per page.
 * @param hits Search results (hits).  Hits are records from your index that match the search criteria, augmented with additional attributes, such as, for highlighting.
 * @param query Search query.
 * @param params URL-encoded string of all search parameters.
 * @param compositions
 * @param abTestID A/B test ID. This is only included in the response for indices that are part of an A/B test.
 * @param abTestVariantID Variant ID. This is only included in the response for indices that are part of an A/B test.
 * @param aroundLatLng Computed geographical location.
 * @param automaticRadius Distance from a central coordinate provided by `aroundLatLng`.
 * @param exhaustive
 * @param appliedRules Rules applied to the query.
 * @param exhaustiveFacetsCount See the `facetsCount` field of the `exhaustive` object in the response.
 * @param exhaustiveNbHits See the `nbHits` field of the `exhaustive` object in the response.
 * @param exhaustiveTypo See the `typo` field of the `exhaustive` object in the response.
 * @param facets Facet counts.
 * @param facetsStats Statistics for numerical facets.
 * @param index Index name used for the query.
 * @param indexUsed Index name used for the query. During A/B testing, the targeted index isn't always the index used by the query.
 * @param message Warnings about the query.
 * @param nbSortedHits Number of hits selected and sorted by the relevant sort algorithm.
 * @param parsedQuery Post-[normalization](https://www.algolia.com/doc/guides/managing-results/optimize-search-results/handling-natural-languages-nlp/#what-does-normalization-mean) query string that will be searched.
 * @param processingTimingsMS Experimental. List of processing steps and their times, in milliseconds. You can use this list to investigate performance issues.
 * @param queryAfterRemoval Markup text indicating which parts of the original query have been removed to retrieve a non-empty result set.
 * @param redirect
 * @param renderingContent
 * @param serverTimeMS Time the server took to process the request, in milliseconds.
 * @param serverUsed Host name of the server that processed the request.
 * @param userData An object with custom data.  You can store up to 32kB as custom data.
 * @param queryID Unique identifier for the query. This is used for [click analytics](https://www.algolia.com/doc/guides/analytics/click-analytics/).
 * @param automaticInsights Whether automatic events collection is enabled for the application.
 */
@Serializable
public data class SearchResultsItem(

  /** Time the server took to process the request, in milliseconds. */
  @SerialName(value = "processingTimeMS") val processingTimeMS: Int,

  /** Page of search results to retrieve. */
  @SerialName(value = "page") val page: Int,

  /** Number of results (hits). */
  @SerialName(value = "nbHits") val nbHits: Int,

  /** Number of pages of results. */
  @SerialName(value = "nbPages") val nbPages: Int,

  /** Number of hits per page. */
  @SerialName(value = "hitsPerPage") val hitsPerPage: Int,

  /** Search results (hits).  Hits are records from your index that match the search criteria, augmented with additional attributes, such as, for highlighting.  */
  @SerialName(value = "hits") val hits: List<Hit>,

  /** Search query. */
  @SerialName(value = "query") val query: String,

  /** URL-encoded string of all search parameters. */
  @SerialName(value = "params") val params: String,

  @SerialName(value = "compositions") val compositions: Map<kotlin.String, ResultsCompositionInfoResponse>,

  /** A/B test ID. This is only included in the response for indices that are part of an A/B test. */
  @SerialName(value = "abTestID") val abTestID: Int? = null,

  /** Variant ID. This is only included in the response for indices that are part of an A/B test. */
  @SerialName(value = "abTestVariantID") val abTestVariantID: Int? = null,

  /** Computed geographical location. */
  @SerialName(value = "aroundLatLng") val aroundLatLng: String? = null,

  /** Distance from a central coordinate provided by `aroundLatLng`. */
  @SerialName(value = "automaticRadius") val automaticRadius: String? = null,

  @SerialName(value = "exhaustive") val exhaustive: Exhaustive? = null,

  /** Rules applied to the query. */
  @SerialName(value = "appliedRules") val appliedRules: List<JsonObject>? = null,

  /** See the `facetsCount` field of the `exhaustive` object in the response. */
  @Deprecated(message = "This property is deprecated.")
  @SerialName(value = "exhaustiveFacetsCount") val exhaustiveFacetsCount: Boolean? = null,

  /** See the `nbHits` field of the `exhaustive` object in the response. */
  @Deprecated(message = "This property is deprecated.")
  @SerialName(value = "exhaustiveNbHits") val exhaustiveNbHits: Boolean? = null,

  /** See the `typo` field of the `exhaustive` object in the response. */
  @Deprecated(message = "This property is deprecated.")
  @SerialName(value = "exhaustiveTypo") val exhaustiveTypo: Boolean? = null,

  /** Facet counts. */
  @SerialName(value = "facets") val facets: Map<kotlin.String, Map<kotlin.String, Int>>? = null,

  /** Statistics for numerical facets. */
  @SerialName(value = "facets_stats") val facetsStats: Map<kotlin.String, FacetStats>? = null,

  /** Index name used for the query. */
  @SerialName(value = "index") val index: String? = null,

  /** Index name used for the query. During A/B testing, the targeted index isn't always the index used by the query. */
  @SerialName(value = "indexUsed") val indexUsed: String? = null,

  /** Warnings about the query. */
  @SerialName(value = "message") val message: String? = null,

  /** Number of hits selected and sorted by the relevant sort algorithm. */
  @SerialName(value = "nbSortedHits") val nbSortedHits: Int? = null,

  /** Post-[normalization](https://www.algolia.com/doc/guides/managing-results/optimize-search-results/handling-natural-languages-nlp/#what-does-normalization-mean) query string that will be searched. */
  @SerialName(value = "parsedQuery") val parsedQuery: String? = null,

  /** Experimental. List of processing steps and their times, in milliseconds. You can use this list to investigate performance issues. */
  @SerialName(value = "processingTimingsMS") val processingTimingsMS: JsonObject? = null,

  /** Markup text indicating which parts of the original query have been removed to retrieve a non-empty result set. */
  @SerialName(value = "queryAfterRemoval") val queryAfterRemoval: String? = null,

  @SerialName(value = "redirect") val redirect: Redirect? = null,

  @SerialName(value = "renderingContent") val renderingContent: RenderingContent? = null,

  /** Time the server took to process the request, in milliseconds. */
  @SerialName(value = "serverTimeMS") val serverTimeMS: Int? = null,

  /** Host name of the server that processed the request. */
  @SerialName(value = "serverUsed") val serverUsed: String? = null,

  /** An object with custom data.  You can store up to 32kB as custom data.  */
  @SerialName(value = "userData") val userData: JsonElement? = null,

  /** Unique identifier for the query. This is used for [click analytics](https://www.algolia.com/doc/guides/analytics/click-analytics/). */
  @SerialName(value = "queryID") val queryID: String? = null,

  /** Whether automatic events collection is enabled for the application. */
  @SerialName(value = "_automaticInsights") val automaticInsights: Boolean? = null,
)
