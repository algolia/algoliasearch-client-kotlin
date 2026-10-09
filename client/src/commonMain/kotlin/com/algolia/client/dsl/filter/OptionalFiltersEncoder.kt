@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl.filter

import com.algolia.client.dsl.AlgoliaExperimentalDsl
import com.algolia.client.model.search.OptionalFilters

internal object OptionalFiltersEncoder {
  operator fun invoke(rows: List<List<Filter.Facet>>): OptionalFilters? =
    rows
      .filter { it.isNotEmpty() }
      .takeIf { it.isNotEmpty() }
      ?.let { kept ->
        OptionalFilters.of(
          kept.map { row -> OptionalFilters.of(row.map { OptionalFilters.of(encode(it)) }) }
        )
      }

  private fun encode(facet: Filter.Facet): String {
    val value =
      when {
        facet.negated -> "-${facet.value}"
        facet.value.startsWith('-') -> "\\${facet.value}"
        else -> facet.value
      }
    return "${facet.attribute}:$value${facet.score?.let { "<score=$it>" }.orEmpty()}"
  }
}
