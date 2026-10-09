@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl

import com.algolia.client.api.SearchClient
import com.algolia.client.dsl.deleteBy as buildDeleteBy
import com.algolia.client.model.search.SearchParams
import com.algolia.client.model.search.SearchResponse
import com.algolia.client.model.search.UpdatedAtResponse
import com.algolia.client.transport.RequestOptions

/** Searches a single index with a [query] DSL block. */
@AlgoliaExperimentalDsl
public suspend fun SearchClient.searchSingleIndex(
  indexName: String,
  requestOptions: RequestOptions? = null,
  block: DSLQuery.() -> Unit,
): SearchResponse =
  searchSingleIndex(
    indexName = indexName,
    searchParams = SearchParams.of(query(block = block)),
    requestOptions = requestOptions,
  )

/** Updates index settings with a [settings] DSL block. */
@AlgoliaExperimentalDsl
public suspend fun SearchClient.setSettings(
  indexName: String,
  forwardToReplicas: Boolean? = null,
  requestOptions: RequestOptions? = null,
  block: DSLSettings.() -> Unit,
): UpdatedAtResponse =
  setSettings(
    indexName = indexName,
    indexSettings = settings(block),
    forwardToReplicas = forwardToReplicas,
    requestOptions = requestOptions,
  )

/**
 * Deletes records that match a [deleteBy] DSL block. Throws [IllegalArgumentException] before any
 * request when a filter block or group adds no filter, or when there is no filter and no geo
 * condition.
 */
@AlgoliaExperimentalDsl
public suspend fun SearchClient.deleteBy(
  indexName: String,
  requestOptions: RequestOptions? = null,
  block: DSLDeleteBy.() -> Unit,
): UpdatedAtResponse =
  deleteBy(
    indexName = indexName,
    deleteByParams = buildDeleteBy(block),
    requestOptions = requestOptions,
  )
