@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl.filter

import com.algolia.client.dsl.AlgoliaExperimentalDsl
import com.algolia.client.dsl.DSLParameters
import com.algolia.client.model.search.OptionalFilters

/** Collects `optionalFilters` rows of facet leaves: the outer list is `AND`, each row is `OR`. */
@DSLParameters
@AlgoliaExperimentalDsl
public class DSLFacetFilters private constructor(private val rows: FilterRows<Filter.Facet>) :
  DSLFacet by FacetLeafMixin({ rows.add(listOf(it)) }) {

  internal constructor() : this(FilterRows())

  /** ANDs the filters in [block] into this block. An empty block adds nothing. */
  public fun and(block: DSLFacetFilters.() -> Unit) {
    rows.and(DSLFacetFilters().apply(block).rows)
  }

  /** Adds the facet leaves in [block] as one `OR` row. An empty block adds nothing. */
  public fun or(block: DSLGroupFacet.() -> Unit) {
    rows.add(DSLGroupFacet().apply(block).leaves())
  }

  internal fun addRows(seed: List<List<Filter.Facet>>) {
    rows.addAll(seed)
  }

  internal fun rows(): List<List<Filter.Facet>> = rows.snapshot()
}

/** Constructs [OptionalFilters] from a facet-only DSL block, or `null` when the block is empty. */
@AlgoliaExperimentalDsl
public fun optionalFilters(block: DSLFacetFilters.() -> Unit): OptionalFilters? =
  writeOptionalFilters(block).value

internal fun writeOptionalFilters(
  block: DSLFacetFilters.() -> Unit
): FilterWrite<OptionalFilters, Filter.Facet> {
  val rows = DSLFacetFilters().apply(block).rows()
  return FilterWrite(OptionalFiltersEncoder(rows), rows)
}
