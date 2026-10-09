@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl

import com.algolia.client.dsl.filter.DSLFilters
import com.algolia.client.dsl.generated.DSLDeleteByParamsAdditions
import com.algolia.client.model.search.DeleteByParams

/**
 * Collects delete-by `filters { }` fragments and builds one [DeleteByParams]. Nothing runs until
 * [build]: every [add] fragment is AND-ed with the [base] filters inside one [DSLFilters], then
 * every [override] runs in call order (last write wins). [build] throws [IllegalStateException]
 * when a fragment merges into a `filters` value that [base] assigned directly, and
 * [IllegalArgumentException] when a fragment or group adds no filter or when the result has no
 * filter and no geo condition. Every [build] re-runs every stored block. Not thread-safe.
 */
@DSLParameters
@AlgoliaExperimentalDsl
public class DSLDeleteByComposer public constructor(base: DSLDeleteBy.() -> Unit = {}) {
  private val core =
    ComposerCore(
      ::DSLDeleteByParamsAdditions,
      { DSLDeleteBy() },
      base,
      merge = true,
      DSLDeleteByParamsAdditions::applyTo,
    )

  /** Stores [block]; it runs on every [build], before the overrides. */
  public fun add(block: DSLDeleteByAdditions.() -> Unit): Unit = core.add(block)

  /** Stores [block]; it runs on every [build], after the fragments. Last write wins. */
  public fun override(block: DSLDeleteBy.() -> Unit): Unit = core.override(block)

  /**
   * Builds a [DeleteByParams]. Throws [IllegalArgumentException] when the result has no filter and
   * no geo condition.
   */
  public fun build(): DeleteByParams = core.build().build().requireDeleteCondition()
}

internal fun DeleteByParams.requireDeleteCondition(): DeleteByParams {
  val hasCondition =
    !filters.isNullOrBlank() ||
      facetFilters != null ||
      numericFilters != null ||
      tagFilters != null ||
      !aroundLatLng.isNullOrBlank() ||
      aroundRadius != null ||
      insideBoundingBox != null ||
      insidePolygon != null
  require(hasCondition) {
    "deleteBy: no filter and no geo condition; the engine rejects an empty delete-by. " +
      "Skip the delete when there is nothing to match."
  }
  return this
}
