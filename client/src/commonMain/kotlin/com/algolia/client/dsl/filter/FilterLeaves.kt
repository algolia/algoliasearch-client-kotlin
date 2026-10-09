@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl.filter

import com.algolia.client.dsl.AlgoliaExperimentalDsl

/**
 * Facet leaf helpers of the filter receivers. Throws [IllegalArgumentException] when the attribute
 * or value is empty.
 */
@AlgoliaExperimentalDsl
public sealed interface DSLFacet {
  /**
   * Adds a facet filter on [attribute] equal to [value]. A non-null [score] is emitted as
   * `<score=N>`, including `0`. Pass [isNegated] by name: the third positional argument is [score].
   */
  public fun facet(
    attribute: String,
    value: String,
    score: Int? = null,
    isNegated: Boolean = false,
  ): Unit

  /** Adds a facet filter on [attribute] equal to [value]; same [score] and [isNegated] rules. */
  public fun facet(
    attribute: String,
    value: Boolean,
    score: Int? = null,
    isNegated: Boolean = false,
  ): Unit

  /** Adds a facet filter on [attribute] equal to [value]; same [score] and [isNegated] rules. */
  public fun facet(
    attribute: String,
    value: Number,
    score: Int? = null,
    isNegated: Boolean = false,
  ): Unit
}

/**
 * Tag leaf helpers of the filter receivers. Throws [IllegalArgumentException] when the value is
 * empty.
 */
@AlgoliaExperimentalDsl
public sealed interface DSLTag {
  /** Adds a `_tags` filter for [value]. */
  public fun tag(value: String, isNegated: Boolean = false): Unit
}

/**
 * Numeric leaf helpers of the filter receivers. Throws [IllegalArgumentException] when the
 * attribute is empty.
 */
@AlgoliaExperimentalDsl
public sealed interface DSLNumeric {
  /** Adds a range filter on [attribute] between [lowerBound] and [upperBound], inclusive. */
  public fun range(
    attribute: String,
    lowerBound: Number,
    upperBound: Number,
    isNegated: Boolean = false,
  ): Unit

  /** Adds a range filter on [attribute] covering [range], inclusive. */
  public fun range(attribute: String, range: IntRange, isNegated: Boolean = false): Unit

  /** Adds a range filter on [attribute] covering [range], inclusive. */
  public fun range(attribute: String, range: LongRange, isNegated: Boolean = false): Unit

  /** Adds a numeric comparison of [attribute] against [value] with [operator]. */
  public fun comparison(
    attribute: String,
    operator: NumericOperator,
    value: Number,
    isNegated: Boolean = false,
  ): Unit
}

internal class FacetLeafMixin(private val sink: (Filter.Facet) -> Unit) : DSLFacet {
  override fun facet(attribute: String, value: String, score: Int?, isNegated: Boolean) {
    sink(Filter.Facet(attribute, value, score, isNegated))
  }

  override fun facet(attribute: String, value: Boolean, score: Int?, isNegated: Boolean) {
    sink(Filter.Facet(attribute, value.toString(), score, isNegated))
  }

  override fun facet(attribute: String, value: Number, score: Int?, isNegated: Boolean) {
    sink(Filter.Facet(attribute, value.toString(), score, isNegated))
  }
}

internal class TagLeafMixin(private val sink: (Filter.Tag) -> Unit) : DSLTag {
  override fun tag(value: String, isNegated: Boolean) {
    sink(Filter.Tag(value, isNegated))
  }
}

internal class NumericLeafMixin(private val sink: (Filter.Numeric) -> Unit) : DSLNumeric {
  override fun range(
    attribute: String,
    lowerBound: Number,
    upperBound: Number,
    isNegated: Boolean,
  ) {
    sink(Filter.Range(attribute, lowerBound, upperBound, isNegated))
  }

  override fun range(attribute: String, range: IntRange, isNegated: Boolean) {
    sink(Filter.Range(attribute, range.first, range.last, isNegated))
  }

  override fun range(attribute: String, range: LongRange, isNegated: Boolean) {
    sink(Filter.Range(attribute, range.first, range.last, isNegated))
  }

  override fun comparison(
    attribute: String,
    operator: NumericOperator,
    value: Number,
    isNegated: Boolean,
  ) {
    sink(Filter.Comparison(attribute, operator, value, isNegated))
  }
}
