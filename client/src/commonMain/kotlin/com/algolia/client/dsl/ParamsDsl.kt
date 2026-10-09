@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl

import com.algolia.client.dsl.generated.DSLBrowseParamsObject
import com.algolia.client.dsl.generated.DSLDeleteByParams
import com.algolia.client.dsl.generated.DSLSearchParamsObject
import com.algolia.client.model.search.BrowseParamsObject
import com.algolia.client.model.search.DeleteByParams
import com.algolia.client.model.search.SearchParamsObject

/**
 * Constructs a [SearchParamsObject] from a [DSLQuery] block. Last write wins: a non-null [query] is
 * written first and [block] may overwrite it; a later `filters { }` or `filters = "…"` replaces an
 * earlier `filters` value (same for `optionalFilters`).
 */
@AlgoliaExperimentalDsl
public fun query(
  query: String? = null,
  block: DSLQuery.() -> Unit,
): SearchParamsObject =
  DSLSearchParamsObject()
    .apply {
      if (query != null) {
        this.query = query
      }
      block()
    }
    .build()

/** Constructs a [BrowseParamsObject] from a [DSLBrowse] block. Last write wins. */
@AlgoliaExperimentalDsl
public fun browse(block: DSLBrowse.() -> Unit): BrowseParamsObject =
  DSLBrowseParamsObject().apply(block).build()

/**
 * Constructs a [DeleteByParams] from a [DSLDeleteBy] block. Last write wins. Throws
 * [IllegalArgumentException] when `filters { }`, or any group in it, adds no filter (dropping it
 * would widen the delete), and when the result has no filter and no geo condition.
 */
@AlgoliaExperimentalDsl
public fun deleteBy(block: DSLDeleteBy.() -> Unit): DeleteByParams =
  DSLDeleteByParams().apply(block).build().requireDeleteCondition()
