@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl.filter

import com.algolia.client.dsl.AlgoliaExperimentalDsl

internal object FiltersEncoder {

  operator fun invoke(rows: List<List<Filter>>): String? =
    rows
      .filter { it.isNotEmpty() }
      .takeIf { it.isNotEmpty() }
      ?.joinToString(" AND ") { emitRow(it) }

  private fun emitRow(row: List<Filter>): String {
    if (row.size == 1) return emitLeaf(row.single())
    return row.joinToString(separator = " OR ", prefix = "(", postfix = ")") { emitLeaf(it) }
  }

  private fun emitLeaf(filter: Filter): String {
    val text =
      when (filter) {
        is Filter.Facet -> emitFacet(filter)
        is Filter.Tag -> emitTag(filter)
        is Filter.Comparison -> emitComparison(filter)
        is Filter.Range -> emitRange(filter)
      }
    return if (filter.negated) "NOT $text" else text
  }

  private fun emitFacet(filter: Filter.Facet): String {
    val attribute = FilterQuote.quote(filter.attribute)
    val value = FilterQuote.quote(filter.value)
    val score = filter.score?.let { "<score=$it>" }.orEmpty()
    return "$attribute:$value$score"
  }

  private fun emitTag(filter: Filter.Tag): String = "_tags:${FilterQuote.quote(filter.value)}"

  private fun emitComparison(filter: Filter.Comparison): String =
    "${FilterQuote.quote(filter.attribute)} ${filter.operator.raw} ${filter.value}"

  private fun emitRange(filter: Filter.Range): String =
    "${FilterQuote.quote(filter.attribute)}:${filter.lowerBound} TO ${filter.upperBound}"
}
