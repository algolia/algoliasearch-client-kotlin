@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl.filter

import com.algolia.client.dsl.AlgoliaExperimentalDsl

internal sealed interface Filter {

  val negated: Boolean

  data class Facet
  internal constructor(
    val attribute: String,
    val value: String,
    val score: Int? = null,
    override val negated: Boolean = false,
  ) : Filter {
    init {
      require(attribute.isNotEmpty()) { "Facet filter attribute must not be empty" }
      require(value.isNotEmpty()) { "Facet filter value must not be empty" }
    }
  }

  data class Tag internal constructor(val value: String, override val negated: Boolean = false) :
    Filter {
    init {
      require(value.isNotEmpty()) { "Tag filter value must not be empty" }
    }
  }

  sealed interface Numeric : Filter

  data class Comparison
  internal constructor(
    val attribute: String,
    val operator: NumericOperator,
    val value: Number,
    override val negated: Boolean = false,
  ) : Numeric {
    init {
      require(attribute.isNotEmpty()) { "Comparison filter attribute must not be empty" }
    }
  }

  data class Range
  internal constructor(
    val attribute: String,
    val lowerBound: Number,
    val upperBound: Number,
    override val negated: Boolean = false,
  ) : Numeric {
    init {
      require(attribute.isNotEmpty()) { "Range filter attribute must not be empty" }
    }
  }
}

/** Operator of a numeric comparison filter. */
@AlgoliaExperimentalDsl
public enum class NumericOperator(public val raw: String) {
  Less("<"),
  LessOrEquals("<="),
  Equals("="),
  NotEquals("!="),
  GreaterOrEquals(">="),
  Greater(">"),
}
