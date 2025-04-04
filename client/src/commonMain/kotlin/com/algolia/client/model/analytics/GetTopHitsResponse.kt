/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.analytics

import com.algolia.client.exception.AlgoliaClientException
import com.algolia.client.extensions.internal.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*
import kotlin.jvm.JvmInline

/**
 * GetTopHitsResponse
 *
 * Implementations:
 * - [TopHitsResponse]
 * - [TopHitsResponseWithAnalytics]
 * - [TopHitsResponseWithRevenueAnalytics]
 */
@Serializable(GetTopHitsResponseSerializer::class)
public sealed interface GetTopHitsResponse {
  @Serializable
  @JvmInline
  public value class TopHitsResponseValue(public val value: TopHitsResponse) : GetTopHitsResponse

  @Serializable
  @JvmInline
  public value class TopHitsResponseWithAnalyticsValue(public val value: TopHitsResponseWithAnalytics) : GetTopHitsResponse

  @Serializable
  @JvmInline
  public value class TopHitsResponseWithRevenueAnalyticsValue(public val value: TopHitsResponseWithRevenueAnalytics) : GetTopHitsResponse

  public companion object {

    public fun of(value: TopHitsResponse): GetTopHitsResponse = TopHitsResponseValue(value)

    public fun of(value: TopHitsResponseWithAnalytics): GetTopHitsResponse = TopHitsResponseWithAnalyticsValue(value)

    public fun of(value: TopHitsResponseWithRevenueAnalytics): GetTopHitsResponse = TopHitsResponseWithRevenueAnalyticsValue(value)
  }
}

internal class GetTopHitsResponseSerializer : JsonContentPolymorphicSerializer<GetTopHitsResponse>(GetTopHitsResponse::class) {
  override fun selectDeserializer(element: JsonElement): DeserializationStrategy<GetTopHitsResponse> = when {
    element is JsonObject -> TopHitsResponse.serializer()
    element is JsonObject -> TopHitsResponseWithAnalytics.serializer()
    element is JsonObject -> TopHitsResponseWithRevenueAnalytics.serializer()
    else -> throw AlgoliaClientException("Failed to deserialize json element: $element")
  }
}
