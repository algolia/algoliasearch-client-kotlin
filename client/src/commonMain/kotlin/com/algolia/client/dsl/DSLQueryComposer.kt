@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl

import com.algolia.client.dsl.filter.DSLFilters
import com.algolia.client.dsl.generated.DSLSearchParamsObjectAdditions
import com.algolia.client.model.search.SearchParamsObject

/**
 * Collects query fragments and builds one [SearchParamsObject]. Nothing runs until [build]: every
 * [add] fragment merges into the [base] value of its field (filter fragments AND-ed inside one
 * [DSLFilters], list fragments appended in call order), then every [override] runs in call order
 * (last write wins). A list field whose fragments add nothing is sent as `[]`; empty filter
 * fragments leave the field as the base set it, or omitted. [build] throws [IllegalStateException]
 * when a fragment merges into a `filters` or `optionalFilters` value that [base] assigned directly.
 * Every [build] re-runs every stored block. Not thread-safe.
 */
@DSLParameters
@AlgoliaExperimentalDsl
public class DSLQueryComposer
private constructor(private val core: ComposerCore<DSLSearchParamsObjectAdditions, DSLQuery>) {

  /** Starts every [build] from [base]; fragments merge into it. */
  public constructor(
    base: DSLQuery.() -> Unit = {}
  ) : this(
    ComposerCore(
      ::DSLSearchParamsObjectAdditions,
      { DSLQuery() },
      base,
      merge = true,
      DSLSearchParamsObjectAdditions::applyTo,
    )
  )

  /**
   * Starts every [build] from a copy of [from] (never modified); a field with [add] fragments is
   * replaced by them, other fields keep the object's value.
   */
  public constructor(
    from: SearchParamsObject
  ) : this(
    ComposerCore(
      ::DSLSearchParamsObjectAdditions,
      { DSLQuery(from) },
      {},
      merge = false,
      DSLSearchParamsObjectAdditions::applyTo,
    )
  )

  /** Stores [block]; it runs on every [build], before the overrides. */
  public fun add(block: DSLQueryAdditions.() -> Unit): Unit = core.add(block)

  /** Stores [block]; it runs on every [build], after the fragments. Last write wins. */
  public fun override(block: DSLQuery.() -> Unit): Unit = core.override(block)

  public fun build(): SearchParamsObject = core.build().build()
}
