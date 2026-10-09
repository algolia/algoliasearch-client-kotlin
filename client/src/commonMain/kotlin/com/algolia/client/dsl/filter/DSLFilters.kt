@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl.filter

import com.algolia.client.dsl.AlgoliaExperimentalDsl
import com.algolia.client.dsl.DSLParameters

/**
 * Collects `filters` rows: top-level filters are `AND`ed in call order, [orFacet], [orTag] and
 * [orNumeric] add one `OR` row each. In delete-by filters a block or group that adds no filter
 * throws [IllegalArgumentException] instead of adding nothing.
 */
@DSLParameters
@AlgoliaExperimentalDsl
public class DSLFilters private constructor(private val rows: FilterRows<Filter>) :
  DSLFacet by FacetLeafMixin({ rows.add(listOf(it)) }),
  DSLTag by TagLeafMixin({ rows.add(listOf(it)) }),
  DSLNumeric by NumericLeafMixin({ rows.add(listOf(it)) }) {

  internal constructor() : this(FilterRows())

  /** ANDs the filters in [block] into this block. Empty: adds nothing (delete-by: throws). */
  public fun and(block: DSLFilters.() -> Unit) {
    rows.and(DSLFilters().apply(block).rows)
  }

  /** Adds one `OR` row of the facet leaves in [block]. Empty: adds nothing (delete-by: throws). */
  public fun orFacet(block: DSLGroupFacet.() -> Unit) {
    rows.add(DSLGroupFacet().apply(block).leaves())
  }

  /** Adds one `OR` row of the tag leaves in [block]. Empty: adds nothing (delete-by: throws). */
  public fun orTag(block: DSLGroupTag.() -> Unit) {
    rows.add(DSLGroupTag().apply(block).leaves())
  }

  /** Adds one `OR` row of numeric leaves in [block]. Empty: adds nothing (delete-by: throws). */
  public fun orNumeric(block: DSLGroupNumeric.() -> Unit) {
    rows.add(DSLGroupNumeric().apply(block).leaves())
  }

  internal fun addRows(seed: List<List<Filter>>) {
    rows.addAll(seed)
  }

  internal fun rows(): List<List<Filter>> = rows.snapshot()
}

/** Constructs a SQL `filters` string from the DSL block, or `null` when the block is empty. */
@AlgoliaExperimentalDsl
public fun filters(block: DSLFilters.() -> Unit): String? = writeFilters(block).value

internal fun writeFilters(block: DSLFilters.() -> Unit): FilterWrite<String, Filter> {
  val rows = DSLFilters().apply(block).rows()
  return FilterWrite(FiltersEncoder(rows), rows)
}

internal fun writeDeleteByFilters(block: DSLFilters.() -> Unit): FilterWrite<String, Filter> {
  val rows = DSLFilters().apply(block).rows()
  require(rows.isNotEmpty() && rows.none { it.isEmpty() }) {
    "deleteBy filters: a filters { } block or group adds no filter; dropping it would widen " +
      "the delete. Skip the delete when there is nothing to match."
  }
  return FilterWrite(FiltersEncoder(rows), rows)
}
