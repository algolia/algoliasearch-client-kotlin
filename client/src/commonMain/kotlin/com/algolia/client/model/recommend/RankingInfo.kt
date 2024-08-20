/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.recommend

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * Object with detailed information about the record's ranking.
 *
 * @param firstMatchedWord Position of the first matched word in the best matching attribute of the record.
 * @param geoDistance Distance between the geo location in the search query and the best matching geo location in the record, divided by the geo precision (in meters).
 * @param nbExactWords Number of exactly matched words.
 * @param nbTypos Number of typos encountered when matching the record.
 * @param userScore Overall ranking of the record, expressed as a single integer. This attribute is internal.
 * @param filters Whether a filter matched the query.
 * @param geoPrecision Precision used when computing the geo distance, in meters.
 * @param matchedGeoLocation
 * @param personalization
 * @param promoted Whether the record was promoted by a rule.
 * @param proximityDistance Number of words between multiple matches in the query plus 1. For single word queries, `proximityDistance` is 0.
 * @param words Number of matched words.
 * @param promotedByReRanking Whether the record is re-ranked.
 */
@Serializable
public data class RankingInfo(

  /** Position of the first matched word in the best matching attribute of the record. */
  @SerialName(value = "firstMatchedWord") val firstMatchedWord: Int,

  /** Distance between the geo location in the search query and the best matching geo location in the record, divided by the geo precision (in meters). */
  @SerialName(value = "geoDistance") val geoDistance: Int,

  /** Number of exactly matched words. */
  @SerialName(value = "nbExactWords") val nbExactWords: Int,

  /** Number of typos encountered when matching the record. */
  @SerialName(value = "nbTypos") val nbTypos: Int,

  /** Overall ranking of the record, expressed as a single integer. This attribute is internal. */
  @SerialName(value = "userScore") val userScore: Int,

  /** Whether a filter matched the query. */
  @SerialName(value = "filters") val filters: Int? = null,

  /** Precision used when computing the geo distance, in meters. */
  @SerialName(value = "geoPrecision") val geoPrecision: Int? = null,

  @SerialName(value = "matchedGeoLocation") val matchedGeoLocation: MatchedGeoLocation? = null,

  @SerialName(value = "personalization") val personalization: Personalization? = null,

  /** Whether the record was promoted by a rule. */
  @SerialName(value = "promoted") val promoted: Boolean? = null,

  /** Number of words between multiple matches in the query plus 1. For single word queries, `proximityDistance` is 0. */
  @SerialName(value = "proximityDistance") val proximityDistance: Int? = null,

  /** Number of matched words. */
  @SerialName(value = "words") val words: Int? = null,

  /** Whether the record is re-ranked. */
  @SerialName(value = "promotedByReRanking") val promotedByReRanking: Boolean? = null,
)