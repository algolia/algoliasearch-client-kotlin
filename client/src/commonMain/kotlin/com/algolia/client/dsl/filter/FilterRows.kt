package com.algolia.client.dsl.filter

internal class FilterRows<L : Filter> {
  private val rows: MutableList<List<L>> = mutableListOf()

  fun add(row: List<L>) {
    rows.add(row)
  }

  fun and(child: FilterRows<L>) {
    if (child.rows.isEmpty()) rows.add(emptyList()) else rows.addAll(child.rows)
  }

  fun addAll(seed: List<List<L>>) {
    rows.addAll(seed)
  }

  fun snapshot(): List<List<L>> = rows.toList()
}
